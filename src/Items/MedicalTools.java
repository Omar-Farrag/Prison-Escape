package Items;

import Commands.CommandExecution;
import Commands.CommandsDirectory;
import IO.Printer;

public class MedicalTools extends Item {

    public MedicalTools() {

        name = "Medical Tools";
        commands = new String[] { "look" };
        commandFuncs = new CommandExecution[] {
                (command, commandRecipient) -> {
                    look();
                },
                
        };
        cd = new CommandsDirectory(this);
        takeable = false;
    }

    private void look() {
        Printer.println(" You notice needles , scissors and other medical equipment stored in a glass box not within your reach");
    }

   
}
