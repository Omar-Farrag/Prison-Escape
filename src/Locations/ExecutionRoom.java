package Locations;

import Characters.inmateone;
import Characters.inmatetwo;
import Commands.CommandExecution;
import Commands.CommandsDirectory;
import Constants.Days;
import Constants.GoodnessLevel;
import General.InternalClock;
import Items.*;
import States.NotVisitedState;
import States.PermittedToEnter;
import TimeTrackers.Time;

public class ExecutionRoom extends Location {
    public ExecutionRoom() {
        description = " A place where prisoners get to take their last breath before they get hanged...There are ropes everywhere";
        locationName = LocationNames.EXECUTION_ROOM.getName();
        persons = new Characters.Character[] {
                new inmateone(),
                new inmatetwo(),
        };
        items = new Item[] {
                new Rope(),
        };
        reasonCantEnter = "";

        commands = new String[] {
        };

        commandFuncs = new CommandExecution[] {};
        cd = new CommandsDirectory(this);

        visitationState = new NotVisitedState();
        entryPermissionState = new PermittedToEnter();

        needsEmployment = false;
        currentlyEmployed = false;
        hint = "nothing to find here... ";

        requiredGoodness = GoodnessLevel.any;

        openingTime = new Time(Days.ALLDAYS, 8, 0, 0);
        currentTime = InternalClock.getInstance().getTime();
        closingTime = new Time(Days.ALLDAYS, 12, 0, 0);

    }

}
