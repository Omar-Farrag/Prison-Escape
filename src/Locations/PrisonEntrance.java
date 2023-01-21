package Locations;

import Characters.Character;
import Characters.EntranceSecurity;
import Commands.CommandExecution;
import Commands.CommandsDirectory;
import Constants.Days;
import Constants.GoodnessLevel;
import General.InternalClock;
import IO.Printer;
import Items.EntranceDoor;
import Items.Item;
import Items.Table;
import Music.DJ;
import Music.Track;
import ObserversAndSubjects.Subject;
import States.NotPermittedToEnter;
import States.NotVisitedState;
// import States.VisitedState;
import TimeTrackers.Time;

public class PrisonEntrance extends Location {
    boolean ableToEscape = true;

    public PrisonEntrance() {
        description = "This is the entrance, the path to freedom is infront of you, however you have to lie to the guards";
        locationName = LocationNames.PRISONENTRANCE.getName();

        this.persons = new Character[] {
                new EntranceSecurity(),
        };

        // door is locked by default, it's opens when escape sequence is initiated
        this.items = new Item[] { new EntranceDoor(), new Table() };
        commands = new String[] { "escape", "spare" };
        commandFuncs = new CommandExecution[] {
                (command, commandRecipient) -> {
                    escapePrison();
                },
                (command, commandRecipient) -> {
                    collectSpareKey();
                },

        };
        reasonCantEnter = "You are not disguised as a security officer";
        cd = new CommandsDirectory(this);
        visitationState = new NotVisitedState();
        entryPermissionState = new NotPermittedToEnter();
        requiredGoodness = GoodnessLevel.any;
        openingTime = new Time(Days.ALLDAYS, 0, 0, 0);
        currentTime = InternalClock.getInstance().getTime();
        closingTime = new Time(Days.ALLDAYS, 23, 59, 59);
        needsEmployment = false;
        currentlyEmployed = false;
        hint = "You just have to walk out through door";

        for (Subject s : this.getSubjects()) {
            s.removeObserver(this);
        }

    }

    public void escapePrison() {
        // open or bash door function from Door object
        if (ableToEscape)
            // Do you want to the CONFETTI sound track to play
            // If you do, you will need to call the DJ.getInstance().play(sdfsdf) function
            DJ.getInstance().play(Track.CONFETTI, true, false);
        else {
            Printer.println("There is a spare key at the table");
        }
    }

    public void collectSpareKey() { // failsafe in case you are not able to escape
        ableToEscape = true;
    }

}
