package States;

import Locations.*;

public interface VisitationState {
    public void next(Location l);

    public void fastTravel(Location newLocation);
}
