package Items;

import Commands.CommandExecution;
import Commands.CommandsDirectory;
import IO.Printer;
import Locations.Library;
import Music.DJ;
import Music.Track;

public class Lamp extends Item {
    boolean isON;
    Library lib;

    public Lamp(Library lib) {
        this.name = "lamp";
        this.commands = new String[] { "turn-on", "turn-off" };
        this.commandFuncs = new CommandExecution[] {
                (command, commandRecipient) -> {
                    switchLamp(command, true);
                },
                (command, commandRecipient) -> {
                    switchLamp(command, false);
                }
        };
        this.cd = new CommandsDirectory(this);
        takeable = false;
        reasonCantTake = "A lamp? cmon man";
        this.lib = lib;
    }

    private void switchLamp(String command, boolean ON) {

        Printer.println("You reach for the lamp's switch to turn it" + (ON ? " ON" : " OFF"));
        try {
            DJ.getInstance().play(Track.SWITCH, false, false).join();
            if (ON)
                Printer.println("You can now see clearly around the table");
            else
                Printer.println("It's so dark right now. It will make it difficult for you to read and write");

            lib.setLightOn(ON);
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
