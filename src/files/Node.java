package files;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

public class Node implements Serializable {
	private long size;
	private ArrayList<Node> dir;
	private ArrayList<File> files;
	private int filesCount;
	private String name;

	public Node() {}

	public Node(File f) {
		this.size = 0;
		this.name = f.getAbsolutePath();
		dir = new ArrayList<>();
		files = new ArrayList<>();
		try {
			System.out.println(f.getCanonicalPath());
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			for (File fl : f.listFiles()) {
				if (fl.isDirectory()) {
					Node n = new Node(fl);
					dir.add(n);
					this.size += n.size;
					this.filesCount+= n.getFilesCount();
				} else {
					this.size += fl.length();
					this.filesCount++;
				}
			}
		} catch (Exception e) {
		}
	}

	public long getSize() {
		return size;
	}

	public ArrayList<Node> getDir() {
		return dir;
	}

	public void setDir(ArrayList<Node> dir) {
		this.dir = dir;
	}

	public ArrayList<File> getFiles() {
		return files;
	}

	public void setFiles(ArrayList<File> files) {
		this.files = files;
	}

	public int getFilesCount() {
		return filesCount;
	}


	public String getName() {
		return name;
	}
}