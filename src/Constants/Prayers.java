package Constants;

public enum Prayers {

    FAJR("Salat Al Fajr"),
    DHUHR("Salat Al DHUHR"),
    ASR("Salat Al ASR"),
    MAGHRIB("Salat Al MAGHRIB"),
    ISHAA("Salat Al ISHAA");

    private String name;

    private Prayers(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
