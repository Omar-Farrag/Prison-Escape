package Items;

import Commands.CommandExecution;
import Commands.CommandsDirectory;
import General.UserLocation;
import IO.Printer;
import Music.DJ;
import Music.Track;
import TimeTrackers.TimedQuest;

public class Tea extends Item {
    private static boolean prepared = false;
    private static boolean injected = false;
    private UserLocation ul;

    public Tea() {
        this.name = "Tea";
        this.commands = new String[] { "prepare", "inject" };
        this.commandFuncs = new CommandExecution[] {
                (command, commandRecipient) -> {
                    prepareTea();
                },
                (command, commandRecipient) -> {
                    injectMelatonin(commandRecipient);
                },
        };
        this.cd = new CommandsDirectory(this);
        this.takeable = true;
        this.reasonCantTake = "";
    }

    private void prepareTea() {
        DJ.getInstance().play(Track.TEA, false, false);
        Printer.println("You grab two empty cups and begin preparing tea");
        prepared = true;
        injected = false;
        dumpTeaAfterHour();
    }

    private void injectMelatonin(String commandRecipient) {
        if (ul == null)
            ul = UserLocation.getInstance();

        // String firstWord = commandRecipient.split(" ")[0] ;
        if (!commandRecipient.startsWith("anesthetic drugs"))
            Printer.println("Brother what exactly are you trying to inject in the tea????");

        else if (!prepared)
            Printer.println("You did not prepare the tea yet...Prepare it first and add it to your inventory");

        else if (ul.getMobileInventory().findItem("anesthetic drugs") == null)
            Printer.println(
                    "There are no anesthetic drugs in your inventory...How are you gonna inject them if you don't even have them...");
        else {
            Printer.println("Anesthetic drugs injected in tea...");
            injected = true;
        }

    }

    public void dumpTeaAfterHour() {
        if (ul == null)
            ul = UserLocation.getInstance();
        new TimedQuest(120, () -> {
            Printer.println("The tea got too cold...It will now be thrown away");
            Item tea = ul.getMobileInventory().findItem("tea");
            ul.getMobileInventory().removeFromInventory(tea);
            prepared = false;
            injected = false;
        });
    }

    public static boolean isPrepared() {
        return prepared;
    }

    public static boolean isInjected() {
        return injected;
    }

}
