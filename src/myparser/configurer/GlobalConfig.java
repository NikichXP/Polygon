package myparser.configurer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GlobalConfig {

	private static String defaultEntityId;
	private static Map<String, Class> installedAddons = new HashMap<>();

	public static void initConfigList(List<String> args) {

		System.out.println("Global config recieve:");
		args.stream()
				.map(x -> (!x.contains("//")) ? x : x.substring(0, x.indexOf("//")))
				.forEach(x -> {
					if (x.startsWith("'#")) {
						//TODO Here we need to import addons
					}

					switch (x.substring(0, x.indexOf(':'))) {
						case "'#lombok'":

					}
				});

	}

}
