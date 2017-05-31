package rusanova2;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Task {
	
	int id;
	static int id_gen = 0;
	List<Link> next = new ArrayList<>();
	int weight = 1;
	
	public Task () {
		this.id = id_gen++;
	}
	
	public Task (int weight) {
		this();
		this.weight = weight;
	}
	
	public void addNext (Task task) {
		if (task == this) {
			return;
		}
		next.add(new Link(task));
	}
	
	public void addNext (Task task, int weight) {
		if (weight == 0) {
			return;
		}
		if (task == this) {
			return;
		}
		if (next.stream().filter(link -> link.link.id == task.id).findAny().orElse(null) == null) {
			if (weight > 0) {
				next.add(new Link(task, weight));
			}
		} else {
			next.stream().filter(link -> link.link.id == task.id).findAny().ifPresent(link -> link.weight += weight);
		}
	}
	
	public void remove (Task task) {
		next.removeIf(link -> link.link.id == task.id);
	}
	
	public String toString() {
		return "{ id : " + id + ", weight : " + weight + ", links : [" + next.stream().map(to -> to.link.getId() + "").reduce((l1, l2) -> l1 + ", " + l2).orElse("") + "]}";
	}
	
	public String print () {
		return getId() + " (" + getWeight() + ")";
	}
	
	public Link findNext (Task task) {
		return next.stream().filter(e -> e.link.id == task.id).findAny().orElse(null);
	}
	
	
	public static class Link {
		Task link;
		int weight;
		
		public Link (Task task) {
			this.link = task;
			this.weight = 1;
		}
		public Link (Task task, int weight) {
			this.link = task;
			this.weight = weight;
		}
	}
}
