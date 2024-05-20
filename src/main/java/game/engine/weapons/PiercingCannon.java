package game.engine.weapons;

import java.util.*;

import game.engine.titans.*;

public class PiercingCannon extends Weapon {
	public final static int WEAPON_CODE = 1;

	public PiercingCannon(int baseDamage) {
		super(baseDamage);
	}

	@Override
public int turnAttack(PriorityQueue<Titan> laneTitans) {
    int defeatedCounter = 0;
    int resources = 0;
    List<Titan> tempTitans = new ArrayList<>();
    // Poll the first five Titans from the PriorityQueue
    for (int i = 0; i < 5 && !laneTitans.isEmpty(); i++) {
        tempTitans.add(laneTitans.poll());
    }
    // Attack the Titans in the temporary list
    for (Titan titan : tempTitans) {
        if (titan != null) {
            resources += attack(titan);
            if (titan.isDefeated()) {
                defeatedCounter++;
            } else {
                // Add the Titan back to the PriorityQueue if it's not defeated
                laneTitans.add(titan);
            }
        }
    }
    return resources;
}







}