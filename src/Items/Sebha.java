package Items;

import Commands.CommandExecution;
import Commands.CommandsDirectory;
import General.GoodnessIndex;
import IO.Printer;
import Music.DJ;
import Music.Track;

public class Sebha extends Item {
    public Sebha() {
        this.name = "Sebha";
        this.commands = new String[] { "sabbeh", "kabber", "ehmed" };
        this.commandFuncs = new CommandExecution[] {
                (command, commandRecipient) -> {
                    tasbeeh();
                },
                (command, commandRecipient) -> {
                    takbeer();
                },
                (command, commandRecipient) -> {
                    tahmeed();
                },
        };
        this.cd = new CommandsDirectory(this);
        takeable = true;
    }

    private void tasbeeh() {
        DJ.getInstance().play(Track.TASBEEH, true, false);
        Printer.println("Sobhan Allah");
        GoodnessIndex.getInstance().increaseGoodnessIndex(1);
    }

    private void takbeer() {
        DJ.getInstance().play(Track.TAKBEER, true, false);
        Printer.println("Allahu Akbar");
        GoodnessIndex.getInstance().increaseGoodnessIndex(1);
    }

    private void tahmeed() {
        DJ.getInstance().play(Track.TAHMEED, true, false);
        Printer.println("Al Hamdolellah");
        GoodnessIndex.getInstance().increaseGoodnessIndex(1);
    }
}
