package game.engine.weapons;

import game.engine.titans.Titan;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class VolleySpreadCannon extends Weapon {
	public final static int WEAPON_CODE = 3;
	private final int minRange;
	private final int maxRange;

	public int getMinRange() {
		return minRange;
	}

	public int getMaxRange() {
		return maxRange;
	}

	public VolleySpreadCannon(int baseDamage, int minRange, int maxRange) {
		super(baseDamage);
		this.maxRange = maxRange;
		this.minRange = minRange;
	}

	@Override
	public int turnAttack(PriorityQueue<Titan> laneTitans){
		int resources = 0;
		ArrayList<Titan> titansList = new ArrayList<Titan>(laneTitans);
		for(int i=0; i<titansList.size(); i++){
			Titan titan = titansList.get(i);
			if(titan.getDistance() <= maxRange && titan.getDistance() >= minRange){
				resources += attack(titan);
				if(titan.isDefeated())
					laneTitans.remove(titan);
			}
		}
		return resources;
	}

}
