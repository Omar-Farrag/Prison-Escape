package Items;

import Commands.CommandExecution;
import Commands.CommandsDirectory;
import Constants.Tasks;
import IO.Printer;
import Locations.Kitchen;
import Music.DJ;
import Music.Track;

public class Kettle extends Item {
    private boolean hasWater;
    private Kitchen kitchen;

    public Kettle(Kitchen kitchen) {
        this.name = "Kettle";
        this.commands = new String[] { "fill", "heat", "pour" };
        this.commandFuncs = new CommandExecution[] {
                (command, commandRecipient) -> {
                    fillWithWater();
                },
                (command, commandRecipient) -> {
                    heat();
                },
                (command, commandRecipient) -> {
                    pour();
                },
        };
        this.cd = new CommandsDirectory(this);
        this.takeable = false;
        this.reasonCantTake = "Do you really think you can fit the kettle into your pocket???";
        hasWater = false;
        this.kitchen = kitchen;
    }

    private void fillWithWater() {
        if (hasWater)
            Printer.println("Already has water");
        else {
            DJ.getInstance().play(Track.WATER_DISPENSER, false, false);
            Printer.println("You take the kettle to the water dispenser and fill it up with water");
            hasWater = true;
        }
    }

    private void heat() {
        if (!hasWater)
            Printer.println("Be careful!! Heating the kettle when it's empty can damage it");
        else {
            DJ.getInstance().play(Track.KETTLE_HEATING, false, false);
            Printer.println("You turn on the kettle and the water in it begins boiling");
            kitchen.checkIfTaskCompleted(Tasks.FILL_KETTLE);
        }

    }

    private void pour() {
        Printer.println("You pour water out of the kettle....Looks steaming hot");
        hasWater = false;
    }
}
