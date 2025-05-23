package game.engine.titans;
import game.engine.interfaces.*;

public class ArmoredTitan extends Titan {
	public final static int TITAN_CODE = 3;

	public ArmoredTitan(int baseHealth, int baseDamage, int heightInMeters, int distanceFromBase, int speed,
			int resourcesValue, int dangerLevel) {
		super(baseHealth, baseDamage, heightInMeters, distanceFromBase, speed, resourcesValue, dangerLevel);
	}

	@Override
	public int takeDamage(int damage) {
		damage *= (double) 0.25;
		setCurrentHealth(getCurrentHealth() - damage);
		if (isDefeated()){
			setCurrentHealth(0);
			return getResourcesValue();
		}
		return 0;
	}

}
