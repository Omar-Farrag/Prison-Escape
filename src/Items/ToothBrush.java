package Items;

import Commands.CommandExecution;
import Commands.CommandsDirectory;
import General.GoodnessIndex;
import IO.Printer;

public class ToothBrush extends Item {

    public ToothBrush() {

        this.name = "toothbrush";
        this.commands = new String[] { "brush" };
        this.commandFuncs = new CommandExecution[] {
                (command, commandRecipient) -> {
                    brushTeeth();
                }
        };
        this.cd = new CommandsDirectory(this);
        takeable = true;
    }

    public void brushTeeth() {
        Printer.println("You begin brushing your teeth...They're looking shiny now");
        GoodnessIndex.getInstance().increaseGoodnessIndex(1);
    }

}
