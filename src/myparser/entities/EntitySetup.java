package myparser.entities;

import java.util.ArrayList;
import java.util.List;

public class EntitySetup {

	private static List<Entity> entities = new ArrayList<>();

	public static void setEntities(List<String> entities) {
		int start = 0, end;
		for (int i = 0; i < entities.size(); i++) {
			if (entities.get(i).matches("'[A-Za-z0-9]*' \\{")) {
				start = i;
			}
			if (entities.get(i).trim().equals("}")) {
				buildEntity(entities.subList(start, i));
			}
		}

	}

	private static void buildEntity(List<String> strings) {
		strings.forEach(x -> System.out.println(x));
		String name = strings.get(0).substring(1, strings.get(0).substring(1).indexOf('\'') + 1);
		System.out.println(name);
	}
}
