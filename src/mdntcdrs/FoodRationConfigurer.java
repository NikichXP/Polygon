package mdntcdrs;

public class FoodRationConfigurer {

	public static void configureFoodRation () {
		Cow.eats = new MealKind[]{MealKind.GRASS, MealKind.VEGETABLE};
		Eagle.eats = new MealKind[]{MealKind.REPTILE, MealKind.BIRD};
		Human.eats = new MealKind[]{MealKind.BIRD, MealKind.VEGETABLE, MealKind.FRUIT, MealKind.MAMMAL};
		Lion.eats = new MealKind[]{MealKind.BIRD, MealKind.MAMMAL, MealKind.HUMANOID};
		Worm.eats = new MealKind[]{MealKind.GRASS, MealKind.FRUIT};
		Ktulhu.eats = new MealKind[]{MealKind.BIRD, MealKind.CAT, MealKind.HUMANOID, MealKind.MAMMAL, MealKind.VEGETABLE};
	}

}
