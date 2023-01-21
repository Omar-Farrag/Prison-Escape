package Locations;

import Commands.CommandExecution;
import Commands.CommandsDirectory;
import Constants.Days;
import Constants.GoodnessLevel;
import General.InternalClock;
import Items.*;
import States.NotVisitedState;
import States.PermittedToEnter;
import TimeTrackers.Time;
import Characters.*;

public class WorkShop extends Location {
        public WorkShop() {
                description = "A very dusty place with pieces of wood and tools scattered around the work benches...";
                locationName = LocationNames.WORKSHOP.getName();
                persons = new Characters.Character[] {
                                new Oliver()
                };
                items = new Item[] {
                                new Hammer(),
                                new HandSaw(),
                                new MeasuringTape(),
                                new WorkBench(),
                                new Wood()
                };
                reasonCantEnter = "";

                commands = new String[] {

                };

                commandFuncs = new CommandExecution[] {
                                (command, commandRecipient) -> {

                                }

                };
                cd = new CommandsDirectory(this);

                visitationState = new NotVisitedState();
                entryPermissionState = new PermittedToEnter();

                needsEmployment = true;
                currentlyEmployed = false;
                hint = "Look for a tool that could break glass";

                requiredGoodness = GoodnessLevel.minToBeGOOD;

                openingTime = new Time(Days.ALLDAYS, 9, 0, 0);
                currentTime = InternalClock.getInstance().getTime();
                closingTime = new Time(Days.ALLDAYS, 21, 0, 0);

        }

}