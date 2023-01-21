package Items;

import Commands.CommandExecution;
import Commands.CommandsDirectory;
import IO.Printer;

public class Window extends Item {
    public Window() {
        this.name = "window";
        this.commands = new String[] { "look" };

        this.commandFuncs = new CommandExecution[] {
                (command, commandRecipient) -> {
                    lookThroughWindow();
                }
        };
        this.cd = new CommandsDirectory(this);
        takeable = false;
    }

    private void lookThroughWindow() {
        Printer.println("You look through the window and see the prison's open yard below..."
                + "\nThe other prisoners chilling and playing..."
                + "\nThe weather seems fine but it is a bit hot");
    }

}
