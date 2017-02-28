package learnbasics;

public interface CombatUnit {

	int attack (CombatUnit target);
	int defend (CombatUnit attacker);
	int getAttack();
	int setAttack();

}
