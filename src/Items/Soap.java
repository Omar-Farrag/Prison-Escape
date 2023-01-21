package Items;

import Commands.CommandExecution;
import Commands.CommandsDirectory;
import General.UserLocation;
import IO.Printer;

public class Soap extends Item {

    public Soap() {

        name = "soap";
        commands = new String[] { "clean", "duplicate" };
        commandFuncs = new CommandExecution[] {
                (command, commandRecipient) -> {
                    clean();
                },
                (command, commandRecipient) -> {
                    duplicatekey();
                },

        };
        cd = new CommandsDirectory(this);
        takeable = true;
    }

    private void clean() {
        Printer.println("You are now using the soap to clean your hands");
    }

    private void duplicatekey() {
        Item cellKey = UserLocation.getInstance().getMobileInventory().findItem("cell key");
        if (cellKey == null)
            Printer.println(
                    "There is no cell key in your inventory right now...How will you duplicate what you don't even have...tick tock..");

        else if (!UserLocation.getInstance().getMobileInventory().addToInventory(new DuplicateKey()))
            Printer.println("There is no space in your inventory to add the duplicate key");
        else
            Printer.println("You are now duplicating the cell key using the soap");
    }

}
