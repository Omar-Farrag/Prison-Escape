package General;

import Constants.Days;
import Constants.Prayers;
import IO.Printer;
import ObserversAndSubjects.ConcreteSubject;
import ObserversAndSubjects.Message;
import ObserversAndSubjects.Topic;
import TimeTrackers.Time;

public class InternalClock extends ConcreteSubject implements Runnable, ThreadTermination {

    private static InternalClock instance;
    private Days[] weekDays;
    private Time time;
    public Thread t;
    private Prayers currentPrayer;
    private Time Fajr;
    private Time Dhuhr;
    private Time Asr;
    private Time Maghrib;
    private Time Ishaa;
    private boolean stop;

    private InternalClock() {
        weekDays = Days.getWeekDays();
        time = new Time(Days.SUNDAY, 23, 55, 0);
        Fajr = new Time(Days.ALLDAYS, 5, 18, 0);
        Dhuhr = new Time(Days.ALLDAYS, 12, 4, 0);
        Asr = new Time(Days.ALLDAYS, 15, 07, 0);
        Maghrib = new Time(Days.ALLDAYS, 17, 28, 0);
        Ishaa = new Time(Days.ALLDAYS, 18, 58, 0);
        t = new Thread(this);
        t.setName("Internal Clock");
        t.start();
    }

    public static synchronized InternalClock getInstance() {
        if (instance == null)
            return instance = new InternalClock();
        return instance;
    }

    public Time getTime() {
        return time;
    }

    public void run() {
        while (!stop) {

            try {
                Thread.sleep(100 / 3);
                time.seconds = (time.seconds + 1) % 60;
                if (time.seconds == 0)
                    time.minutes = (time.minutes + 1) % 60;
                if (time.minutes == 0 && time.seconds == 0) {
                    time.hours = (time.hours + 1) % 24;
                    publishMessage(new Message(this, Topic.TIME, null));
                }
                if (time.minutes == 0 && time.seconds == 0 && time.hours == 0)
                    time.day = weekDays[(time.day.getDayNum() + 1) % 7];

                if (isFajrTiming() || isDhuhrTiming() || isAsrTiming() || isMaghribTiming() || isIshaaTiming())
                    publishMessage(new Message(this, Topic.PRAYER_TIMING, currentPrayer));

            } catch (Exception e) {
                Printer.println(e.getMessage());
            }
        }
    }

    public void passTime(int hours) {
        synchronized (time) {
            time.forwardTime(hours);
        }
        publishMessage(new Message(this, Topic.TIME, null));

        Prayers previousPrayer = currentPrayer;

        adjustCurrentPrayer();

        if (previousPrayer != currentPrayer)
            publishMessage(new Message(this, Topic.CURRENT_PRAYER_ADJUSTMENT, currentPrayer));

    }

    private void adjustCurrentPrayer() {
        if (time.between(Fajr, Dhuhr))
            currentPrayer = Prayers.FAJR;
        else if (time.between(Dhuhr, Asr))
            currentPrayer = Prayers.DHUHR;
        else if (time.between(Asr, Maghrib))
            currentPrayer = Prayers.ASR;
        else if (time.between(Maghrib, Ishaa))
            currentPrayer = Prayers.MAGHRIB;
        else
            currentPrayer = Prayers.ISHAA;
    }

    private boolean isFajrTiming() {
        if (time.equalTo(Fajr)) {
            currentPrayer = Prayers.FAJR;
            return true;
        }
        return false;
    }

    private boolean isDhuhrTiming() {
        if (time.equalTo(Dhuhr)) {
            currentPrayer = Prayers.DHUHR;
            return true;
        }
        return false;
    }

    private boolean isAsrTiming() {
        if (time.equalTo(Asr)) {
            currentPrayer = Prayers.ASR;
            return true;
        }
        return false;
    }

    private boolean isMaghribTiming() {
        if (time.equalTo(Maghrib)) {
            currentPrayer = Prayers.MAGHRIB;
            return true;
        }
        return false;
    }

    private boolean isIshaaTiming() {
        if (time.equalTo(Ishaa)) {
            currentPrayer = Prayers.ISHAA;
            return true;
        }
        return false;
    }

    @Override
    public void killThread() {
        stop = true;

    }

}
