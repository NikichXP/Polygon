package midnigt2;

public class Main {

	public static void main(String[] args) {
		Cow c = new Cow();
		Grass g = new Grass();
		c.eat(g);

		Lion l = new Lion();
		l.eat(c);
//		l.eat(g);  //error
	}

}
