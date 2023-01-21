package Items;

import Commands.CommandExecution;
import Commands.CommandsDirectory;
import IO.Printer;

public class WorkBench extends Item {

    public WorkBench() {

        name = "Work Bench";
        commands = new String[] { "sit", "observe" };
        commandFuncs = new CommandExecution[] {
                (command, commandRecipient) -> {
                    sit();
                },
                (command, commandRecipient) -> {
                    observe();
                },
        };
        cd = new CommandsDirectory(this);
        takeable = false;
    }

    private void sit() {
        Printer.println("You sat on a chair infront of your work bench to work on something");
    }

    public void observe() {
        Printer.println("You look around the work bench and you see a bunch of tools that could help you build something ");
    }
}
