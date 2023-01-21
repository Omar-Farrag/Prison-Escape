package SensorControl;

public enum SensorCommand {
    PUNCH_RIGHT, PUNCH_LEFT,
    DODGE_RIGHT, DODGE_LEFT,
    Lift, SHOOT_HOOP,
    SLEEP, SWING_HAMMER;

    public static SensorCommand[] getSensorCommands() {
        return new SensorCommand[] { PUNCH_RIGHT, PUNCH_LEFT,
                DODGE_RIGHT, DODGE_LEFT,
                Lift, SHOOT_HOOP,
                SLEEP, SWING_HAMMER };
    }

}
