package rusanova2;

import lombok.Data;

import java.util.LinkedList;

@Data
public class Core {
	
	public static int id_gen = 0;
	public LinkedList<Core> links = new LinkedList<>();
	public int id;
	
	public Core () {
		id = id_gen++;
	}
	
	public void addLink (Core other) {
		if (this.links.contains(other)) {
			return;
		}
		this.links.add(other);
		other.addLink(this);
	}
	
	public String toString() {
		return "{ id : " + id + ", links : [" + links.stream().map(link -> link.getId() + "").reduce((l1, l2) -> l1 + ", " + l2).orElse("") + "]}";
	}
	
}