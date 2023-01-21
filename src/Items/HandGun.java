package Items;

import Music.DJ;
import Music.Track;
import Strategies.NoEffect;

public class HandGun extends Gun {

    public HandGun() {
        super("hand gun", new NoEffect());
    }

    @Override
    protected Thread playSound() {
        return DJ.getInstance().play(Track.HANDGUN, false, false);

    }

}
