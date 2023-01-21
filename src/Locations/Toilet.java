package Locations;

import Commands.CommandExecution;
import Commands.CommandsDirectory;
import Constants.Days;
import Constants.GoodnessLevel;
import General.InternalClock;
import IO.Printer;
import Items.*;
import States.NotVisitedState;
import States.PermittedToEnter;
import TimeTrackers.Time;

public class Toilet extends Location {
    public Toilet() {
        description = " This is the toilet....the first thing you may notice are the stainless steel toilet seats and the horrible odour breezing past by you....nothing much happens here just do your business and leave...";
        locationName = LocationNames.TOILET.getName();
        persons = new Characters.Character[] {

        };
        items = new Item[] {
                new MetalToiletSeat(),
                new ToiletPaper(),
                new Soap(),
                new Towel()
        };
        reasonCantEnter = "";

        commands = new String[] {
                "bathe"
        };

        commandFuncs = new CommandExecution[] {
                (command, commandRecipient) -> {
                    bath();
                }

        };
        cd = new CommandsDirectory(this);

        visitationState = new NotVisitedState();
        entryPermissionState = new PermittedToEnter();

        needsEmployment = false;
        currentlyEmployed = false;
        hint = "Look for an object that could help you duplicate the cell key";

        requiredGoodness = GoodnessLevel.any;

        openingTime = new Time(Days.ALLDAYS, 6, 0, 0);
        currentTime = InternalClock.getInstance().getTime();
        closingTime = new Time(Days.ALLDAYS, 10, 0, 0);

    }

    public void bath() {

        Printer.println("You are now taking a bath");
    }
}
