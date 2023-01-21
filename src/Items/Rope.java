package Items;

import Commands.CommandExecution;
import Commands.CommandsDirectory;

public class Rope extends Item {
    public Rope() {
        this.name = "Rope";
        this.commands = new String[] {};
        this.commandFuncs = new CommandExecution[] {};
        this.cd = new CommandsDirectory(this);
        this.takeable = true;
        this.reasonCantTake = "";
    }
}
