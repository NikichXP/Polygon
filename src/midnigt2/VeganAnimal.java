package midnigt2;

public abstract class VeganAnimal {

	public void eat(Green food) {
		System.out.println(this.getClass().getSimpleName() + " eaten " + food.getClass().getSimpleName());
	}

}
