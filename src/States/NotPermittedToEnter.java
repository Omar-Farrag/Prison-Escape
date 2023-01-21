package States;

import Locations.*;
import Music.DJ;
import Music.Track;
import General.UserLocation;
import IO.Printer;

public class NotPermittedToEnter implements EntryPermissionState {

    @Override
    public void next(Location l) {
        if (l.needsToWorkHereFirst() || l.outsideWorkingHours() || !l.goodEnoughToEnter()) {
            l.setEntryPermissionState(this);
            updateReasonCantEnter(l);
        } else
            l.setEntryPermissionState(new PermittedToEnter());

    }

    @Override
    public void updateReasonCantEnter(Location l) {
        if (l.needsToWorkHereFirst())
            l.setReasonCantEnter("Location Locked. Visit the warden and ask for a job here first");
        else if (l.outsideWorkingHours())
            l.setReasonCantEnter("This location is now closed. It opens at " + l.getOpeningTime() + " and closes at "
                    + l.getClosingTime());
        else
            l.setReasonCantEnter("You're not good enough to enter this room. Come back when you're reformed");

    }

    @Override
    public void kickOutOfLocation() {
        Printer.println("This location has just shut down. Only makes sense to go back to your cell");
        UserLocation.getInstance().fastTravel(LocationNames.CELL.getName());
    }

    @Override
    public void enterLocation(Location newLocation) {
        DJ.getInstance().play(Track.DENIED, false, false);
        Printer.println("You are not allowed to enter this room: " + newLocation.getReasonCantEnter());
    }

    @Override
    public String toString() {
        return "PERMISSION DENIED";
    }
}
