package Items;

import Commands.CommandExecution;
import Commands.CommandsDirectory;
import IO.Printer;

public class Towel extends Item {

    public Towel() {

        name = "towel";
        commands = new String[] { "dry" };
        commandFuncs = new CommandExecution[] {
                (command, commandRecipient) -> {
                    dryyourself();
                },

        };
        cd = new CommandsDirectory(this);
        takeable = true;
    }

    private void dryyourself() {
        Printer.println(" You are now drying yourself up after taking a bath");
    }

}
