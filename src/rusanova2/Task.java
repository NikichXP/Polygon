package rusanova2;

import lombok.Data;

import java.util.ArrayList;
import java.util.Comparator;
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
		if (task.id == this.id) {
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
	
	public int getCriticalTime() {
		if (next.size() == 0) {
			return weight;
		}
		Task crit = next.stream().max(Comparator.comparingInt(t -> t.link.getCriticalTime())).orElse(null).link;
		return crit.weight + weight;
	}
	
	public List<Task> getCriticalRoute() {
		if (next.size() == 0) {
			List<Task> task = new ArrayList<>();
			task.add(this);
			return task;
		}
		return next.stream().map(task -> task.link.getCriticalRoute()).max(Comparator.comparingInt(List::size)).orElse(new ArrayList<>());
	}
	
	public Link findNext (Task task) {
		return next.stream().filter(e -> e.link.id == task.id).findAny().orElse(null);
	}
	public String print () {
		return getId() + " (" + getWeight() + ")";
	}
	
	
	public String toString() {
		return "{ id : " + id + ", weight : " + weight + ", crittime : " + getCriticalTime() + " ,links : [" + next.stream().map(to -> to.link.getId() + "").reduce((l1, l2) -> l1 + ", " + l2).orElse("") + "]}";
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
