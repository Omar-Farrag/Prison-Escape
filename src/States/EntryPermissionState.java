package States;

import Locations.*;

public interface EntryPermissionState {
    public void next(Location l);

    public void enterLocation(Location l);

    public void updateReasonCantEnter(Location l);

    public void kickOutOfLocation();

}
