package game.engine.weapons;

import game.engine.titans.Titan;
import java.util.PriorityQueue;

public class SniperCannon extends Weapon {
	public final static int WEAPON_CODE = 2;

	public SniperCannon(int baseDamage) {
		super(baseDamage);
	}

	@Override
	public int turnAttack(PriorityQueue<Titan> laneTitans){
		if(laneTitans.peek() != null){
			Titan titan = laneTitans.peek();
			int resources = attack(titan);
			if(titan.isDefeated()){
				laneTitans.poll();
			}
			return resources;
		}
		return 0;
	}

}
