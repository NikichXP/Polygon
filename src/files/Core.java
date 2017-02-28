package files;

import java.io.*;
import java.util.*;

public class Core {

	public static LinkedList<byte[]> data = new LinkedList<>();
	public static LinkedList<Integer> size = new LinkedList<>();
	public static LinkedList<String> names = new LinkedList<>();
	public static Boolean end = false;
	public static int clusterSize = 64; //4KB

	public static void main(String[] args) throws IOException {

		/*
		 * If we copy all data from C:\foo dir to D:\foo, we may use it like "headers" in names.
		 */
		Thread loader = new Thread(() -> {
			File folder = new File("C:\\foo");
			try {
				for (File readFile : folder.listFiles()) {
					names.add(readFile.getCanonicalPath().substring(folder.getCanonicalPath().length()));
					FileInputStream inputStream = new FileInputStream(readFile);
					byte[] read = new byte[clusterSize];
					int res;
					do {
						res = inputStream.read(read);
						data.add(read);
						size.add(res);
					} while (res == clusterSize);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println("END1");
			synchronized (end) {
				end = false;
			}
		});
		Thread saver = new Thread(() -> {
			File folder = new File("D:\\foo");
			if (!folder.exists()) {
				folder.mkdir();
			}
			boolean end;
			synchronized (Core.end) {
				end = Core.end;
			}
			byte[] get;
			while (end == false) {
				get = data.poll();
				while (get != null) {
					System.out.println(get);
					get = data.poll();
				}
				synchronized (Core.end) {
					end = Core.end;
				}
			}
			System.out.println("END2");
		});
//		loader.start();
//		saver.start();

	}

	public static void analyzer(String dir) {
		Node[] nodes = new Node[0];
		try {
			nodes = scan(dir);
			saveData(nodes, dir);
		} catch (IOException e) {
			e.printStackTrace();
		}
		for (Node node : nodes) {
			System.out.println(node.getName() + "\t\t" + node.getFilesCount() + "\t\t" + (node.getSize() / (1024 * 1024D)));
		}
	}

	public static void saveData(Node[] n, String dir) throws IOException {
		StringBuilder sb = new StringBuilder();
		for (char c : dir.toCharArray()) {
			if (Character.isLetterOrDigit(c)) {
				sb.append(c);
			}
		}
		File save = new File("systemFSscan" + dir + ".dat");
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(save));
		oos.writeObject(n);

	}

	public static Node[] scan(String dir) throws IOException {
		File[] root = new File(dir).listFiles();
		Node[] n = new Node[root.length];
		int i = 0;
		for (File f : root) {
			if (f.isDirectory()) {
				n[++i] = new Node(f);
			}
		}

		HashSet<Node> set = new HashSet<>(Arrays.asList(n));
		set.remove(null);
		n = new Node[set.size()];
		return set.toArray(n);
	}
}
