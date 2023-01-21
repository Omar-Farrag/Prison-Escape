package Locations;

import Commands.CommandExecution;
import Commands.CommandsDirectory;
import Constants.Days;
import Constants.GoodnessLevel;
import General.GoodnessIndex;
import General.InternalClock;
import General.UserLocation;
import IO.Printer;
import Items.*;
import Music.DJ;
import Music.Track;
import States.NotVisitedState;
import States.PermittedToEnter;
import TimeTrackers.Time;
import TimeTrackers.TimedQuest;
import Characters.*;
import Characters.Character;

public class Armory extends Location {

    private static boolean guardsAsleep = false;
    private static boolean insideArmory = false;
    private String innerDescription;

    public Armory() {
        description = "You're standing in front of the armory's entrance...Standing there are officers"
                + "\nWoods and Mason";
        innerDescription = "Oh man there are tons of weapons here..."
                + "\nThere are AK47s, M16s, RPGs, Sniper Rifles, Hand Guns, Grenades, and Smoke Bombs"
                + "\nThis is probably where Rambo and the Terminator go shopping during the weekends";
        locationName = LocationNames.ARMORY.getName();
        persons = new Character[] {
                new Woods("Woods"),
                new Mason("Mason"),
        };
        items = new Item[] {
                new AK47(),
                new M16(),
                new RPG(),
                new SniperRifle(),
                new HandGun(),
                new Grenade(),
                new SmokeBomb(),
        };
        reasonCantEnter = "The guards are standing in front of the door 24/7";
        commands = new String[] { "give", "enter", "leave", "see" };
        commandFuncs = new CommandExecution[] {
                (command, commandRecipient) -> {
                    giveTea(commandRecipient);
                },
                (command, commandRecipient) -> {
                    enterArmory();
                },
                (command, commandRecipient) -> {
                    leaveArmory();
                },
                (command, commandRecipient) -> {
                    see();
                },
        };
        cd = new CommandsDirectory(this);
        visitationState = new NotVisitedState();
        entryPermissionState = new PermittedToEnter();
        needsEmployment = false;
        currentlyEmployed = false;
        hint = "The guards said they wanted tea...Perhaps you can deliver to them a 'custom made' tea,"
                + "\nif you know what I mean ^-";
        locationTrack = Track.NOTHING;
        requiredGoodness = GoodnessLevel.any;
        openingTime = new Time(Days.ALLDAYS, 0, 0, 0);
        currentTime = InternalClock.getInstance().getTime();
        closingTime = new Time(Days.ALLDAYS, 23, 59, 59);
        guardsAsleep = false;
        insideArmory = false;

    }

    private void giveTea(String commandRecipient) {

        Inventory playerMobileInventory = UserLocation.getInstance().getMobileInventory();
        String firstWord = commandRecipient.split(" ")[0];
        if (!firstWord.equals("tea")) {
            Printer.println("Didn't quite get what you are trying to give");
            return;
        }
        Item tea = playerMobileInventory.findItem("tea");
        if (tea == null) {
            Printer.println(
                    "The tea is not in your inventory...How will you pass on the tea if you don't have it in the first place");
            return;
        }
        if (!Tea.isInjected())
            letGuardsEnjoyTea();
        else
            sendGuardsToSleep();

        playerMobileInventory.removeFromInventory(tea);
    }

    private void enterArmory() {
        if (!guardsAsleep)
            Printer.println("Broo didn't I tell you there are guards standing in front of the door?");
        else {
            Printer.println("You are now inside the armory");
            insideArmory = true;
        }
    }

    private void leaveArmory() {
        if (!insideArmory) {
            Printer.println("You are already outside the armory");
        } else {
            insideArmory = false;
            Printer.println("You are now back at the entrance to the armory");
        }
    }

    private void see() {
        if (insideArmory)
            Printer.println(innerDescription);
        else
            Printer.println("You need to be enter the armory first to see what's inside");
    }

    private void sendGuardsToSleep() {
        guardsAsleep = true;
        Printer.println("Somehow, your plan actually worked...After a few sips, both officers, Woods and Mason,"
                + "\nwent to la la land");

        DJ.getInstance().play(Track.GUARDS_FALLING, false, false);

        new TimedQuest(60, () -> {
            wakeUpGuards();
        });
    }

    private void letGuardsEnjoyTea() {
        Printer.println("[WOODS] That's some good tea right there");

    }

    private void wakeUpGuards() {
        guardsAsleep = false;

        if (UserLocation.getInstance().getCurrentLocation().equals(this))
            insideArmory = false;

        if (insideArmory) {
            Printer.println("[WOODS] HEEYYYY WHAT DO YOU THINK YOU'RE DOING INSIDE THERE");
            GoodnessIndex.getInstance().decreaseGoodnessIndex(30);
            UserLocation.getInstance().getPunished();
            insideArmory = false;
        } else
            Printer.println("[MASON] Oh man...Woods any idea what happened ?");

    }

    public static void setGuardsAsleep(boolean guardsAsleep) {
        Armory.guardsAsleep = guardsAsleep;
    }

    public static void setInsideArmory(boolean insideArmory) {
        Armory.insideArmory = insideArmory;
    }

    public static boolean isGuardsAsleep() {
        return guardsAsleep;
    }

    public static boolean isInsideArmory() {
        return insideArmory;
    }

}
