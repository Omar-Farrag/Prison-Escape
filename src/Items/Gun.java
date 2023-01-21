package Items;

import Strategies.WeaponEffectStrategy;
import Commands.CommandExecution;
import Commands.CommandsDirectory;
import IO.Printer;
import Locations.Armory;

public abstract class Gun extends Item {

    protected WeaponEffectStrategy effectStrategy;

    protected Gun(String name, WeaponEffectStrategy effectStrategy) {
        this.name = name;
        this.commands = new String[] { "shoot" };
        this.commandFuncs = new CommandExecution[] {
                (command, commandRecipient) -> {
                    shoot();
                },
        };
        this.cd = new CommandsDirectory(this);
        this.takeable = false;
        this.reasonCantTake = "BROOOO...What are you gonna do when the officers see you carrying a gun in prison?";
        this.effectStrategy = effectStrategy;
    }

    protected final void shoot() {
        if (Armory.isInsideArmory()) {
            try {
                playSound().join();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            effectStrategy.affect();
            Printer.println("");
        }

        else {
            Printer.println("You got a gun I don't know about or something?? The guns are inside the armory man...");
        }
    }

    protected abstract Thread playSound();

    public void setEffectStrategy(WeaponEffectStrategy effectStrategy) {
        this.effectStrategy = effectStrategy;
    }

}
