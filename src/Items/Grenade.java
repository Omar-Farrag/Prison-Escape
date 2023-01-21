package Items;

import Music.DJ;
import Music.Track;
import Strategies.KillsPlayer;

public class Grenade extends Projectile {

    public Grenade() {
        super("Grenade", new KillsPlayer());
    }

    @Override
    protected Thread playSound() {
        return DJ.getInstance().play(Track.GRENADE, false, false);
    }

}
