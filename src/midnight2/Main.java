package midnight2;

import midnight2.predators.Lion;
import midnight2.predators.Wolf;

public class Main {

	public static void main(String[] args) {
		Cow c = new Cow();
		Grass g = new Grass();
		c.eat(g);

		Lion l = new Lion();
		l.eat(c);

		Wolf w = new Wolf();

		w.eat(l);
//		w.eat(w);  //error
//		l.eat(g);  //error
	}

}
