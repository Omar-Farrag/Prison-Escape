package Items;

import java.util.ArrayList;

import Commands.CommandExecution;
import Commands.CommandsDirectory;
import IO.Printer;

public class Refridgerator extends Item {

    private ArrayList<String> foodInFridge;

    public Refridgerator() {
        this.name = "Refrigerator";
        this.commands = new String[] { "open", "get" };
        this.commandFuncs = new CommandExecution[] {
                (command, commandRecipient) -> {
                    open();
                },
                (command, commandRecipient) -> {
                    get(commandRecipient);
                },
        };
        this.cd = new CommandsDirectory(this);
        this.takeable = false;
        this.reasonCantTake = "You think you're superman or something to just take the whole refridgerator and leave???";
        foodInFridge = new ArrayList<>() {
            {
                add("chicken");
                add("meat");
                add("cucumbers");
                add("tomatoes");
                add("pickles");
                add("lettuce");
                add("olives");
                add("apples");
                add("juice");
            }
        };
    }

    private void open() {
        Printer.println("You open up the refridgerator door...."
                + "\nThere is chicken, meat, cucumbers, tomatoes,pickles, lettuce, olives, apples,"
                + "\nand many bottles of juice,");
    }

    private boolean get(String commandRecipient) {
        return foodInFridge.contains(commandRecipient);

    }
}
