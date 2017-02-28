package mdntcdrs;

public class Human extends Animal {

	static {
		foodChainLevel = 5;
	}

	public Human () {
		this.title = "Human";
	}

	public Human (String title) {
		this.title = title;
	}
}
