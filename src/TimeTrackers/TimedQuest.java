package TimeTrackers;

import IO.Printer;

import java.util.ArrayList;

import General.CBFunc;
import General.ThreadTermination;

public class TimedQuest implements Runnable, ThreadTermination {

    private static ArrayList<ThreadTermination> activeTimedQuests = new ArrayList<>();
    private int seconds;
    private CBFunc cB;
    private boolean stop;// determines whether someone has stopped the timedQuest from outside
    private boolean done;// determines whether the thread finished waiting for the duration or not
    private Thread t;
    static int index = 1;

    public TimedQuest(int numOfSeconds, CBFunc cB) {

        this.seconds = numOfSeconds;
        this.cB = cB;
        stop = false;
        done = false;
        t = new Thread(this);
        if (activeTimedQuests.size() >= 20) {
            terminateActiveTimedQuests();
        }
        activeTimedQuests.add(this);
        t.setName("TIMED_QUEST_THREAD" + index++);
        t.start();

    }

    public void run() {

        try {
            float counter = 0;
            while (!stop && counter < seconds) {
                Thread.sleep(1000);
                counter += 1;
            }
            done = true;
            if (!stop)
                cB.callBackFunction();
        } catch (Exception e) {
            Printer.println(e.getMessage());
        }
    }

    public void endTimedQuest() {
        stop = true;
        // activeTimedQuests.remove(this);
    }

    public boolean isDone() {
        return done;
    }

    public boolean isStopped() {
        return stop;
    }

    public Thread getThread() {
        return t;
    }

    @Override
    public synchronized void killThread() {
        // TODO Auto-generated method stub
        endTimedQuest();

    }
public static void terminateActiveTimedQuests() {
        for (ThreadTermination t : activeTimedQuests)
            t.killThread();
        activeTimedQuests.clear();
    }

    // public static void main(String[] args) {
    // try {
    // new TimedQuest(1, () -> System.out.println("hello1"));
    // new TimedQuest(2, () -> System.out.println("hello2"));
    // new TimedQuest(3, () -> System.out.println("hello3"));
    // new TimedQuest(4, () -> System.out.println("hello4"));
    // new TimedQuest(5, () -> System.out.println("hello5"));
    // new TimedQuest(6, () -> System.out.println("hello6"));
    // new TimedQuest(7, () -> System.out.println("hello7"));
    // new TimedQuest(8, () -> System.out.println("hello8"));
    // new TimedQuest(9, () -> System.out.println("hello9"));
    // new TimedQuest(10, () -> System.out.println("hello10"));
    // new TimedQuest(11, () -> System.out.println("hello11"));
    // new TimedQuest(12, () -> System.out.println("hello12"));
    // new TimedQuest(13, () -> System.out.println("hello13"));
    // new TimedQuest(14, () -> System.out.println("hello14"));
    // new TimedQuest(15, () -> System.out.println("hello15"));
    // new TimedQuest(16, () -> System.out.println("hello16"));
    // new TimedQuest(17, () -> System.out.println("hello17"));
    // new TimedQuest(18, () -> System.out.println("hello18"));
    // new TimedQuest(19, () -> System.out.println("hello19"));
    // new TimedQuest(20, () -> System.out.println("hello20"));
    // new TimedQuest(21, () -> System.out.println("hello21"));
    // new TimedQuest(22, () -> System.out.println("hello22"));
    // new TimedQuest(23, () -> System.out.println("hello23"));
    // new TimedQuest(24, () -> System.out.println("hello24"));
    // new TimedQuest(25, () -> System.out.println("hello25"));
    // new TimedQuest(26, () -> System.out.println("hello26"));
    // new TimedQuest(27, () -> System.out.println("hello27"));
    // new TimedQuest(28, () -> System.out.println("hello28"));
    // new TimedQuest(29, () -> System.out.println("hello29"));
    // new TimedQuest(30, () -> System.out.println("hello30"));
    // new TimedQuest(31, () -> System.out.println("hello31"));
    // new TimedQuest(32, () -> System.out.println("hello32"));
    // new TimedQuest(33, () -> System.out.println("hello33"));
    // new TimedQuest(34, () -> System.out.println("hello34"));
    // new TimedQuest(35, () -> System.out.println("hello35"));
    // new TimedQuest(5, () -> System.out.println("hello36"));
    // new TimedQuest(6, () -> System.out.println("hello37"));
    // new TimedQuest(7, () -> System.out.println("hello38"));
    // new TimedQuest(8, () -> System.out.println("hello39"));
    // new TimedQuest(9, () -> System.out.println("hello40"));
    // new TimedQuest(10, () -> System.out.println("hello41"));
    // new TimedQuest(11, () -> System.out.println("hello42"));
    // new TimedQuest(12, () -> System.out.println("hello43"));
    // new TimedQuest(13, () -> System.out.println("hello44"));

    // Thread.sleep(5000);

    // // TimedQuest.terminateActiveTimedQuests();
    // System.out.println("Threads terminated successfully");
    // } catch (InterruptedException e) {
    // // TODO Auto-generated catch block
    // e.printStackTrace();
    // }
    // }

}
