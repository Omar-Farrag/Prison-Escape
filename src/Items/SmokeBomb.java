package Items;

import Music.DJ;
import Music.Track;
import Strategies.inToxicatesPlayer;

public class SmokeBomb extends Projectile {

    public SmokeBomb() {
        super("Smoke Bomb", new inToxicatesPlayer());
    }

    @Override
    protected Thread playSound() {
        return DJ.getInstance().play(Track.SMOKE_BOMB, false, false);
    }

}
