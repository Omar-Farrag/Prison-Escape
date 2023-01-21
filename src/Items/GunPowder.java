package Items;

import Commands.CommandExecution;
import Commands.CommandsDirectory;
import IO.Printer;

//zayed - Underground storage
public class GunPowder extends Item{
    public GunPowder(){
        this.name="gunpowder";
        this.commands=new String[]{"pour"};
        this.commandFuncs=new CommandExecution[]{
                (command, commandRecipient) -> {
                    pourGunpowder();
                },
        };
        this.cd=new CommandsDirectory(this);
        takeable=true;
    }
    private void pourGunpowder(){
        Printer.println("You begin pouring gunpowder all around the place...You can blow up the whole thing when your're ready");
    }

}
