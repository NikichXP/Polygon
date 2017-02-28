package mdntcdrs;

import java.util.Arrays;

public abstract class Animal extends Eatable {

	public static MealKind[] eats = null;

	public boolean eat(Eatable e) {
		if (this.getFoodChainLevel() > e.getFoodChainLevel()) {
			if (Arrays.asList(this.eats).contains(e.kindOf)) {
				System.out.println(this.getTitle() + " eaten " + e.getTitle());
				return true;
			}
		}
		System.out.println(this.getTitle() + " can't eat " + e.getTitle());
		return false;

	}


}
