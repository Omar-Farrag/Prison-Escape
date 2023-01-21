package Items;

import Commands.CommandExecution;
import Commands.CommandsDirectory;
import General.GoodnessIndex;
import General.UserLocation;
import IO.Printer;
import Locations.LocationNames;

public class DrillingTools extends Item {

    public DrillingTools() {
        name = "drilling tool";
        this.commands = new String[] { "drill" };
        this.commandFuncs = new CommandExecution[] {
                (command, commandRecipient) -> {
                    drillCole();
                },
        };
        this.cd = new CommandsDirectory(this);
        takeable = false;
        reasonCantTake = "drilling tools belong to the coal field...";
    }

    public void drillCole() {
        if (UserLocation.getInstance().getCurrentLocation().getLocationName()
                .equals(LocationNames.COALFIELD.getName())) {
            Printer.println("You are drilling the coal ore, Good job");
            GoodnessIndex.getInstance().increaseGoodnessIndex(5);
        } else
            Printer.println("Are you actually planning to just drill coal here??");
    }

    public String getName() {
        return name;
    }

    public String toString() {
        return name;
    }
}
