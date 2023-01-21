package States;

import Locations.*;
import Music.DJ;
import General.UserLocation;
import IO.Printer;

public class PermittedToEnter implements EntryPermissionState {
    @Override
    public void next(Location l) {
        if (l.needsToWorkHereFirst() || l.outsideWorkingHours() || !l.goodEnoughToEnter()) {
            l.setEntryPermissionState(new NotPermittedToEnter());
            updateReasonCantEnter(l);
        } else
            l.setEntryPermissionState(this);

    }

    @Override
    public void updateReasonCantEnter(Location l) {
        if (l.needsToWorkHereFirst())
            l.setReasonCantEnter("Location Locked. Visit the warden and ask for a job here first");
        else if (l.outsideWorkingHours())
            l.setReasonCantEnter("This location is now closed. It opens at " + l.getOpeningTime() + " and closes at "
                    + l.getClosingTime());
        else {
            l.setReasonCantEnter("You're not good enough to enter this room. Come back when you're reformed");
            if (l.isCurrentlyEmployed()) {
                l.setCurrentlyEmployed(false);
                Printer.println(
                        "Guess what? The Warden caught wind of your recent behavior and has revoked your job at the "
                                + l.getLocationName() + "...enjoy unemployment loser....");
                UserLocation.getInstance().setHasAJob(false);
                UserLocation.getInstance().setEmploymentLocation(null);
            }
        }

    }

    @Override
    public void kickOutOfLocation() {
        // NO NEED TO KICK HIM OUT OF LOCATION
    }

    @Override
    public void enterLocation(Location newLocation) {
        UserLocation ul = UserLocation.getInstance();

        if (ul.isCurrentlyConversating()) {
            ul.getTalkingWith().callMeWhenDone(() -> {
                enterLocation(newLocation);
            });
        } else if (ul.getCurrentLocation().equals(newLocation)) {
            // Printer.println("You are already at the " + newLocation.getLocationName());
        } else if (ul.getCurrentLocation().isAllowedToLeave()) {
            DJ.getInstance().stop();
            ul.setCurrentLocation(newLocation);
            ul.setNextPossibleLocations(ul.getDd().getNextPossibleLocations(newLocation));
            Printer.println("You have entered " + ul.getCurrentLocation().getLocationName());
            DJ.getInstance().play(newLocation.getLocationTrack(), true, newLocation.isLoopLocationTrack());
            ul.setVisitationState(new VisitedState());
            if (newLocation.getLocationName().equals(LocationNames.OPEN_YARD.getName())) {
                OpenYard openYard = (OpenYard) newLocation;
                openYard.getJumped();
            }

        } else
            Printer.println("You are currently not allowed to leave this room: "
                    + ul.getCurrentLocation().getReasonCantLeave());

    }

    @Override
    public String toString() {
        return "PERMISSION GRANTED";
    }
}
