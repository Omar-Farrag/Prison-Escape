package Items;

import Commands.CommandExecution;
import Commands.CommandsDirectory;
import IO.Printer;

//zayed
public class Furnace extends Item{

    public Furnace(){
        name="furnace";
        commands=new String[]{"cremate"};
        commandFuncs=new CommandExecution[]{
                (command, commandRecipient) -> {
                    cremateSkeletons();
                },
        };
        cd=new CommandsDirectory (this);
        takeable=false;
        }

    public void cremateSkeletons(){
        Printer.println("The skeletons are now turned into ash");
    }

}

