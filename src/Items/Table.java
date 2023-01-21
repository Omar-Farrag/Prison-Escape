package Items;

import Commands.CommandExecution;
import Commands.CommandsDirectory;
import IO.Printer;
import Music.DJ;
import Music.Track;

public class Table extends Item {
    public Table() {
        this.name = "table";
        this.commands = new String[] { "knock", "sit", "look" };
        this.commandFuncs = new CommandExecution[] {
                (command, commandRecipient) -> {
                    knock();
                },
                (command, commandRecipient) -> {
                    sit();
                },
                (command, commandRecipient) -> {
                    look();
                },
        };
        this.cd = new CommandsDirectory(this);
        takeable = false;
    }

    private void knock() {
        Printer.println("You start knocking on the table with your knuckles");
        DJ.getInstance().play(Track.KNOCK, false, false);
        try {
            Thread.sleep(9000);
        } catch (Exception e) {
            // TODO: handle exception
        }
        DJ.getInstance().play(Track.SHH, false, false);
        Printer.println("[Librarian] SHHHHH!!!! Other inmates are trying to concentrate");
    }

    private void sit() {
        Printer.println("You just grabbed a chair and took a seat by one of many tables");
    }

    private void look() {
        Printer.println("You look at the table and notice a stack of neatly aligned papers and various pens..."
                + "\nThere is also a lamp and a cd player which you can turn on if you like");
    }
}
