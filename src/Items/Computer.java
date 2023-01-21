package Items;

import Commands.CommandExecution;
import Commands.CommandsDirectory;
import IO.Printer;
import Locations.WardensOffice;

public class Computer extends Item {
    private WardensOffice WO;

    public Computer(WardensOffice WO) {

        name = "computer";
        commands = new String[] { "look" };
        commandFuncs = new CommandExecution[] {
                (command, commandRecipient) -> {
                    look();
                },

        };
        cd = new CommandsDirectory(this);
        takeable = false;
        reasonCantTake = "Are you seriously just going to take the warden's computer and dip?? And you expect nobody to notice??";
        this.WO = WO;
    }

    private void look() {
        Printer.println(" You glance at the computer and see the the warden's daily schedule...He works daily from "
                + WO.getOpeningTime() + " to " + WO.getClosingTime());
    }

}
