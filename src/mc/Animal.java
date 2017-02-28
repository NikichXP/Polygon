package mc;

public abstract class Animal implements Eatable {

	protected static int foodChainLevel;
	protected String title;

	public boolean eat(Eatable e) {
		if (this.getFoodChainLevel() > e.getFoodChainLevel()) {
			System.out.println(this.getTitle() + " eaten " + e.getTitle());
			return true;
		} else {
			System.out.println(this.getTitle() + " can't eat " + e.getTitle());
			return false;
		}
	}
	@Override
	public int getFoodChainLevel() {
		return foodChainLevel;
	}

	@Override
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
