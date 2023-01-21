package Strategies;

import General.UserLocation;
import Locations.Armory;

public class KillsPlayer implements WeaponEffectStrategy {

    @Override
    public void affect() {
        UserLocation.getInstance().getHealthBar().decreaseHealthBy(100);
        Armory.setInsideArmory(false);
        Armory.setGuardsAsleep(false);
    }

}
