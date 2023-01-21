package Items;

import Commands.CommandExecution;
import Commands.CommandsDirectory;
import IO.Printer;

public class Desk extends Item {

    public Desk() {

        name = "desk";
        commands = new String[] { "observe" };
        commandFuncs = new CommandExecution[] {
                (command, commandRecipient) -> {
                    observe();
                },

        };
        cd = new CommandsDirectory(this);
        takeable = false;
    }

    private void observe() {
        Printer.println(
                " You take a look at the warden's desk and notice there are no objects that could help you escape.."
                        + "\n it is just a desk filled with no shank pen and penciles and wireless notebooks.. ");
    }

}
