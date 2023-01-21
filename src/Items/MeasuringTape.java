package Items;

import Commands.CommandExecution;
import Commands.CommandsDirectory;
import IO.Printer;

public class MeasuringTape extends Item {

    public MeasuringTape() {

        name = "measuring tape";
        commands = new String[] { "Measure" };
        commandFuncs = new CommandExecution[] {
                (command, commandRecipient) -> {
                    MeasureWood();
                },

        };
        cd = new CommandsDirectory(this);
        takeable = false;
    }

    private void MeasureWood() {
        Printer.println("You begin to exntend the measuring tape to meaure the length of the wood");
    }

}
