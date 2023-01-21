package States;

import Locations.*;
import IO.Printer;

public class NotVisitedState implements VisitationState {

    @Override
    public void next(Location l) {
        l.setVisitationState(new VisitedState());
    }

    @Override
    public void fastTravel(Location newLocation) {
        Printer.println("You can't fast travel to a location unless you have visited it before");
    }

    @Override
    public String toString() {
        return "NOT VISITED";
    }
}
