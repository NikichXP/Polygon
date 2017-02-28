package rusanova;

import java.util.LinkedList;

public class Node {

	public LinkedList<Node> links;
	public static int id_gen = 0;
	public int id;

	public Node () {
		links = new LinkedList<>();
		id = id_gen++;
	}

}
