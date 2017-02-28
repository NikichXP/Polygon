package myparser;

public class CoreParser {

	/*

	$global$
	'config' { //config reserved
	'entity': String(id); [lombok] //on each entity: add String id, use module lombok
	'field': private, getter, setter //on each entity private modifier, getter, setter
	'lombok': @class:Data; @class:NoArgsConstructor //@ - annotation, then - field-type-etc, then - title (until ;)
	}

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

	public static void main(String[] args) {
		//TODO implement this
	}

}
