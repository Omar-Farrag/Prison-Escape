package TimeTrackers;

import Constants.Days;
import IO.Printer;

public class Time {

    public Days day;
    public int hours;
    public int minutes;
    public int seconds;

    public Time() {
        init(Days.SUNDAY, 0, 0, 0);
    }

    public Time(Time t2) {
        init(t2.day, t2.hours, t2.minutes, t2.seconds);
    }

    public Time(Days day, int hours, int minutes, int seconds) {
        init(day, hours, minutes, seconds);
    }

    private void init(Days day, int hours, int minutes, int seconds) {

        if (hours > 23 || hours < 0 || minutes > 59 || minutes < 0 || seconds > 59 || seconds < 0) {
            Printer.println("Invalid format! Initializing to default time");
            init(Days.SUNDAY, 0, 0, 0);

        } else {

            this.day = day;
            this.hours = hours;
            this.minutes = minutes;
            this.seconds = seconds;
        }
    }

    public boolean equalTo(Time t2) {
        return hours == t2.hours && minutes == t2.minutes && seconds == t2.seconds;

    }

    public boolean greaterThan(Time t2) {

        if (this.equalTo(t2))
            return false;
        // if (day.getDayNum() > t2.day.getDayNum())
        // return true;
        // if (day.getDayNum() == t2.day.getDayNum() && hours > t2.hours)
        // return true;
        if (hours > t2.hours)
            return true;
        if (hours == t2.hours && minutes > t2.minutes)
            return true;
        if (hours == t2.hours && minutes == t2.minutes && seconds > t2.seconds)
            return true;
        else
            return false;
    }

    public boolean lessThan(Time t2) {
        return !(this.greaterThan(t2) || this.equalTo(t2));
    }

    public boolean between(Time left, Time right) {
        return this.greaterThan(left) && this.lessThan(right);
    }

    public void forwardTime(int hours) {
        Days[] weekDays = Days.getWeekDays();
        int daysPassed = hours / 24;
        if (this.hours + hours > 23)
            this.day = weekDays[(this.day.getDayNum() + daysPassed + 1) % 7];
        this.day = weekDays[(this.day.getDayNum() + daysPassed) % 7];
        this.hours = (this.hours + hours) % 24;
    }

    private String padLeft(int num, int n, char c) {
        String s = Integer.toString(num);
        return String.format("%" + n + "s", s).replace(' ', c);
    }

    @Override
    public String toString() {
        return day.toString() + (day.toString().equals("") ? ""
                : ", ") + padLeft(hours, 2, '0') + ":" + padLeft(minutes, 2, '0') + ":"
                + padLeft(seconds, 2, '0');
    }

}
