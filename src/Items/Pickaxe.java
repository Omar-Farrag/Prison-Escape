package Items;

import Commands.CommandExecution;
import Commands.CommandsDirectory;
import General.GoodnessIndex;
import IO.Printer;

public class Pickaxe extends Item {
    public Pickaxe() {
        name = "pickaxe";
        this.commands = new String[] { "mine" };
        this.commandFuncs = new CommandExecution[] { // how do i get re
                (command, commandRecipient) -> {
                    mineCoal();
                }
        };
        this.cd = new CommandsDirectory(this);
        takeable = false;
    }

    public void mineCoal() {
        Printer.println("You are minining coal with you pickaxe...very entertaining");
        GoodnessIndex.getInstance().increaseGoodnessIndex(10);
    }

    public String getName() {
        return name;
    }

    public String toString() {
        return name;
    }

}
