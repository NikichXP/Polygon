package midnight2.predators;

import midnight2.VeganAnimal;

public abstract class Predator {

	public void eat(VeganAnimal food) {
		eatAction(food);
	}

	void eatAction(Object a) {
		System.out.println(this.getClass().getSimpleName() + " eaten " + a.getClass().getSimpleName());
	}

}
