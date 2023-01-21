package Items;

import Commands.CommandExecution;
import Commands.CommandsDirectory;
import IO.Printer;
import Music.DJ;
import Music.Track;

public class HandSaw extends Item {

    public HandSaw() {

        name = "Hand saw";
        commands = new String[] { "cut" };
        commandFuncs = new CommandExecution[] {
                (command, commandRecipient) -> {
                    cutWood();
                },
                
        };
        cd = new CommandsDirectory(this);
        takeable = false;
    }

    private void cutWood() {
        Printer.println(" You grab the Hand saw to cut a piece of wood");

        DJ.getInstance().play(Track.HandSawEffect, false, false);
        try {
            Thread.sleep(9000);
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

   
}
