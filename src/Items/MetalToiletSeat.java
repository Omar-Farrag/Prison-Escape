package Items;

import Commands.CommandExecution;
import Commands.CommandsDirectory;
import IO.Printer;

public class MetalToiletSeat extends Item {

    public MetalToiletSeat() {

        name = "metal toilet seat";
        commands = new String[] { "sit" };
        commandFuncs = new CommandExecution[] {
                (command, commandRecipient) -> {
                    sit();
                },
                
        };
        cd = new CommandsDirectory(this);
        takeable = false;
    }

    private void sit() {
        Printer.println("You are now sitting on the toilet seat to do your business");
    }

    
}
