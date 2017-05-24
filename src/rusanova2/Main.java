package rusanova2;

import java.util.*;

public class Main {
	
	private static List<Core> cores = new ArrayList<>();
	private static List<Task> tasks = new ArrayList<>();
	private static Random rand = new Random();
	
	public static void main (String[] args) {
		
		createLinks();
//		System.out.println(checkLinks());
		
		createTasks2(10, 1, 5, 0.4);
		System.out.println(checkTasks());
		printLinks();
		
	}
	
	private static void printLinks () {
		boolean[] startNodes = new boolean[tasks.size()];
		tasks.forEach(task -> task.next.forEach(
				link -> {
					startNodes[link.link.id] = true;
				})
		);
		for (int i = 0; i < startNodes.length; i++) {
			if (!startNodes[i]) {
				getRoutes(tasks.get(i)).forEach(list ->
						System.out.println(printRoute(list))
				);
			}
		}
	}
	
	private static String printRoute (LinkedList<Task> list) {
		/*
			.map(task -> task.getId() + " (" + task.getWeight() + ")")
			.reduce((s1, s2) -> s1 + " -> " + s2)
			.ifPresent(System.out::println)
		 */
		StringBuilder sb = new StringBuilder(list.get(0).print());
		for (int i = 0; i < list.size() - 1; i++) {
			Task.Link link = list.get(i).findNext(list.get(i+1));
			sb.append(" -").append(link.weight).append("-> ").append(link.link.print());
		}
		return sb.toString();
	}
	
	private static LinkedList<LinkedList<Task>> getRoutes (Task task) {
		LinkedList<LinkedList<Task>> ret = new LinkedList<>();
		if (task.next.size() == 0) {
			LinkedList<Task> rx = new LinkedList<>();
			rx.add(task);
			ret.add(rx);
			return ret;
		} else {
			for (Task.Link link : task.next) {
				ret.addAll(getRoutes(link.link));
			}
			ret.forEach(list -> list.push(task));
			return ret;
		}
	}
	
	private static void createTasks2 (int tasksCount, int minWegth, int maxWeight, double corellation) { //corellation = nodes / (nodes + links)
		
		for (int i = 0; i < tasksCount; i++) {
			tasks.add(new Task(rand.nextInt(maxWeight - minWegth) + minWegth));
		}
		
		while (calculateCorrelation() > corellation) {
			int from = rand.nextInt(tasksCount);
			int to = rand.nextInt(tasksCount);
			
			System.out.println("trying add link " + from + " -> " + to);
			
			tasks.get(from).addNext(tasks.get(to), 1);
			
			if (!checkTasks()) {
				tasks.get(from).remove(tasks.get(to));
				System.out.println("error cycle link " + from + " -> " + to);
				System.gc();
			}
		}
		
	}
	
	private static double calculateCorrelation () {
		
		int linkSum = 0, taskSum = 0;
		
		for (Task task : tasks) {
			taskSum += task.getWeight();
			for (Task.Link link : task.next) {
				linkSum += link.weight;
			}
		}
		
		return (taskSum + 0.0) / (taskSum + linkSum);
	}
	
	private static void createTasks () {
		for (int i = 0; i < 10; i++) {
			tasks.add(new Task());
		}
		
		for (int i = 0; i < 9; i++) {
			nextTask(i, i + 1);
		}
		nextTask(9, 0);
		
	}
	
	private static boolean checkTasks () {
		boolean res = true;
		for (Task task : tasks) {
			res = res & innerRecursive(task, 0);
		}
		return res;
	}
	
	private static boolean innerRecursive (Task cur, int step) {
		if (step > tasks.size()) {
			return false;
		}
		boolean flag = true;
		for (Task.Link next : cur.next) {
			flag = flag & innerRecursive(next.link, step + 1);
		}
		return flag;
	}
	
	private static void createLinks () {
		for (int i = 0; i < 10; i++) {
			cores.add(new Core());
		}
		
		//create links
		for (int i = 0; i < 8; i++) {
			link(i, i + 1);
		}
	}
	
	private static boolean checkLinks () {
		boolean[] conns = new boolean[cores.size()]; //determines can we get core[i] via core[0]
		conns[0] = true;
		for (int i = 0; i < cores.size() - 1; i++) {
			cores.stream()
					.filter(core -> conns[core.getId()]) //filters procs that live now
					.forEach(core -> core.links.forEach(link -> {
						if (!conns[link.getId()]) {
							conns[link.getId()] = true;
						}
					}));
		}
		boolean x = true;
		for (boolean b : conns) {
			x = b & x;
		}
		return x;
	}
	
	private static void link (int a, int b) {
		cores.get(a).addLink(cores.get(b));
	}
	
	private static void nextTask (int a, int b) {
		tasks.get(a).addNext(tasks.get(b));
	}
	
	
}
