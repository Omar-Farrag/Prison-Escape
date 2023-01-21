package Items;

import Commands.CommandExecution;
import Commands.CommandsDirectory;
import IO.Printer;

public class ToiletPaper extends Item {

    public ToiletPaper() {

        name = "toilet paper";
        commands = new String[] { "wipe" };
        commandFuncs = new CommandExecution[] {
                (command, commandRecipient) -> {
                    wipe();
                },

        };
        cd = new CommandsDirectory(this);
        takeable = true;
    }

    private void wipe() {
        Printer.println("You are now cleaning yourself up after using the bathroom");
    }

}
