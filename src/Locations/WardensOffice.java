package Locations;

import Commands.CommandExecution;
import Commands.CommandsDirectory;
import Constants.Days;
import Constants.GoodnessLevel;
import General.InternalClock;
import General.UserLocation;
import IO.Printer;
import Items.*;
import States.NotVisitedState;
import States.PermittedToEnter;
import TimeTrackers.Time;
import Characters.*;

public class WardensOffice extends Location {
    public WardensOffice() {
        description = "a space where the warden spend most of his time making sure that the prison is running properly...";
        locationName = LocationNames.WARDENSOFFICE.getName();
        persons = new Characters.Character[] {
                new RaymondTBaker()
        };
        items = new Item[] {
                new Computer(this),
                new Desk(),
                new CellKey()

        };
        reasonCantEnter = "The room is locked";

        commands = new String[] {
                "Distract",
                "get",
                "quit",
        };

        commandFuncs = new CommandExecution[] {
                (command, commandRecipient) -> {
                    Distract();
                },
                (command, commandRecipient) -> {
                    UserLocation.getInstance().getJobAt(commandRecipient);
                },
                (command, commandRecipient) -> {
                    UserLocation.getInstance().quitJobAt(commandRecipient);
                }

        };
        cd = new CommandsDirectory(this);

        visitationState = new NotVisitedState();
        entryPermissionState = new PermittedToEnter();

        needsEmployment = false;
        currentlyEmployed = false;
        hint = "You need to grab the cell key....distract the warden....";

        requiredGoodness = GoodnessLevel.minToBeGOOD;

        openingTime = new Time(Days.ALLDAYS, 10, 0, 0);
        currentTime = InternalClock.getInstance().getTime();
        closingTime = new Time(Days.ALLDAYS, 22, 0, 0);

    }

    public void Distract() {

        Printer.println("You distracted the warden.... by making him look at the picture on the wall..");
    }

}
