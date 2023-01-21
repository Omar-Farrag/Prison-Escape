package Items;

import Commands.CommandExecution;
import Commands.CommandsDirectory;
import IO.Printer;

public class Mattress extends Item {

    public Mattress() {
        name = "mattress";
        commands = new String[] { "lift" };
        commandFuncs = new CommandExecution[] {
                (command, commandRecipient) -> {
                    liftMattress();
                }

        };
        cd = new CommandsDirectory(this);
        takeable = false;
    }

    private void liftMattress() {
        Printer.println("You lift up the mattress...It was a bit heavy so you struggle a little bit"
                + "\nOnce the mattress is up you notice there is a note hidden beneath...");
    }
}
