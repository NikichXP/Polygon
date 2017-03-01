package midnight2;

public abstract class Predator {

	void eat (VeganAnimal a) {
		System.out.println(this.getClass().getSimpleName() + " eaten " + a.getClass().getSimpleName());
	}

}
