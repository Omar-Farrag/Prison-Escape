package States;

public class VisitationContext {

    private VisitationState state;

    public VisitationContext() {
        this.setState(new NotVisitedState());
    }

    public void setState(VisitationState state) {
        this.state = state;
    }

    public VisitationState getState() {
        return state;
    }

}
