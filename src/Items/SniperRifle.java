package Items;

import Music.DJ;
import Music.Track;
import Strategies.NoEffect;

public class SniperRifle extends Gun {

    public SniperRifle() {
        super("Sniper Rifle", new NoEffect());
    }

    @Override
    protected Thread playSound() {
        return DJ.getInstance().play(Track.SNIPER_RIFLE, false, false);

    }

}
