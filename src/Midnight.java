import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 * Requires org.apache.httpcomponents:httpclient:4.4.1
 *
 * @author NikichXP
 */

public class Midnight {

	public static void main(String[] args) throws Exception {

		if (!args[0].startsWith("http://")) {
			throw new IllegalStateException("Путь должен быть http: " + args[0]);
		}

		String response = query(args[0]);

		if ((response.startsWith("<!DOCTYPE html>") || response.startsWith("<html")) == false) {
			throw new IllegalStateException("Данный URL – " + args[0] + "не содержит HTML содержания.");
		}

		Map<String, Integer> wordsCounter = new HashMap<>();

		long wordsAtAll = Arrays.stream(response.split("<"))
//				.map(str -> "<" + str)
//				.map(str -> str.substring(Math.max(0, str.lastIndexOf('>') + 1)))
//				.filter(x -> !x.startsWith("<"))
				.map(String::trim)
				.map(str -> str.substring(Math.max(0, str.lastIndexOf('>'))))
				.distinct()
				.flatMap(string -> Arrays.stream(string.split("[ \u0096,.!?;\n]")))
				.filter(str -> str.matches("[a-zA-Zа-яА-Я]*") && str.length() > 0)
				.peek(str -> wordsCounter.put(str, (wordsCounter.get(str) != null) ? wordsCounter.get(str) + 1 : 1))
				.distinct()
				.count();

		wordsCounter.keySet().stream().sorted(Comparator.comparingInt(wordsCounter::get)).forEach(key -> System.out.println(wordsCounter.get(key) + "\t раз: " + key ));

		System.out.println("Всего слов: " + wordsAtAll);

	}

	public static String query(String path) throws Exception {
		Socket s = new Socket(InetAddress.getByName("stackoverflow.com"), 80);
		PrintWriter pw = new PrintWriter(s.getOutputStream());
		pw.print("GET / HTTP/1.1\r\n");
		pw.print("Host: stackoverflow.com\r\n\r\n");
		pw.flush();
		BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
		StringBuilder sb = new StringBuilder();
		String t;
		while((t = br.readLine()) != null) {
			sb.append(t);
		}
		br.close();
		return sb.toString();
	}

}
