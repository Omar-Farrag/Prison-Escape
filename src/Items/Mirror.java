package Items;

import Commands.CommandExecution;
import Commands.CommandsDirectory;
import General.UserLocation;
import IO.Printer;
import Music.DJ;
import Music.Track;

public class Mirror extends Item {

    private boolean broken;

    public Mirror() {
        this.name = "mirror";
        this.commands = new String[] { "observe", "break" };
        this.commandFuncs = new CommandExecution[] {
                (command, commandRecipient) -> {
                    observe();
                },
                (command, commandRecipient) -> {
                    breakMirror();
                }
        };
        this.cd = new CommandsDirectory(this);
        broken = false;
        takeable = false;
    }

    private void observe() {
        if (!broken) {
            Printer.println("You look in the mirror and see your reflection..." +
                    "\nyou see a handsome man standing there..." +
                    "\nOh wait.That's you !!");
        } else
            Printer.println(
                    "You look in the mirror but all you see is bits and pieces of a monstrous figure which used to be a free, innocent man");
    }

    private void breakMirror() {
        if (!broken) {
            Printer.println("After staring in the mirror for a while,"
                    + "\nyou finally lose your mind and punch the mirror...");
            try {
                DJ.getInstance().play(Track.GlassBreak, false, false).join();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                System.out.println(e.getMessage());
                e.printStackTrace();
            }

            Printer.println("You bleed for a while but then it stops");
            UserLocation.getInstance().getHealthBar().decreaseHealthBy(10);
            broken = true;
        } else if (broken) {
            Printer.println("You already broke it in a moment of anger");
        }
    }

}
