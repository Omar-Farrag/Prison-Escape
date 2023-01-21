package Items;

import Music.DJ;
import Music.Track;
import Strategies.KillsPlayer;

public class RPG extends Gun {

    public RPG() {
        super("RPG", new KillsPlayer());
    }

    @Override
    protected Thread playSound() {
        return DJ.getInstance().play(Track.RPG, false, false);

    }

}