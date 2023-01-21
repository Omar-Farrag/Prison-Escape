package Items;

import Commands.CommandExecution;
import Commands.CommandsDirectory;
import IO.Printer;
import Music.DJ;
import Music.Track;

public class Matchbox extends Item {
    private boolean isLit;

    public Matchbox() {
        this.name = "Matchbox";
        this.commands = new String[] { "light", "extinguish" };
        this.commandFuncs = new CommandExecution[] {
                (command, commandRecipient) -> {
                    light();
                },
                (command, commandRecipient) -> {
                    extinguish();
                },
        };
        this.cd = new CommandsDirectory(this);
        this.takeable = true;
        this.reasonCantTake = "";
        isLit = false;
    }

    private void light() {
        DJ.getInstance().play(Track.MATCH_LIT, false, false);
        Printer.println("You take a match from the matchbox and light it up");
    }

    private void extinguish() {
        Printer.println("You blow at the match until it's extinguished");
    }

    public boolean isLit() {
        return isLit;
    }
}
