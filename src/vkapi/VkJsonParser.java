package vkapi;

import java.util.Arrays;

public class VkJsonParser {

	public static VkJson parse (String s) {
		s = s.trim();
		if (s.startsWith("{") == false || s.endsWith("}") == false) {
			return null;
		}
		VkJson ret = new VkJson();
		int startptr = 1;
		for (char ch : s.toCharArray()) {

		}
		return ret;
	}

}
