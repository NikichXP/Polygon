package mdntcdrs;

public class Main {

	public static void main(String[] args) {

		FoodRationConfigurer.configureFoodRation();

		Eatable grass = new Vegetable();

		Animal animals[] = {new Cow(), new Lion(), new Human("Dave")};

		Animal e1 = new Ktulhu();

		animals[0].eat(grass);
		animals[1].eat(animals[0]);
		animals[2].eat(animals[1]);
		e1.eat(grass);

	}

}
