package Items;

import Commands.CommandExecution;
import Commands.CommandsDirectory;
import IO.Printer;

//zayed
public class Skeletons extends Item{
    public Skeletons(){
        name="Skeleton";
        commands=new String[]{"break"};
        commandFuncs=new CommandExecution[]{
                (commands, commandRecipient) -> {
                    breakSkeleton();
                },
        };
        cd=new CommandsDirectory(this);
        takeable=false;
    }

    public void breakSkeleton(){
        Printer.println("You broke the skeleton!");
    }

}
