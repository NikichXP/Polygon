package myparser;

import myparser.configurer.GlobalConfig;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;
import java.util.stream.Collectors;

/** Example:

 $global$
 '#lombok': @class:Data; @class:NoArgsConstructor //@ - annotation, then - field-type-etc, then - title (until ;)
 //# is a module. should be implemented BEFORE the usage
 'entity': String(id); [lombok] //on each entity: add String id, use module lombok
 'field': private, getter, setter //on each entity private modifier, getter, setter


 $entity$
 'User' {
 String: name, pass, login, phone, mail
 LocalDateTime: registered
 #constructor# mail, pass
 }

 'Product' {
 String: id, title
 Id@User: user //finds User. adds it's Id typed-field
 }

 $spring$
 'data' {
 'db' : mongo
 'db-url' : mongodb://login:pass@myhost.com:27017/abcd
 }

	 */
public class CoreParser {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new FileReader("C:\\meta.app"));
		List<String> lines = br.lines().collect(Collectors.toList());
		int start = 0;
		for (int i = 1; i < lines.size(); i++) {
			if (lines.get(i).matches("[$]\\S*[$]")) {
				switch (lines.get(start).replace('$', ' ').trim()) {
					case "global":
						GlobalConfig.initConfigList(lines.subList(start + 1, i));
						break;
					case "entity":
						EntitySetup.setEntities(lines.subList(start, i));
						break;
					case "spring":
						analyze(lines.subList(start, i));
						break;
				}
				start = i;
			}
		}

	}

	private static void analyze(List<String> strings) {
		strings.forEach(x -> System.out.println(x));
		System.out.println(".......");
	}

}
