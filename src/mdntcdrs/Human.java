package mdntcdrs;

public class Human extends Animal {

	{
		foodChainLevel = 5;
		kindOf = MealKind.HUMANOID;
	}

	private boolean isVegetarian;

	public Human () {
		this.title = "Human";
	}

	public Human (String title) {
		this.title = title;
	}

	public boolean isVegetarian() {
		return isVegetarian;
	}

	public void setVegetarian(boolean vegetarian) {
		isVegetarian = vegetarian;
	}

	@Override
	public boolean eat(Eatable e) {
		if (isVegetarian) { //actually can be another class?
			if (e.kindOf == MealKind.FRUIT || e.kindOf == MealKind.VEGETABLE) {
				return super.eat(e);
			} else {
				System.out.println(this.getTitle() + " is vegetarian =(. can't eat " + e.getTitle());
				return false;
			}
		}
		return super.eat(e);
	}
}
