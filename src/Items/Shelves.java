package Items;

import Commands.CommandExecution;
import Commands.CommandsDirectory;
import IO.Printer;

public class Shelves extends Item {
    public Shelves() {
        this.name = "shelves";
        this.commands = new String[] { "look" };
        this.commandFuncs = new CommandExecution[] {
                (command, commandRecipient) -> {
                    lookAtShelves();
                },
        };
        this.cd = new CommandsDirectory(this);
        takeable = false;
    }

    private void lookAtShelves() {
        Printer.println("As you walk by the shelves, you see hundreds of books on various topics"
                + "\nFrom science to fiction, everything is there. You can grab any book and read it");

    }
}
