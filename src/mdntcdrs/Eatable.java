package mdntcdrs;

public abstract class Eatable {

	protected int foodChainLevel;
	protected String title;

	public Eatable() {
		this.title = this.getClass().getSimpleName();
	}

	protected MealKind kindOf;

	public int getFoodChainLevel() {
		return foodChainLevel;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
