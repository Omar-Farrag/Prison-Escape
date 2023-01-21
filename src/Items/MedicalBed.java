package Items;

import Commands.CommandExecution;
import Commands.CommandsDirectory;
import IO.Printer;

public class MedicalBed extends Item {

    public MedicalBed() {

        name = "Medical bed";
        commands = new String[] { "lay" };
        commandFuncs = new CommandExecution[] {
                (command, commandRecipient) -> {
                    layonbed();
                },
                
        };
        cd = new CommandsDirectory(this);
        takeable = false;
    }

    private void layonbed() {
        Printer.println(" You are now laying on the Medical Bed");
    }

   
}
