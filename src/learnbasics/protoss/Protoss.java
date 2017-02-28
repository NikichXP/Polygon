package learnbasics.protoss;

import learnbasics.CombatUnit;
import learnbasics.XelNaga;

public interface Protoss extends XelNaga, CombatUnit {

	int shield = 0;

	boolean connectToKhala();

}
