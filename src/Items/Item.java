package Items;

import Commands.CommandExecution;
import Commands.CommandsDirectory;
import General.UserLocation;
import IO.Printer;

public abstract class Item {

    /*
     * Initialize the name of the item in all the child classes
     * Add all the necessary functions for that item in the child class
     */
    protected String name;
    protected String[] commands;
    protected CommandExecution[] commandFuncs;
    protected CommandsDirectory cd;
    protected boolean takeable;
    protected String reasonCantTake = "";

    public void useItem(String command, String commandRecipient) {
        if (command.equals("take"))
            UserLocation.getInstance().takeItem(commandRecipient);
        else if (command.equals("drop"))
            UserLocation.getInstance().dropItem(commandRecipient);
        else if (cd.foundCommand(command))
            cd.getCommandFunc(command).execute(command, commandRecipient);

        else
            Printer.println("You cannot '" + command + "' the " + name
                    + "... You can only "
                    + (isTakeable() ? "take/drop/" : "")
                    + String.join("/", commands) + " the "
                    + name);
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }

    public String[] getCommands() {
        return commands;
    }

    public CommandExecution[] getCommandFuncs() {
        return commandFuncs;
    }

    public CommandsDirectory getCd() {
        return cd;
    }

    public boolean isTakeable() {
        return takeable;
    }

    public void printItemCommands() {
        Printer.println(String.join("/", commands));
    }

    public String getReasonCantTake() {
        return reasonCantTake;
    }
}
