package Items;

import Commands.CommandExecution;
import Commands.CommandsDirectory;
import IO.Printer;

public class GuardUniform extends Item{

    public GuardUniform(){
        name="guard uniform";
        commands=new String[]{"wear"};
        commandFuncs=new CommandExecution[]{
                (command, commandRecipient) -> {
                    wearUniform();
                },
        };

        cd=new CommandsDirectory(this);
        takeable=true;

    }

    private void wearUniform(){
        Printer.println("You are now disguised as a security guard, dont do anything suspicious");
    }

}
