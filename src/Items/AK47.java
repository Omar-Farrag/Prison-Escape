package Items;

import Music.DJ;
import Music.Track;
import Strategies.NoEffect;

public class AK47 extends Gun {

    public AK47() {
        super("AK-47", new NoEffect());
    }

    @Override
    protected Thread playSound() {
        return DJ.getInstance().play(Track.AK47, false, false);

    }

}
