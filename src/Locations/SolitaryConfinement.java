package Locations;

import Commands.CommandExecution;
import Commands.CommandsDirectory;
import Constants.Days;
import Constants.GoodnessLevel;
import General.InternalClock;
import Items.*;
import Music.Track;
import States.PermittedToEnter;
import States.VisitedState;
import TimeTrackers.Time;
import Characters.Character;

public class SolitaryConfinement extends Location {
    public SolitaryConfinement() {
        description = "It's very dark in here...No windows...Nobody around...There is only one small harmonica at the back of the room";
        locationName = LocationNames.SOLITARY_CONFINEMENT.getName();
        persons = new Character[] {

        };
        items = new Item[] {
                new Harmonica(),
        };
        reasonCantEnter = "";
        commands = new String[] {};
        commandFuncs = new CommandExecution[] {};
        cd = new CommandsDirectory(this);
        visitationState = new VisitedState();
        entryPermissionState = new PermittedToEnter();
        needsEmployment = false;
        currentlyEmployed = false;
        hint = "What do you think sherlock...An empty room with nothing more than a harmonica";
        locationTrack = Track.CELL_TRACK;
        requiredGoodness = GoodnessLevel.any;
        openingTime = new Time(Days.ALLDAYS, 0, 0, 0);
        currentTime = InternalClock.getInstance().getTime();
        closingTime = new Time(Days.ALLDAYS, 23, 59, 59);
        allowedToLeave = true;
        reasonCantLeave = "You know what they say...Don't do the crime unless you're willing to do the time";
        InternalClock.getInstance().removeObserver(this);
    }
}
