package Items;

import Commands.CommandExecution;
import Commands.CommandsDirectory;
import IO.Printer;

public class CookingPot extends Item {
    public CookingPot() {
        this.name = "Cooking Pot";
        this.commands = new String[] { "smell" };
        this.commandFuncs = new CommandExecution[] {
                (command, commandRecipient) -> {
                    smellFood();
                },
        };
        this.cd = new CommandsDirectory(this);
        this.takeable = false;
        this.reasonCantTake = "Like cmon what on earth are you going to do with the cooking pot";
    }

    private void smellFood() {
        Printer.println("You lean closer to the cooking pot and finally smell what the rock has been cooking");
    }
}
