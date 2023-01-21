package Escape;

public enum EscapeFileCommands {
    PAUSE("[PAUSE]"), TRACK("[TRACK]"), END("[END]");

    String name;

    private EscapeFileCommands(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
