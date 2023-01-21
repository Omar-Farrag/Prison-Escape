package Constants;

public enum Days {
    ALLDAYS("", -1), SUNDAY("Sunday", 0), MONDAY("Monday", 1), TUESDAY("Tuesday", 2), WEDNESDAY("Wednesday", 3),
    THURSDAY("Thursday", 4),
    FRIDAY("Friday", 5), SATURDAY("Saturday", 6);

    private int dayNum;
    private String dayString;

    private Days(String dayString, int dayNum) {
        this.dayString = dayString;
        this.dayNum = dayNum;
    }

    public int getDayNum() {
        return dayNum;
    }

    @Override
    public String toString() {
        return dayString;
    }

    public static Days[] getWeekDays() {
        return new Days[] { SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY,
                FRIDAY, SATURDAY };
    }

}
