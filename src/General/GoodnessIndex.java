package General;

import Constants.GoodnessLevel;
import IO.Printer;
import ObserversAndSubjects.ConcreteSubject;
import ObserversAndSubjects.Message;
import ObserversAndSubjects.Topic;

public class GoodnessIndex extends ConcreteSubject implements Runnable, ThreadTermination {

    private boolean isGood;
    private boolean stop;
    private Thread t;

    private int goodnessIndex;
    private static GoodnessIndex instance;

    private GoodnessIndex() {
        goodnessIndex = 0;
        isGood = false;
        t = new Thread(this);
        t.setName("GoodnessIndex thread");
        t.start();
    }

    public void run() {
        while (!stop) {
            try {
                int count = 0;
                while (count <= 30 && !stop) {
                    Thread.sleep(1000);
                    count++;
                }
                if (goodnessIndex != 100)
                    increaseGoodnessIndex(1);
                if (!isGood && goodnessIndex >= GoodnessLevel.minToBeGOOD.getLevel()) {
                    isGood = true;
                    publishMessage(new Message(this, Topic.GOODNESS, isGood));
                } else if (isGood && goodnessIndex <= GoodnessLevel.maxToBeBAD.getLevel()) {
                    isGood = false;
                    publishMessage(new Message(this, Topic.GOODNESS, isGood));
                }
            } catch (Exception e) {
                Printer.println(e.getMessage());
            }

        }
    }

    public int getGoodnessIndex() {
        return goodnessIndex;
    }

    public static synchronized GoodnessIndex getInstance() {
        if (instance == null)
            return instance = new GoodnessIndex();
        return instance;
    }

    public synchronized void increaseGoodnessIndex(int amount) {
        if (goodnessIndex + amount >= 100)
            goodnessIndex = 100;
        else
            goodnessIndex += amount;

        if (!isGood && goodnessIndex >= GoodnessLevel.minToBeGOOD.getLevel()) {
            isGood = true;
            publishMessage(new Message(this, Topic.GOODNESS, isGood));
        }

    }

    public synchronized void decreaseGoodnessIndex(int amount) {
        if (goodnessIndex - amount <= -100)
            goodnessIndex = -100;
        else
            goodnessIndex -= amount;

        if (isGood && goodnessIndex <= GoodnessLevel.maxToBeBAD.getLevel()) {
            isGood = false;
            publishMessage(new Message(this, Topic.GOODNESS, isGood));
        }
    }

    @Override
    public void killThread() {
        stop = true;

    }

}
