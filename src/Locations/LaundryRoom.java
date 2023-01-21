package Locations;

import Characters.Character;
import Characters.LaundryMan;
import Commands.CommandExecution;
import Commands.CommandsDirectory;
import Constants.Days;
import Constants.GoodnessLevel;
import General.GoodnessIndex;
import General.InternalClock;
import IO.Printer;
import Items.GuardUniform;
import Items.Item;
import States.NotVisitedState;
import States.PermittedToEnter;
import TimeTrackers.Time;

public class LaundryRoom extends Location {

    public LaundryRoom() {
        description = "The Laundry Room is where you wash your clothes and leave.An officer forgot their uniform in a washing machine, take advantage of this oppurtinity";
        locationName = LocationNames.LAUNDRY_ROOM.getName();
        persons = new Character[] {
                new LaundryMan()
        };
        items = new Item[] {
                new GuardUniform()
        };
        reasonCantEnter = "";
        commands = new String[] { "wash", "foam" };
        commandFuncs = new CommandExecution[] {
                (command, commandRecipient) -> {
                    washClothes();
                },
                (command, commandRecipient) -> {
                    foam();
                }
        };
        cd = new CommandsDirectory(this);
        visitationState = new NotVisitedState();
        entryPermissionState = new PermittedToEnter();
        needsEmployment = true;
        currentlyEmployed = false;
        hint = "Take the guard's uniform";
        requiredGoodness = GoodnessLevel.any;
        openingTime = new Time(Days.ALLDAYS, 10, 0, 0);
        currentTime = InternalClock.getInstance().getTime();
        closingTime = new Time(Days.SATURDAY, 17, 00, 00);
    }

    public void washClothes() {
        Printer.println("Your clothes are done washing and are looking fresh");
    }

    public void foam() {
        Printer.println(
                "You have overfilled the laundry room with foam and annoyed the laundryman");
        GoodnessIndex.getInstance().decreaseGoodnessIndex(5);
    }

}
