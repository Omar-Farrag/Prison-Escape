package States;

public class EntryPermissionContext {
    EntryPermissionState state;

    public EntryPermissionContext(EntryPermissionState state) {
        setState(state);
    }

    public synchronized EntryPermissionState getState() {
        return state;
    }

    public synchronized void setState(EntryPermissionState state) {
        this.state = state;
    }
}
