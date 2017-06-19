package rusanova2;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
	
	static List<Core> cores = new ArrayList<>();
	static List<Task> gtasks = new ArrayList<>();
	static List<String> resStr = new LinkedList<>();
	static int[][] connMatrix;
	private static Random rand = new Random();
	
	public static void main (String[] args) {
		
		
		int baseWeight = 5;

//		createTasks();
		for (double corell = 0.1; corell < 1; corell += 0.05) {
			cores.clear();
			createLinks();
			gtasks.clear();
			Task.id_gen = 0;
			createTasks2(30, 1, 5, corell, (int) (baseWeight / (5 * corell)), (int) (baseWeight / corell));
			
			printLinks();
			
			//var 3
			System.out.println("\nVariant 3: \n");
			List<Task> taskList = gtasks.stream()
				.sorted(Comparator.comparingInt(t -> -t.getCriticalTime()))
//				.peek(System.out::println)
				.collect(Collectors.toList());
			
			//var 4
//		System.out.println("\nVariant 4: \n");
//		List<Task> taskList = getVar4();

//		input2(taskList);
			//var 3 (7)
			List<Core> prior = cores.stream().sorted((c1, c2) -> c2.links.size() - c1.links.size())
				.collect(Collectors.toList());
			
			doWork(taskList, prior);
			
			System.out.println();
			for (Core core : cores) {
				System.out.println(core.schedule);
			}
		}
		//var 5
//		System.out.println("\nVariant 5: \n");
//		getVar5();
	}
	
	private static void doWork (List<Task> tasks, List<Core> corePriority) {
		tasks.sort((t1, t2) -> {
			if (!t1.incomingTask.contains(t2) && !t2.incomingTask.contains(t1)) {
				return 0;
			}
			if (t1.incomingTask.contains(t2)) {
				return 1;
			} else {
				return -1;
			}
		});
		for (int i = 0; i < tasks.size(); i++) {
//			if (tasks.get(i).incomingTask) TODO Sort here, if prequisitives complete
		}
		int cycle = 0;
		for (Task task : tasks) {
			boolean put = false;
			do {
				for (Core core : corePriority) {
					if (core.canPut(task, cycle)) {
						core.putTask(task, cycle);
						put = true;
						break;
					}
				}
				if (!put) {
					cycle++;
				}
			} while (!put);
		}
	}
	
	private static void tryPutTask (Task task) {
		
	}
	
	private static boolean allTasksArePut () {
		for (Task task : gtasks) {
			if (task.executeCore == null) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * First processor got free
	 */
	private static void input2 (List<Task> taskList) {
		for (Task task : taskList) {
//			cores.stream().min(Comparator.comparingInt(core -> core.freeSince))
//					.orElse(null).putTask(task);
		}
		
		for (Core core : cores) {
			System.out.println(core.schedule);
		}
	}
	
	private static List<Task> getVar4 () {
		return gtasks.stream().sorted((t1, t2) -> (t1.getLongestRoute().size() != t2.getLongestRoute().size())
			? t2.getLongestRoute().size() - t1.getLongestRoute().size()
			: ((t2.next.size() + t2.incomingTask.size()) - (t1.next.size() + t1.incomingTask.size()))
		).peek(System.out::println).collect(Collectors.toList());
	}
	
	private static void getVar5 () {
		List<Task> crits = gtasks.stream()
			.map(Task::getLongestRoute)
			.max(Comparator.comparingInt(List::size))
			.orElseThrow(() -> new IllegalArgumentException("No routes found"));
		
		boolean added[] = new boolean[gtasks.size()];
//		System.out.println(crits);
		System.out.println("crit route: " + crits.stream().map(task -> task.id + "").reduce((s1, s2) -> s1 + " -> " + s2).orElse(""));
		crits.forEach(crt -> {
			added[crt.id] = true;
			System.out.println(crt);
		});
		
		gtasks.stream().sorted((task1, task2) -> {
			if (retain(crits, task1.getCriticalRoute()) != retain(crits, task2.getCriticalRoute())) {
				return retain(crits, task2.getCriticalRoute()) - retain(crits, task1.getCriticalRoute());
			}
			return task2.getCriticalRoute().size() - task1.getCriticalRoute().size();
		}).filter(i -> !added[i.id]).forEach(System.out::println);
	}
	
	public static int retain (List<Task> tasks, List<Task> links) {
		return (int) links.stream().filter(tasks::contains).count();
	}
	
	private static void printLinks () {
		if (!checkTasks()) {
			System.out.println("Tasks are cycled, can not print");
			return;
		}
		boolean[] startNodes = new boolean[gtasks.size()];
		gtasks.forEach(task -> task.next.forEach(
			link -> {
				startNodes[link.link.id] = true;
			})
		);
		for (int i = 0; i < startNodes.length; i++) {
			if (!startNodes[i]) {
				getRoutes(gtasks.get(i)).forEach(list ->
					System.out.println(printRoute(list))
				);
			}
		}
	}
	
	private static String printRoute (LinkedList<Task> list) {
		StringBuilder sb = new StringBuilder(list.get(0).print());
		for (int i = 0; i < list.size() - 1; i++) {
			Task.Link link = list.get(i).findNext(list.get(i + 1));
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
	
	private static void createTasks2 (int tasksCount, int minWegth, int maxWeight, double corellation, int minLink, int maxLink) { //corellation = nodes / (nodes + links)
		
		for (int i = 0; i < tasksCount; i++) {
			gtasks.add(new Task(rand.nextInt(maxWeight - minWegth) + minWegth));
		}
		
		int from = 0, to = 0;
		
		while (calculateCorrelation() > corellation) {
			from = rand.nextInt(tasksCount);
			to = rand.nextInt(tasksCount);
			
			System.out.println("trying add link " + from + " -> " + to);
			
			gtasks.get(from).addNext(gtasks.get(to), rand.nextInt(maxLink - minLink) + minLink);
			
			if (!checkTasks()) {
				gtasks.get(from).remove(gtasks.get(to));
				System.out.println("error cycle link " + from + " -> " + to);
			}
			
			
		}
		while (calculateCorrelation() < corellation) {
			System.out.println("here");
			gtasks.get(from).addNext(gtasks.get(to), -1);
		}
		
	}
	
	private static double calculateCorrelation () {
		
		int linkSum = 0, taskSum = 0;
		
		for (Task task : gtasks) {
			taskSum += task.getWeight();
			for (Task.Link link : task.next) {
				linkSum += link.weight;
			}
		}
		
		return (taskSum + 0.0) / (taskSum + linkSum);
	}
	
	private static void createTasks () {
		gtasks.add(new Task(2));
		gtasks.add(new Task(5));
		gtasks.add(new Task(7));
		gtasks.add(new Task(1));
		gtasks.add(new Task(1));
		
		nextTask(0, 4);
		nextTask(1, 4);
		nextTask(2, 4, 2);
		nextTask(3, 4, 10);
		
	}
	
	private static boolean checkTasks () {
		boolean res = true;
		for (Task task : gtasks) {
			res = res & innerRecursive(task, 0);
		}
		return res;
	}
	
	private static boolean findCyclesByWarshell () {
		int steps[][] = new int[gtasks.size()][gtasks.size()];
		for (int i = 0; i < gtasks.size(); i++)
			for (int j = 0; j < gtasks.size(); j++)
				steps[i][j] = 0;
		
		gtasks.forEach(task -> task.next.forEach(link ->
			steps[task.id][link.link.id] = 1
		));
		for (int i = 0; i < gtasks.size(); i++) {
			for (int j = 0; j < gtasks.size(); j++) {
				for (int k = 0; k < gtasks.size(); k++) {
					if (steps[i][j] != 0 && steps[j][k] != 0) {
						steps[j][k] = steps[i][j] + steps[j][k];
					}
				}
			}
		}
		
		for (int i = 0; i < gtasks.size(); i++) {
			System.out.println();
			for (int j = 0; j < gtasks.size(); j++) {
				System.out.print((steps[i][j] < 1000) ? steps[i][j] + "\t" : "*\t");
			}
		}
		
		return false;
	}
	
	private static boolean innerRecursive (Task cur, int step) {
		if (step > gtasks.size()) {
			return false;
		}
		boolean flag = true;
		for (Task.Link next : cur.next) {
			flag = flag & innerRecursive(next.link, step + 1);
		}
		return flag;
	}
	
	private static void createLinks () {
		Core.id_gen = 0;
		for (int i = 0; i < 8; i++) {
			cores.add(new Core());
		}
		
		link(0, 1);
		link(0, 3);
		link(0, 7);
		link(1, 2);
		link(1, 4);
		link(1, 5);
		link(2, 6);
		link(2, 7);
		link(3, 4);
		link(3, 5);
		link(3, 6);
		link(4, 5);
		link(4, 7);
		link(5, 6);
		link(6, 7);
		
		
		connMatrix = new int[cores.size()][cores.size()];
		for (Core core : cores) {
			for (Core c2 : core.links) {
				connMatrix[core.id][c2.id] = 1;
				connMatrix[c2.id][core.id] = 1;
			}
		}
		
		for (int i = 0; i < connMatrix.length; i++) {
			for (int j = 0; j < connMatrix.length; j++) {
				for (int k = 0; k < connMatrix.length; k++) {
					if (connMatrix[i][j] != 0 && connMatrix[j][k] != 0) {
						if (connMatrix[i][k] == 0) {
							connMatrix[i][k] = connMatrix[i][j] + connMatrix[j][k];
						} else {
							connMatrix[i][k] = Math.min(connMatrix[i][k], connMatrix[i][j] + connMatrix[j][k]);
						}
						connMatrix[k][i] = connMatrix[i][k];
					}
				}
			}
			connMatrix[i][i] = 0;
		}
		
		for (int[] arr : connMatrix) {
			for (int i : arr) {
				System.out.print(i + "\t");
			}
			System.out.println();
		}
		
		//create links
//		for (int i = 0; i < 8; i++) {
//			link(i, i + 1);
//		}
	}
	
	private static boolean checkLinks () {
		boolean[] conns = new boolean[cores.size()]; //determines can we get core[i] via core[0]
		conns[0] = true;
		for (int i = 0; i < cores.size() - 1; i++) {
			cores.stream()
				.filter(core -> conns[core.id]) //filters procs that live now
				.forEach(core -> core.links.forEach(link -> {
					if (!conns[link.id]) {
						conns[link.id] = true;
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
		gtasks.get(a).addNext(gtasks.get(b));
	}
	
	private static void nextTask (int a, int b, int weight) {
		gtasks.get(a).addNext(gtasks.get(b), weight);
	}
	
}
