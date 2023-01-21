package ObserversAndSubjects;

public abstract class ConcreteObserver implements Observer {

    private Subject[] subjects;

    public ConcreteObserver(Subject[] subjects) {
        this.subjects = subjects;
        for (Subject s : subjects)
            s.registerObserver(this);
    }

    protected Subject[] getSubjects() {
        return subjects;
    }

    public abstract void update(Message m);
}
