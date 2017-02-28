import java.util.HashMap;

public class Main5 {

	public static void main(String[] args) {
		HashMap<Character, Character> map = new HashMap<>();
		HashMap<Character, Character> backmap = new HashMap<>();
		map.put(' ', '#');
		map.put('.', '_');
		map.put('!', '?');
		map.put('?', '.');
		map.put(',', '!');
		backmap.put('#', ' ');
		backmap.put('_', '.');
		backmap.put('?', '!');
		backmap.put('.', '?');
		backmap.put('!', ',');

		for (char c = 'A'; c <= 'Z'; c++) {
			boolean flag = true;
			while (flag) {
				char ch;
				ch = ((char) (Math.random()*('Z' - 'A' + 1.0)));
				if (Math.random() > 0.5) {
					ch += 'a';
				} else {
					ch += 'A';
				}
				System.out.println("ch:" + (int)ch + "!");
				if (map.values().contains(ch) == false) {
					flag = false;
					map.put(c, ch);
					backmap.put(ch, c);
				}

			}
		}
		for (char c = 'a'; c <= 'z'; c++) {
			boolean flag = true;
			while (flag) {
				char ch;
				ch = ((char) (Math.random()*('Z' - 'A' + 1.0)));
				if (Math.random() > 0.5) {
					ch += 'a';
				} else {
					ch += 'A';
				}
				if (map.values().contains(ch) == false) {
					flag = false;
					map.put(c, ch);
					backmap.put(ch, c);
				}

			}
		}

		for (char cz : map.keySet()) {
			System.out.println(cz + " => " + map.get(cz));
		}

		String text = "Some text to encode!";

		StringBuilder sb = new StringBuilder();
		for (char c : text.toCharArray()) {
			sb.append(map.get(c));
		}

		System.out.println(sb.toString());

	}

}
