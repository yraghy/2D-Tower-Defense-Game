package game.engine.weapons;

import game.engine.titans.Titan;
import java.util.PriorityQueue;

public class WallTrap extends Weapon {
	public final static int WEAPON_CODE = 4;

	public WallTrap(int baseDamage) {
		super(baseDamage);
	}

	@Override
	public int turnAttack(PriorityQueue<Titan> laneTitans) {
        if (laneTitans.isEmpty())
            return 0;

        int resources = 0;
        Titan titan = null;

        if (laneTitans.peek() != null)
			titan = laneTitans.peek();

        if (titan.getDistance() > 0)
            return 0;

        resources += attack(titan);

        if (titan.isDefeated())
            laneTitans.poll();

        return resources;
    }

}
