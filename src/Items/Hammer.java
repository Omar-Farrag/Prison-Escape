package Items;

import Commands.CommandExecution;
import Commands.CommandsDirectory;
import IO.Printer;
import Music.DJ;
import Music.Track;

public class Hammer extends Item {

    public Hammer() {

        name = "Hammer";
        commands = new String[] { "swing ,swing2" };
        commandFuncs = new CommandExecution[] {
                (command, commandRecipient) -> {
                    swing();
                    swing2();
                },

        };
        cd = new CommandsDirectory(this);
        takeable = true;
    }

    private void swing() {
        Printer.println("You swing the hammer to drive a nail into a piece of wood");

        DJ.getInstance().play(Track.HammeringNail, false, false);
        try {
            Thread.sleep(9000);
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    private void swing2() {
        Printer.println("You swing the hammer to break the glass");

        try {
            DJ.getInstance().play(Track.GlassBreak, false, false).join();
            // Thread.sleep(9000);
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

}
