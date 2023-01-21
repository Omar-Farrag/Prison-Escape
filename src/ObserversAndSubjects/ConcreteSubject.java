package ObserversAndSubjects;

import java.util.ArrayList;

public abstract class ConcreteSubject implements Subject {

    private ArrayList<Observer> observers;

    protected ConcreteSubject() {
        observers = new ArrayList<>();
    }

    @Override
    public void registerObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    @Override
    public void publishMessage(Message m) {
        for (Observer o : observers)
            o.update(m);
    }
}
