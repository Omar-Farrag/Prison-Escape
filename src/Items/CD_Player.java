package Items;

import Commands.CommandExecution;
import Commands.CommandsDirectory;
import IO.Printer;

public class CD_Player extends Item {
    public CD_Player() {
        this.name = "cd_player";
        this.commands = new String[] { "look" };
        this.commandFuncs = new CommandExecution[] {
                (command, commandRecipient) -> {
                    lookAtCDPlayer();
                },
        };
        this.cd = new CommandsDirectory(this);
        takeable = false;
    }

    private void lookAtCDPlayer() {
        Printer.println("You look at the CD Player"
                + "\nLooks a bit old but it seems"
                + "\nto be working..."
                + "\nRight next to the CD Player,"
                + "\nyou see an album with a collection of songs");

    }

}
