package Items;

import Strategies.WeaponEffectStrategy;
import Commands.CommandExecution;
import Commands.CommandsDirectory;

public abstract class Projectile extends Item {

    protected WeaponEffectStrategy effectStrategy;

    protected Projectile(String name, WeaponEffectStrategy effectStrategy) {
        this.name = name;
        this.commands = new String[] { "throw" };
        this.commandFuncs = new CommandExecution[] {
                (command, commandRecipient) -> {
                    throwProjectile();
                },
        };
        this.cd = new CommandsDirectory(this);
        this.takeable = true;
        this.reasonCantTake = "";
        this.effectStrategy = effectStrategy;
    }

    protected final void throwProjectile() {
        try {
            playSound().join();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        effectStrategy.affect();
    }

    protected abstract Thread playSound();

    public void setEffectStrategy(WeaponEffectStrategy effectStrategy) {
        this.effectStrategy = effectStrategy;
    }

}
