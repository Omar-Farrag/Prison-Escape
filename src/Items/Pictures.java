package Items;

import Commands.CommandExecution;
import Commands.CommandsDirectory;
import IO.Printer;

public class Pictures extends Item {

    public Pictures() {
        this.name = "pictures";
        this.commands = new String[] { "look" };
        this.commandFuncs = new CommandExecution[] {
                (command, commandRecipient) -> {
                    viewPictures();
                }
        };
        this.cd = new CommandsDirectory(this);
        takeable = true;
    }

    private void viewPictures() {
        Printer.println("You start look at pictures of your wife and kids...."
                + "\nIt makes you reflect on the choices you made on life..."
                + "\nand how you ended up in prison in the first place"
                + "\nyou miss your family more than anything else...");
    }

}
