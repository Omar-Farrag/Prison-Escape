package Locations;

import Commands.CommandExecution;
import Commands.CommandsDirectory;
import Constants.Days;
import Constants.GoodnessLevel;
import General.InternalClock;
import General.UserLocation;
import IO.Printer;
import Items.*;
import States.NotPermittedToEnter;
import States.NotVisitedState;
import TimeTrackers.Time;

//Zayed
public class UnderGroundStorage extends Location {

    private static boolean boobyTrapped = false;

    public UnderGroundStorage() {
        description = "This is the Underground Storage. Oil barrels, gas canisters, cooking utensils are stored here";
        locationName = LocationNames.UNDERGROUNDSTORAGE.getName();
        persons = new Characters.Character[] {};
        items = new Item[] {
                new GunPowder(),
                // new CookingResources(),
                new DrillingTools()
        };

        reasonCantEnter = "";
        commands = new String[] { "light", "boobytrap" };
        commandFuncs = new CommandExecution[] {

                (command, commandRecipient) -> {
                    switchLight();
                },

                (command, commandRecipient) -> {
                    boobyTrapUGS(commandRecipient); // UGS- UnderGround Storage
                },

        };
        cd = new CommandsDirectory(this);
        visitationState = new NotVisitedState();
        entryPermissionState = new NotPermittedToEnter();
        needsEmployment = true;
        currentlyEmployed = false;
        hint = "This place has is an explosive hazard. Use an item around to setup a distraction for your escape later";

        requiredGoodness = GoodnessLevel.minToBeGOOD;
        openingTime = new Time(Days.ALLDAYS, 5, 0, 0);
        currentTime = InternalClock.getInstance().getTime();
        closingTime = new Time(Days.ALLDAYS, 21, 0, 0);
        boobyTrapped = false;
    }

    // escape sequence requirement
    // This function does something very similar to the gunpowder pour function
    // I think we can comment this one out
    public void boobyTrapUGS(String commandRecipient) {
        UserLocation ul = UserLocation.getInstance();
        Item gunpowder = ul.getMobileInventory().findItem("gunpowder");
        if (gunpowder == null) {
            Printer.println("Find the gunpowder and add it to your inventory");
        } else {

            Printer.println(
                    "Underground storage boobytrapped...You can blow up the underground storage anytime when you want to escape");
            ul.getMobileInventory().removeFromInventory(gunpowder);
            boobyTrapped = true;
        }

    }

    public void switchLight() {
        Printer.println("You have switched on the light");

    }

    public static boolean isBoobyTrapped() {
        return boobyTrapped;
    }
}