package game.engine;

import java.io.*;
import java.util.*;

import game.engine.*;
import game.engine.base.Wall;
import game.engine.dataloader.DataLoader;
import game.engine.exceptions.InsufficientResourcesException;
import game.engine.exceptions.InvalidLaneException;
import game.engine.lanes.Lane;
import game.engine.titans.Titan;
import game.engine.titans.TitanRegistry;
import game.engine.weapons.factory.FactoryResponse;
import game.engine.weapons.factory.WeaponFactory;
import game.engine.weapons.*;

import static java.util.Collections.sort;

public class Battle {

	private final static int[][] PHASES_APPROACHING_TITANS =
		{		{ 1, 1, 1, 2, 1, 3, 4 },
				{ 2, 2, 2, 1, 3, 3, 4 },
				{ 4, 4, 4, 4, 4, 4, 4 } };

	private final static int WALL_BASE_HEALTH =10000 ;
	private final WeaponFactory weaponFactory;
	private final HashMap<Integer, TitanRegistry> titansArchives;
	private final ArrayList<Titan> approachingTitans;
	private	final PriorityQueue<Lane> lanes;
	private final ArrayList<Lane> originalLanes;
	private int numberOfTurns;
	private int resourcesGathered;
	private BattlePhase battlePhase;
	private int numberOfTitansPerTurn;
	private int score;
	private int titanSpawnDistance;


	public int getNumberOfTurns() {
		return numberOfTurns;
	}

	public void setNumberOfTurns(int numberOfTurns) {
		this.numberOfTurns = numberOfTurns;
	}

	public int getResourcesGathered() {
		return resourcesGathered;
	}

	public void setResourcesGathered(int resourcesGathered) {
		this.resourcesGathered = resourcesGathered;
	}

	public BattlePhase getBattlePhase() {
		return battlePhase;
	}

	public void setBattlePhase(BattlePhase battlePhase) {
		this.battlePhase = battlePhase;
	}

	public int getNumberOfTitansPerTurn() {
		return numberOfTitansPerTurn;
	}

	public void setNumberOfTitansPerTurn(int numberOfTitansPerTurn) {
		this.numberOfTitansPerTurn = numberOfTitansPerTurn;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getTitanSpawnDistance() {
		return titanSpawnDistance;
	}

	public void setTitanSpawnDistance(int titanSpawnDistance) {
		this.titanSpawnDistance = titanSpawnDistance;
	}

	public int[][] getPHASES_APPROACHING_TITANS() {
		return PHASES_APPROACHING_TITANS;
	}

	public int getWALL_BASE_HEALTH() {
		return WALL_BASE_HEALTH;
	}

	public WeaponFactory getWeaponFactory() {
		return weaponFactory;
	}

	public HashMap<Integer, TitanRegistry> getTitansArchives() {
		return titansArchives;
	}

	public ArrayList<Titan> getApproachingTitans() {
		return approachingTitans;
	}

	public PriorityQueue<Lane> getLanes() {
		return lanes;
	}

	public ArrayList<Lane> getOriginalLanes() {
		return originalLanes;
	}

	

	public Battle(int numberOfTurns, int score, int titanSpawnDistance, int initialNumOfLanes,
			int initialResourcesPerLane) throws IOException {
		this.numberOfTurns = numberOfTurns;
		this.score = score;
		this.titanSpawnDistance = titanSpawnDistance;
		this.battlePhase = BattlePhase.EARLY;
		this.numberOfTitansPerTurn = 1;
		resourcesGathered = initialNumOfLanes*initialResourcesPerLane;
		titansArchives = DataLoader.readTitanRegistry();
		approachingTitans = new ArrayList<Titan>();
		lanes = new PriorityQueue<Lane>(Comparator.comparing(Lane::getDangerLevel).reversed());
		originalLanes = new ArrayList<Lane>();
		weaponFactory = new WeaponFactory();
		initializeLanes(initialNumOfLanes);
	
	}

	private void initializeLanes(int numOfLanes) {
		
		for(int i = 0 ; i < numOfLanes;i++) {
			Lane l = new Lane(new Wall(WALL_BASE_HEALTH));
			originalLanes.add(l);
			lanes.add(l);
			
		}
	}

	public void refillApproachingTitans() {
    approachingTitans.clear();
    TitanRegistry titan = null;
    int length = PHASES_APPROACHING_TITANS[battlePhase.ordinal()].length;
    for(int i=0; i<length; i++) {
        titan = titansArchives.get(PHASES_APPROACHING_TITANS[battlePhase.ordinal()][i]);
        approachingTitans.add(titan.spawnTitan(getTitanSpawnDistance()));
    }
}

	public void purchaseWeapon(int weaponCode, Lane lane) throws InsufficientResourcesException, InvalidLaneException {
        if (lane.isLaneLost() || !(lanes.contains(lane)))
            throw new InvalidLaneException("Lane is lost");
        FactoryResponse weaponRes;
        try {
            weaponRes = weaponFactory.buyWeapon(resourcesGathered, weaponCode);
        } catch (InsufficientResourcesException e) {
            try {
                throw new InsufficientResourcesException("Not enoguh resources to buy weapon", e.getResourcesProvided());
            } catch (InsufficientResourcesException ex) {
                throw new RuntimeException(ex);
            }
        }
        Weapon weapon = weaponRes.getWeapon();
		setResourcesGathered(weaponRes.getRemainingResources());
        lane.addWeapon(weapon);
		performTurn();
	}

	public void passTurn() {
		for(int i=0; i<1; i++)
			performTurn();
	}

	private void addTurnTitansToLane() {
    // Refill the approachingTitans list if it's empty
    if (approachingTitans.isEmpty()) {
        refillApproachingTitans();
    }

    // Find the least dangerous lane
    Lane leastDangerousLane = null;
    for (Lane lane : lanes) {
        if (leastDangerousLane == null || lane.getDangerLevel() < leastDangerousLane.getDangerLevel()) {
            leastDangerousLane = lane;
        }
    }

    // Add Titans from approachingTitans to the least dangerous lane
    for (int i = 0; i < numberOfTitansPerTurn; i++) {
        // Refill the approachingTitans list if it's empty during the loop
        if (approachingTitans.isEmpty()) {
            refillApproachingTitans();
        }
        Titan titan = approachingTitans.remove(0); // Use remove(0) for ArrayList
        assert leastDangerousLane != null;
        leastDangerousLane.addTitan(titan);
    }

    // Refill the approachingTitans list if it's empty after adding Titans to the lane
    if (approachingTitans.isEmpty()) {
        refillApproachingTitans();
    }
}

	private void moveTitans() {
		for (Lane lane : lanes) {
			if(!(lane.isLaneLost()))
				lane.moveLaneTitans();
		}
	}

	private int performWeaponsAttacks() {
		int resources = 0;
		ArrayList<Lane> lanesList = new ArrayList<Lane>(lanes);
		for (Lane lane : lanesList) {
			resources += (lane.performLaneWeaponsAttacks());
		}
		score += resources;
		resourcesGathered += resources;
		return resources;
	}

	private int performTitansAttacks() {
    int resources = 0;
    Iterator<Lane> iterator = lanes.iterator();
    while (iterator.hasNext()) {
        Lane lane = iterator.next();
        resources += lane.performLaneTitansAttacks();
        if (lane.isLaneLost()) {
            iterator.remove();
        }
    }
    return resources;
}

	private void updateLanesDangerLevels() {
    List<Lane> tempLanes = new ArrayList<>();
    while (!lanes.isEmpty()) {
        Lane lane = lanes.poll();
        if (lane != null && !lane.isLaneLost()) {
            lane.updateLaneDangerLevel();
            tempLanes.add(lane);
        }
    }
    lanes.addAll(tempLanes);
}

	private void finalizeTurns() {
		numberOfTurns++;

		if (numberOfTurns < 15)
			battlePhase = BattlePhase.EARLY;

		else if (numberOfTurns < 30)
			battlePhase = BattlePhase.INTENSE;

		else if ((numberOfTurns > 29)) {
			if (numberOfTurns > 30 && numberOfTurns % 5 == 0) {
				battlePhase = BattlePhase.GRUMBLING;
				numberOfTitansPerTurn *= 2;
			}
			else{
				battlePhase = BattlePhase.GRUMBLING;
			}
		}

	}

	private void performTurn() {
		moveTitans();
		resourcesGathered += performWeaponsAttacks();
		for (Lane lane : lanes) {
			if (lane != null && !lane.isLaneLost()) {
				resourcesGathered += lane.performLaneTitansAttacks();
			}
		}
		addTurnTitansToLane();
		updateLanesDangerLevels();
		finalizeTurns();
	}

	public boolean isGameOver() {
		boolean flag = true;
		for (Lane lane : lanes) {
			if(!(lane.isLaneLost()))
				flag = false;
		}
		return flag;
	}

}
