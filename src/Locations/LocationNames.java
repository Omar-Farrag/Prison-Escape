package Locations;

public enum LocationNames {

    CELL("Cell"),
    LIBRARY("Library"),
    MOSQUE("Mosque"),
    MORGUE("Morgue"),
    PRISONENTRANCE("Prison Entrance"),
    COALFIELD("Coal Field"),
    UNDERGROUNDSTORAGE("Underground Storage"),
    KITCHEN("Kitchen"),
    WARDENSOFFICE("Warden's Office"),
    WORKSHOP("Workshop"),
    INFIRMARY("Infirmary"),
    ARMORY("Armory"),
    OPEN_YARD("Open Yard"),
    SOLITARY_CONFINEMENT("Solitary Confinement"),
    TOILET("Toilet"),
    EXECUTION_ROOM("Execution Room"),
    LAUNDRY_ROOM("Laundry Room");

    String name;

    private LocationNames(String s) {
        name = s;
    }

    public String getName() {
        return name;
    }

}
