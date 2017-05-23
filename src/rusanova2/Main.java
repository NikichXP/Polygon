package rusanova2;

import java.util.ArrayList;
import java.util.List;

public class Main {
	
	static List<Core> cores = new ArrayList<>();
	static List<Task> tasks = new ArrayList<>();
	
	public static void main (String[] args) {
		
		createLinks();
//		System.out.println(checkLinks());
		
		createTasks();
		System.out.println(checkTasks());
		
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
		for (Task next : cur.next) {
			flag = flag & innerRecursive(next, step + 1);
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
