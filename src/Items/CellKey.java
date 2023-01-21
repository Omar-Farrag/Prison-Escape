package Items;

import Commands.CommandExecution;
import Commands.CommandsDirectory;
import General.UserLocation;
import IO.Printer;
import TimeTrackers.TimedQuest;

public class CellKey extends Item {

    private boolean keyReturned;

    public CellKey() {

        name = "cell key";
        commands = new String[] { "steal", "return", "duplicate" };
        commandFuncs = new CommandExecution[] {
                (command, commandRecipient) -> {
                    steal();
                },
                (command, commandRecipient) -> {
                    returnKey();
                },
                (command, commandRecipient) -> {
                    duplicateKey();
                },

        };

        cd = new CommandsDirectory(this);
        takeable = true;
        keyReturned = true;
    }

    private void duplicateKey() {
        if (!UserLocation.getInstance().getMobileInventory().contains("cell key"))
            Printer.println("There is no cell key in your inventory...you need to steal first big guy");
        else if (!UserLocation.getInstance().getMobileInventory().contains("soap"))
            Printer.println(
                    "There is no soap in your inventory right now...How else would you duplicate the key...tick tock..");

        else if (!UserLocation.getInstance().getMobileInventory().addToInventory(new DuplicateKey()))
            Printer.println("There is no space in your inventory to add the duplicate key");
        else
            Printer.println("You quickly make a copy of the cell key by pressing it against the soap...Cafuuu");
    }

    private void returnKey() {
        keyReturned = true;
        UserLocation.getInstance().getMobileInventory().removeFromInventory(this);
        Printer.println("Cell key returned");
    }

    private void steal() {
        Printer.println(" You grab the cell key and hide it in your pockets..");
        UserLocation.getInstance().getMobileInventory().addToInventory(this);
        Printer.println(
                "Bear in mind that if you don't return the key within 30 seconds, they will notice that you stole it...");
        keyReturned = false;
        new TimedQuest(30, () -> {
            if (keyReturned || UserLocation.getInstance().isFighting())
                return;
            if (UserLocation.getInstance().isFighting()) {
                keyReturned = true;
                return;
            }
            if (UserLocation.getInstance().isCurrentlyConversating()) {
                UserLocation.getInstance().getTalkingWith().callMeWhenDone(() -> getCaught());
                return;
            }

            getCaught();
        });

    }

    private void getCaught() {
        Printer.println("[OFFICER] HEYYY!!! GET BACK HERE YOU THIEF");
        UserLocation.getInstance().getPunished();
        UserLocation.getInstance().getMobileInventory().removeFromInventory(this);
        keyReturned = true;
    }
}
