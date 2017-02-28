package vkapi;

import lombok.Data;

import java.util.HashMap;

public @Data class VkJson {

	private HashMap<String, Object> data;

	public void addData (String name, Object obj) {
		data.put(name, obj);
	}

	public Object getData (String name) {
		return data.get(name);
	}

}
