package rusanova2;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Task {
	
	int id;
	static int id_gen = 0;
	List<Task> next = new ArrayList<>();
	int time;
	
	public Task () {
		this.id = id_gen++;
	}
	
	public void addNext (Task task) {
		if (task == this) {
			return;
		}
		next.add(task);
	}
	
	public String toString() {
		return "{ id : " + id + ", links : [" + next.stream().map(link -> link.getId() + "").reduce((l1, l2) -> l1 + ", " + l2).orElse("") + "]}";
	}
}
