package mc;

public class Main {

	public static void main(String[] args) {

		Eatable grass = new Eatable() {
			@Override
			public int getFoodChainLevel() {
				return 0;
			}

			@Override
			public String getTitle() {
				return "Grass";
			}
		};

		Animal animals[] = {new Cow(), new Lion(), new Human("Dave")};

		animals[0].eat(grass);
		animals[0].eat(animals[1]);

	}

}
