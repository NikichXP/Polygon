package rusanova2;

import java.util.*;

public class Core {

	public static int id_gen = 0;
	public LinkedList<Core> links = new LinkedList<>();
	public int id;
	public int freeSince = 0;
	public List<Task> schedule = new ArrayList<>();
	public HashMap<Integer, Boolean> linkingOut = new HashMap<>();

	public Core() {
		id = id_gen++;
	}

	public void addLink(Core other) {
		if (this.links.contains(other)) {
			return;
		}
		this.links.add(other);
		other.addLink(this);
	}

	public String toString() {
		return "{ id : " + id + ", links : [" + links.stream().map(link -> link.id + "").reduce((l1, l2) -> l1 + ", " + l2).orElse("") + "]}";
	}

	public int steps(int otherId) {
		return Main.connMatrix[this.id][otherId];
	}

	public int steps(Core other) {
		return steps(other.id);
	}

	public void putTask(Task task) {
		schedule.add(task);
		freeSince = task.plan(freeSince, this);
	}

//	public int expectedRouting (Core from, int startCycle) {
//		if (links.contains(from)) {
//			return
//		}
//	}

	public int transferOut(int from, int weight) {
		while (weight > 0) {
			if (linkingOut.get(from) == null || !linkingOut.get(from)) {
				linkingOut.put(from, true);
				weight--;
			}
			from++;
		}
		return from;
	}

	public int deliverTask(Task task, int startDeliver) {
		return 0;
	}

	public boolean canPut(Task task, int cycle) {
		if (schedule.stream()
				.map(t -> t.endExecute)
				.max(Comparator.comparingInt(t -> t))
				.orElse(0) > cycle
				) {
			return false;
		}
		return task.getLastRequiredPlusRouting(this) <= cycle;
	}
}