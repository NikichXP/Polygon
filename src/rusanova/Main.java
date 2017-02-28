package rusanova;

import java.util.*;
import java.util.stream.IntStream;

public class Main {

	static int s = 0;

	static int rank = 5;

	public static void main(String[] args) {
		List<Node> nodeList = new ArrayList<>();

		IntStream.range(0,6).forEach(i -> nodeList.add(new Node()));

		for (int i = 1; i < 7; i++) {
			nodeList.get(i % 6).links.add(nodeList.get((i - 1) % 6));
			nodeList.get(i % 6).links.add(nodeList.get((i + 1) % 6));
		}

		// end lvl 1

		for (int level = 1; level < rank; level++) {
			int lim = nodeList.size();
			for (int i = 0; i < lim; i++) {
				if (nodeList.get(i).links.size() < 3) {
					List<Node> newNodes = gen6();
					nodeList.get(i).links.add(newNodes.get(0));
					newNodes.get(0).links.add(nodeList.get(i));
					nodeList.addAll(newNodes);
				}
			}
		}

		System.out.println("Ранг/уровень: " + rank);

		System.out.println("Количество вершин:" + nodeList.size());

		nodeList.forEach(node -> {
			if (node.links.size() > s) {
				s = node.links.size();
			}
		});


		System.out.println("Степень: " + s);
		System.out.println("Диаметр: " + (rank * 6 - 3));

		int[][] matrix = avgDiametr(nodeList);
		System.out.println("Средний диаметр: " + getDs(matrix));
		System.out.println("Топологический трафик: " + (2 * getDs(matrix) / s));
	}

	private static double getDs(int[][] minDistances) {
		double ds = 0.0;
		for (int i = 0; i < minDistances.length; i++) {
			for (int j = 0; j < minDistances[i].length; j++) {
				ds += minDistances[i][j];
			}
		}
		int n = minDistances.length;
		ds /= ((n - 1) * n);
		return ds;
	}


	private static int[][] avgDiametr(List<Node> nodeList) {

		int[][] m = new int[nodeList.size()][nodeList.size()];
		Node[] nodes = new Node[nodeList.size()];
		nodes = nodeList.toArray(nodes);

		for (int i = 0; i < nodes.length; i++) {
			for (int j = 0; j < nodes.length; j++) {
				m[i][j] = -1;
			}
			m[i][i] = 0; // this
		}

		nodeList.forEach(node -> {
			node.links.forEach(item -> {
				m[node.id][item.id] = 1;
				m[item.id][node.id] = 1;
			});
		});

		while (!checkMatrix(m)) {
			for (int i = 0; i < m.length; i++) {
				for (int j = 0; j < m[i].length; j++) {
					if (m[i][j] > 0) {
						for (int k = 0; k < m[j].length; k++) {
							if (m[j][k] == 1) {
								if (m[i][k] < 0) {
									m[i][k] = m[i][j] + 1;
									m[k][i] = m[i][j] + 1;
								}
							}
						}
					}
				}
			}
		}

//		for (int[] arr : m) {
//			for (int i : arr) {
//				System.out.print((i > 9) ? i + " " : (i > 0) ? " " + i + " " : " * ");
//			}
//			System.out.println();
//		}

		return m;

	}

	private static boolean checkMatrix(int[][] m) {

		for (int[] arr : m) {
			for (int i : arr) {
				if (i < 0) {
					return false;
				}
			}
		}

		return true;

	}

	public static List<Node> gen6 () {
		List<Node> nodeList = new ArrayList<>();

		IntStream.range(0,6).forEach(i -> nodeList.add(new Node()));

		for (int i = 1; i < 7; i++) {
			nodeList.get(i % 6).links.add(nodeList.get((i - 1) % 6));
			nodeList.get(i % 6).links.add(nodeList.get((i + 1) % 6));
		}

		return nodeList;
	}


}
