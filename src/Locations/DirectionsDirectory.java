package Locations;

import java.util.HashMap;
import java.util.AbstractMap.SimpleEntry;

public class DirectionsDirectory {

        HashMap<String, SimpleEntry<String[], Location[]>> mp;

        public DirectionsDirectory() {
                mp = new HashMap<String, SimpleEntry<String[], Location[]>>();
                Location cell = new Cell();
                Location mosque = new Mosque();
                Location kitchen = new Kitchen();
                Location armory = new Armory();
                Location openYard = new OpenYard();
                Location solitary = new SolitaryConfinement();
                Location morgue = new Morgue();
                Location undergroundStorage = new UnderGroundStorage();
                Location workshop = new WorkShop();
                Location toilet = new Toilet();
                Location infirmary = new Infirmary();
                Location coalField = new CoalField();
                Location wardensOffice = new WardensOffice();
                Location library = new Library();
                Location entrance = new PrisonEntrance();
                Location executionRoom = new ExecutionRoom();
                Location laundryRoom = new LaundryRoom();

                mp.put(LocationNames.CELL.getName().toLowerCase(),
                                new SimpleEntry<>(new String[] { "here", "north", "south", "east", "west" },
                                                new Location[] { cell, morgue, library, workshop, kitchen }));

                mp.put(LocationNames.MOSQUE.getName().toLowerCase(),
                                new SimpleEntry<>(new String[] { "here", "north", "south", "east", "west" },
                                                new Location[] { mosque, infirmary, coalField, undergroundStorage,
                                                                solitary }));
                mp.put(LocationNames.KITCHEN.getName().toLowerCase(),
                                new SimpleEntry<>(new String[] { "here", "north", "south", "east", "west" },
                                                new Location[] { kitchen, executionRoom, library, cell, armory }));

                mp.put(LocationNames.ARMORY.getName().toLowerCase(),
                                new SimpleEntry<>(new String[] { "here", "south", "east" },
                                                new Location[] { armory, entrance, library }));

                // ADD LaundryRoom
                mp.put(LocationNames.OPEN_YARD.getName().toLowerCase(),
                                new SimpleEntry<>(new String[] { "here", "north", "south", "east", "west" },
                                                new Location[] { openYard, library, solitary, infirmary,
                                                                laundryRoom }));

                mp.put(LocationNames.SOLITARY_CONFINEMENT.getName().toLowerCase(),
                                new SimpleEntry<>(new String[] { "here", "north", "south", "east", "west" },
                                                new Location[] { solitary, openYard, coalField, mosque,
                                                                wardensOffice }));
                mp.put(LocationNames.MORGUE.getName().toLowerCase(),
                                new SimpleEntry<>(new String[] { "here", "south", "east", "west" },
                                                new Location[] { morgue, cell, undergroundStorage, executionRoom }));

                mp.put(LocationNames.UNDERGROUNDSTORAGE.getName().toLowerCase(),
                                new SimpleEntry<>(new String[] { "here", "west" },
                                                new Location[] { undergroundStorage, toilet }));

                mp.put(LocationNames.WORKSHOP.getName().toLowerCase(),
                                new SimpleEntry<>(new String[] { "here", "north", "south", "east", "west" },
                                                new Location[] { workshop, morgue, library, undergroundStorage,
                                                                cell }));

                mp.put(LocationNames.TOILET.getName().toLowerCase(),
                                new SimpleEntry<>(new String[] { "here", "north", "south", "east", "west" },
                                                new Location[] { toilet, workshop, infirmary, undergroundStorage,
                                                                library }));

                mp.put(LocationNames.INFIRMARY.getName().toLowerCase(),
                                new SimpleEntry<>(new String[] { "here", "north", "south", "east", "west" },
                                                new Location[] { infirmary, toilet, mosque, undergroundStorage,
                                                                openYard }));

                mp.put(LocationNames.COALFIELD.getName().toLowerCase(),
                                new SimpleEntry<>(new String[] { "here", "north", "east", "west" },
                                                new Location[] { coalField, solitary, undergroundStorage,
                                                                wardensOffice }));
                // ADD Laundry room
                mp.put(LocationNames.WARDENSOFFICE.getName().toLowerCase(),
                                new SimpleEntry<>(new String[] { "here", "north", "east", "west" },
                                                new Location[] { wardensOffice, laundryRoom, openYard, entrance }));

                mp.put(LocationNames.LIBRARY.getName().toLowerCase(),
                                new SimpleEntry<>(new String[] { "here", "north", "south", "east", "west" },
                                                new Location[] { library, cell, openYard, toilet, armory }));

                mp.put(LocationNames.PRISONENTRANCE.getName().toLowerCase(),
                                new SimpleEntry<>(new String[] { "here", "north", "east" },
                                                new Location[] { entrance, armory, wardensOffice }));
                mp.put(LocationNames.EXECUTION_ROOM.getName().toLowerCase(),
                                new SimpleEntry<>(new String[] { "here", "south", "east", "west" },
                                                new Location[] { executionRoom, cell, morgue, armory }));
                mp.put(LocationNames.LAUNDRY_ROOM.getName().toLowerCase(),
                                new SimpleEntry<>(new String[] { "here", "north", "south", "east", "west" },
                                                new Location[] { laundryRoom, library, wardensOffice, openYard,
                                                                armory }));

        }

        public HashMap<String, SimpleEntry<String[], Location[]>> getMp() {
                return mp;
        }

        public SimpleEntry<String[], Location[]> getNextPossibleLocations(Location l) {
                return mp.get(l.getLocationName().toLowerCase().trim());
        }

        public Location getLocation(String locationName) {
                locationName = locationName.toLowerCase().trim();
                if (mp.get(locationName) == null)
                        return null;
                else
                        return mp.get(locationName).getValue()[0];

        }

        public String extractLocation(String locationName) {

                String[] word = locationName.split(" ", 2);

                if (word.length == 0)
                        return "";

                if (word.length == 1) {
                        if (mp.containsKey(word[0]))
                                return word[0];
                        else
                                return "";
                }

                else {
                        if (mp.containsKey(word[1]))
                                return word[1];
                        else
                                return extractLocation(word[1]);
                }
        }
}
