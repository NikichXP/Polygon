package learnbasics.zerg;

import learnbasics.CombatUnit;
import learnbasics.XelNaga;

public abstract class Zerg implements XelNaga, CombatUnit {

	boolean isHidden = false;

	public boolean burrow() {
		this.isHidden = !isHidden;
		return isHidden;
	}

	public abstract Zerg evolve(String arg);

	@Override
	public int attack(CombatUnit target) {
		return 0;
	}

	@Override
	public int defend(CombatUnit attacker) {
		return 0;
	}
}
