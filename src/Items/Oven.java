package Items;

import Commands.CommandExecution;
import Commands.CommandsDirectory;
import Constants.Tasks;
import General.UserLocation;
import IO.Printer;
import Locations.Kitchen;
import Music.DJ;
import Music.Track;

public class Oven extends Item {
    private Kitchen kitchen;

    public Oven(Kitchen kitchen) {
        this.name = "Oven";
        this.commands = new String[] { "touch", "bake" };
        this.commandFuncs = new CommandExecution[] {
                (command, commandRecipient) -> {
                    touchOven();
                },
                (command, commandRecipient) -> {
                    bake();
                },
        };
        this.cd = new CommandsDirectory(this);
        this.takeable = false;
        this.reasonCantTake = "sure sure nobody will notice you are carrying an oven around";
        this.kitchen = kitchen;
    }

    private void touchOven() {
        DJ.getInstance().play(Track.SKIN_BURN, false, false);
        Printer.println("Out of curiosity, you proceed to touch the oven and get a skin burn because of how hot it is");
        UserLocation.getInstance().getHealthBar().decreaseHealthBy(5);
    }

    private void bake() {
        Printer.println("You put the dough in the oven and watch it as it bakes...smells amazing though");
        kitchen.checkIfTaskCompleted(Tasks.BAKE_DOUGH);
    }
}
