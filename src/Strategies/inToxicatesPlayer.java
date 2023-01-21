package Strategies;

import General.UserLocation;
import IO.Printer;
import Music.DJ;
import Music.Track;

public class inToxicatesPlayer implements WeaponEffectStrategy {

    @Override
    public void affect() {
        Printer.println("The gas slowly makes its way through your mouth nostrils...It's toxicity makes you go... ");
        DJ.getInstance().play(Track.COUGH, false, false);
        UserLocation.getInstance().getHealthBar().decreaseHealthBy(50);
    }

}
