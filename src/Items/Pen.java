package Items;

import Commands.CommandExecution;
import Commands.CommandsDirectory;

public class Pen extends Item {
    public Pen() {
        this.name = "Pen";
        this.commands = new String[] {};
        this.commandFuncs = new CommandExecution[] {
                (command, commandRecipient) -> {

                },
        };
        this.cd = new CommandsDirectory(this);
        this.takeable = true;
        this.reasonCantTake = "";
    }
}
