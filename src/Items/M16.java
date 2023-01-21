package Items;

import Music.DJ;
import Music.Track;
import Strategies.NoEffect;

public class M16 extends Gun {

    public M16() {
        super("M16", new NoEffect());
    }

    @Override
    protected Thread playSound() {
        return DJ.getInstance().play(Track.M16, false, false);

    }

}