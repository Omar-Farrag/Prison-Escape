package States;

import Locations.*;

public class VisitedState implements VisitationState {

    @Override
    public void next(Location l) {
        l.setVisitationState(this);
    }

    @Override
    public void fastTravel(Location newLocation) {
        newLocation.getEntryPermissionState().enterLocation(newLocation);
    }

    @Override
    public String toString() {
        return "VISITED";
    }
}
