package Items;

import Commands.CommandExecution;
import Commands.CommandsDirectory;

public class DuplicateKey extends Item {
    public DuplicateKey() {
        this.name = "Duplicate Key";
        this.commands = new String[] {};
        this.commandFuncs = new CommandExecution[] {};
        this.cd = new CommandsDirectory(this);
        this.takeable = true;
        this.reasonCantTake = "";
    }
}
