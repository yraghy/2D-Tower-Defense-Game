package game.engine.lanes;

import java.util.*;
import game.engine.base.Wall;
import game.engine.titans.Titan;
import game.engine.weapons.*;

public class Lane implements Comparable<Lane> {

	private final PriorityQueue<Titan> titans;
	private final ArrayList<Weapon> weapons;
	private final Wall laneWall;
	private int dangerLevel;

	public Lane(Wall laneWall) {
		this.laneWall = laneWall;
		titans = new PriorityQueue<Titan>();
		weapons = new ArrayList<Weapon>();
		dangerLevel = 0;
	}

	public int getDangerLevel() {
		return dangerLevel;
	}

	public void setDangerLevel(int dangerLevel) {
		this.dangerLevel = dangerLevel;
	}

	public Wall getLaneWall() {
		return laneWall;
	}

	public PriorityQueue<Titan> getTitans() {
		return titans;
	}

	public ArrayList<Weapon> getWeapons() {
		return weapons;
	}

	@Override
	public int compareTo(Lane L) {

		return this.dangerLevel - L.dangerLevel;
	}

	public void addTitan(Titan titan) {titans.add(titan);}

	public void addWeapon(Weapon weapon) {weapons.add(weapon);}

	public void moveLaneTitans() {
		ArrayList<Titan> moveTitans = new ArrayList<Titan>(titans);
		titans.clear();
		for (Titan titan : moveTitans) {
			if(!(titan.hasReachedTarget()))
				titan.move();
		}
		moveTitans.sort(Comparator.comparing(Titan::getDistance));
		titans.addAll(moveTitans);
	}

	public int performLaneTitansAttacks() {
		int resources = 0;
		for (Titan titan : titans) {
			if(titan.hasReachedTarget())
				resources += titan.attack(laneWall);
		}
		return resources;
	}

	public int performLaneWeaponsAttacks() {
		int resources = 0;
		for (Weapon weapon : weapons) {
			resources += weapon.turnAttack(titans);
		}
		return resources;
	}

	public boolean isLaneLost() {
		return laneWall.isDefeated();
	}

	public void updateLaneDangerLevel() {
		dangerLevel = 0;
		for (Titan titan : titans)
			dangerLevel += titan.getDangerLevel();
		setDangerLevel(dangerLevel);
	}

}
