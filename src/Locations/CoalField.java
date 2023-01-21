package Locations;

import Characters.Character;
import Commands.CommandExecution;
import Commands.CommandsDirectory;
import Constants.Days;
import Constants.GoodnessLevel;
import General.InternalClock;
import General.UserLocation;
import IO.Printer;
import Items.DrillingTools;
import Items.Item;
import Items.Pickaxe;
import Music.Track;
import Characters.*;
import States.NotPermittedToEnter;
import States.NotVisitedState;
import TimeTrackers.Time;

//Zayed
public class CoalField extends Location {

    CoalField() {
        description = "You are in the CoalField now. There are drilling tools and a pickaxes around you..."
                + "\nThere are other inmates also mining coal and in the center there is the coal field supervison..."
                + "\n Pick a tool and get to work...";
        this.items = new Item[] { new DrillingTools(), new Pickaxe() };
        locationName = LocationNames.COALFIELD.getName();
        this.persons = new Character[] {
                new CoalFieldInmates(), new CoalFieldSupervisor()
        };

        commands = new String[] { "work", "throw" };
        reasonCantEnter = "Your don't work here, you can work if you are an good inmate";

        commandFuncs = new CommandExecution[] {
                (command, commandRecipient) -> {
                    // mineOre(commandRecipient);
                },
                (command, commandRecipient) -> {
                    throwSmokeBomb(commandRecipient);
                },

        };
        cd = new CommandsDirectory(this);
        visitationState = new NotVisitedState();
        entryPermissionState = new NotPermittedToEnter();
        needsEmployment = true;
        currentlyEmployed = false; // discuss later
        hint = "Visit this place later with an escape plan and a smoke bomb if you want to escape";
        locationTrack = Track.NOTHING;
        requiredGoodness = GoodnessLevel.minToBeGOOD;
        openingTime = new Time(Days.ALLDAYS, 0, 0, 0);
        currentTime = InternalClock.getInstance().getTime();
        closingTime = new Time(Days.ALLDAYS, 12, 0, 0);

    }

    public void throwSmokeBomb(String commandRecipient) {
        UserLocation ul = UserLocation.getInstance();

        Item temp = ul.getMobileInventory().findItem(commandRecipient);
        if (temp == null)
            Printer.println("'" + commandRecipient + "' is not in the inventory");

        else {
            Printer.println("Throwing smoke bomb... The guards are now unconsious");
            ul.getMobileInventory().removeFromInventory(temp); // remove smoke grenade after use
        }
    }

    /*
     * [Omar] Reason this is not working is that in the command control class
     * When the user enters something it checks if it contains the item name
     * If it contains the item name then it looks for the item and calls useItem on
     * that item
     * Therefore, you don't need to have functions here to call the useItem its done
     * in command control
     * for example if the user writes drill coal with drilling tool, it will notice
     * that drilling tool is an item
     * therefore it will call useItem on drilling tool instead of coming here to the
     * coalfield class
     */
    // public void mineOre(String commandRecepient) {
    // for (Item m : items) {
    // m.useItem(mine, mine); // need help

    // }
    // }

}