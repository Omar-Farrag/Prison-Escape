package Locations;

import java.util.ArrayList;

import Commands.CommandExecution;
import Commands.CommandsDirectory;
import Constants.Days;
import Constants.GoodnessLevel;
import General.InternalClock;
import General.Main;
import General.UserLocation;
import IO.Printer;
import Items.*;
import Music.Track;
import States.PermittedToEnter;
import States.VisitedState;
import TimeTrackers.Time;
import Characters.Character;

public class Cell extends Location {

    private static Inventory stash = new Inventory(10);
    // ADD ITEMS NEEDED FOR ESCAPE
    private ArrayList<String> itemsNeededForEscape;
    private int numItemsCollectedForEscape;

    public Cell() {

        description = "This is your cell. On the left side there is your bed where you can sit and sleep"
                + "\nAbove your bed there are a few pictures hanged on the wall "
                + "\nThere is a small window on the wall next to your bed and on the right side"
                + "\nyou will find your mirror and toothbrush";
        locationName = LocationNames.CELL.getName();

        this.persons = new Character[] {};
        this.items = new Item[] {
                new Bed(),
                new Mattress(),
                new Mirror(),
                new Note(),
                new Pictures(),
                new ToothBrush(),
                new Window(),
        };

        itemsNeededForEscape = new ArrayList<>() {
            {
                add(new Rope().getName());
                add(new Matchbox().getName());
                add(new SmokeBomb().getName());
                add(new GuardUniform().getName());
                add(new DuplicateKey().getName());
                add(new Hammer().getName());
            }
        };
        numItemsCollectedForEscape = 0;

        commands = new String[] { "remove", "hide", "progress", "see", "escape" };
        commandFuncs = new CommandExecution[] {
                (command, commandRecipient) -> takeItemFromStash(commandRecipient),

                (command, commandRecipient) -> addItemToStash(commandRecipient),

                (command, commandRecipient) -> printItemProgress(),

                (command, commandRecipient) -> seeStash(commandRecipient),

                (command, commandRecipient) -> escape(),

        };
        cd = new CommandsDirectory(this);
        reasonCantEnter = "";
        visitationState = new VisitedState();
        entryPermissionState = new PermittedToEnter();
        requiredGoodness = GoodnessLevel.any;
        openingTime = new Time(Days.ALLDAYS, 0, 0, 0);
        currentTime = InternalClock.getInstance().getTime();
        closingTime = new Time(Days.ALLDAYS, 23, 59, 59);
        needsEmployment = false;
        currentlyEmployed = false;
        hint = "There may be something under the mattress";
        locationTrack = Track.CELL_TRACK;
        InternalClock.getInstance().removeObserver(this);

    }

    private void escape() {
        if (!readyToEscape()) {
            Printer.println("Big man...you just made it here...you're not ready to escape yet");
        } else {
            Printer.println("Alright chief...starting escape...");
            Main.escape();
        }
    }

    private boolean readyToEscape() {
        return (numItemsCollectedForEscape == itemsNeededForEscape.size())
                || UnderGroundStorage.isBoobyTrapped();
    }

    private void seeStash(String commandRecipient) {
        if (commandRecipient.equals("stash"))
            Printer.println(
                    "You lean under the bed to look for your hidden items..." + "\nYou find "
                            + (stash.toString().equals("") ? "nothing"
                                    : stash.toString()));

        else
            Printer.println("Could not understand what you wanna look at...Perhaps you mean 'see stash'");
    }

    public static Inventory getStash() {
        return stash;
    }

    public void addItemToStash(String commandRecipient) {
        UserLocation ul = UserLocation.getInstance();
        Inventory mobile = ul.getMobileInventory();

        Item temp = mobile.findItem(commandRecipient);
        if (temp == null)
            Printer.println(
                    "'" + commandRecipient
                            + "' is not in your mobile inventory. You can only add items in your mobile inventory to the stash");
        else if (stash.findItem(temp.getName()) != null)
            Printer.println("You already have " + temp.getName() + " in the stash");
        else if (!stash.addToInventory(temp)) {
            Printer.println("Stash full! Remove some items first to open up space");
        } else {
            mobile.removeFromInventory(temp);
            if (itemsNeededForEscape.contains(temp.getName())) {
                numItemsCollectedForEscape++;
            }
            Printer.println(temp + " added to stash");
            if (readyToEscape())
                Printer.println("You now have everything you need to escape");

        }

    }

    public void takeItemFromStash(String commandRecipient) {
        Item temp = stash.findItem(commandRecipient);
        if (temp == null)
            Printer.println("'" + commandRecipient + "' is not in the stash");

        else {
            UserLocation ul = UserLocation.getInstance();
            Inventory inv = ul.getMobileInventory();

            if (!inv.addToInventory(temp)) {
                Printer.println("Your inventory looks full. Drop some items to be able to take items from stash");
            } else {
                stash.removeFromInventory(temp);
                if (itemsNeededForEscape.contains(temp.getName())) {
                    numItemsCollectedForEscape--;
                    if ((numItemsCollectedForEscape + 1) == itemsNeededForEscape.size())
                        Printer.println("You no longer have everything you need to escape");
                }
                Printer.println(temp + " dropped");
            }
        }

    }

    public void printItemProgress() {
        Printer.println("You currently have " + numItemsCollectedForEscape + " out of " + itemsNeededForEscape.size()
                + " items needed for escape");
    }

}
