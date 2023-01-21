package Items;

import Commands.CommandExecution;
import Commands.CommandsDirectory;
import IO.Printer;

public class Wood extends Item {

    public Wood() {

        name = "wood";
        commands = new String[] { "touch", "observe" };
        commandFuncs = new CommandExecution[] {
                (command, commandRecipient) -> {
                    touchWood();
                },
                (command, commandRecipient) -> {
                    observeWood();
                },
        };
        cd = new CommandsDirectory(this);
        takeable = false;
    }

    private void touchWood() {
        Printer.println("You are now touching the surface of the wood");
    }

    public void observeWood() {
        Printer.println("You are now observing the quality of the wood from a distance");
    }
}
