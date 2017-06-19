package rusanova2;

import lombok.Data;
import lombok.val;
import util.Collect;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

@Data
public class Task {
	
	static int id_gen = 0;
	int id;
	List<Link> next = new ArrayList<>();
	List<Task> incomingTask = new ArrayList<>();
	int weight = 1;
	
	//made by core
	int startExecute, endExecute;
	Core executeCore;
	
	public Task () {
		this.id = id_gen++;
	}
	
	public Task (int weight) {
		this();
		this.weight = weight;
	}
	
	public int plan2 (int cycle, Core core) {
		this.startExecute = cycle;
		this.endExecute = cycle + weight;
		this.executeCore = core;
		return endExecute;
	}
	
	int plan (int startTime, Core core) {
		this.startExecute = incomingTask.stream()
			.map(task -> {
				int deltime = core.deliverTask(task, endExecute);
				return deltime;
			})
			.max(Comparator.comparingInt(e -> e)).orElse(0);
		this.startExecute = Math.max(startTime, startExecute);
		endExecute = startExecute + weight;
		executeCore = core;
		return endExecute;
	}
	
	public void addNext (Task task) {
		addNext(task, 1);
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
		task.incomingTask.add(this);
	}
	
	public void remove (Task task) {
		next.removeIf(link -> link.link.id == task.id);
	}
	
	public int getLastRequiredPlusRouting (Core expected) {
		return incomingTask.stream()
			.peek(System.out::println)
			.map(task -> task.endExecute + task.executeCore.steps(expected) * task.getLinkWeight(this))
			.max(Comparator.comparingInt(t -> t))
			.orElse(0);
	}
	
	private int getLinkWeight (Task task) {
		return Collect.get(next, link -> link.link.id == task.id).weight;
	}
	
	public int getCriticalTime () {
		if (next.size() == 0) {
			return weight;
		}
		Task crit = next.stream().max(Comparator.comparingInt(t -> t.link.getCriticalTime())).orElse(null).link;
		return crit.getCriticalTime() + weight;
	}
	
	public LinkedList<Task> getLongestRoute () {
		if (next.size() == 0) {
			LinkedList<Task> task = new LinkedList<>();
			task.add(this);
			return task;
		}
		val ret = next.stream().map(task -> task.link.getLongestRoute()).max(Comparator.comparingInt(List::size)).orElse(new LinkedList<>());
		ret.push(this);
		return ret;
	}
	
	public List<Task> getCriticalRoute () {
		if (next.size() == 0) {
			List<Task> task = new ArrayList<>();
			task.add(this);
			return task;
		}
		val ret = next.stream().map(task -> task.link.getCriticalRoute())
			.max(Comparator.comparingInt(list -> list.stream()
				.map(task -> task.weight)
				.reduce((integer, integer2) -> integer + integer2).get()))
			.orElse(new ArrayList<>());
		ret.add(this);
		return ret;
	}
	
	public Link findNext (Task task) {
		return next.stream().filter(e -> e.link.id == task.id).findAny().orElse(null);
	}
	
	public String print () {
		return getId() + " (" + getWeight() + ")";
	}
	
	
	public String toString () {
		return "{ id : " + id + ", next : [" + next.stream().map(to -> to.link.getId() + "").reduce((l1, l2) -> l1 + ", " + l2).orElse("")
			+ "], prev: [" + incomingTask.stream().map(task -> task.id + "").reduce((s1, s2) -> s1 + ", " + s2).orElse("") +
			"], exec : " + startExecute + "-" + endExecute + "}";
//		return "{ id : " + id + ", weight : " + weight + ", crittime : " + getCriticalTime() + " ,links : [" + next.stream().map(to -> to.link.getId() + "").reduce((l1, l2) -> l1 + ", " + l2).orElse("") + "]}";
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
		
		public String toString () {
			return "{ link : " + link.id + ", weight: " + weight + "}";
		}
	}
}
