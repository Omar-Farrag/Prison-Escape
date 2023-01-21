package Items;

import Commands.CommandExecution;
import Commands.CommandsDirectory;
import General.UserLocation;
import IO.Printer;
import Locations.Kitchen;

public class Cabinet extends Item {

    private Matchbox matchBox;
    private Kitchen kitchen;

    public Cabinet(Kitchen kitchen) {
        this.name = "Cabinet";
        this.commands = new String[] { "look", "take" };
        this.commandFuncs = new CommandExecution[] {
                (command, commandRecipient) -> {
                    look();
                },
                (command, commandRecipient) -> {
                    takeMatchbox();
                },
        };
        this.cd = new CommandsDirectory(this);
        this.takeable = false;
        this.reasonCantTake = "you wanna take a cabinet??? ";
        matchBox = new Matchbox();
        this.kitchen = kitchen;
    }

    private void look() {
        if (kitchen.isBrianPresent()) {
            kitchen.kickUserOutOfKitchen();
        }
        Printer.println("You open up the cabinet and see what's on the inside"
                + "\nThere are all sorts of cooking equipment..."
                + "\nBut one of them grabs your attention: a matchbox");
    }

    private void takeMatchbox() {
        if (kitchen.isBrianPresent()) {
            kitchen.kickUserOutOfKitchen();
        } else {
            UserLocation.getInstance().getMobileInventory().addToInventory(matchBox);
            Printer.println("Matchbox added to your inventory");
        }
    }

    @Override
    public void useItem(String command, String commandRecipient) {
        if (command.equals("take") && commandRecipient.startsWith("matchbox"))
            this.takeMatchbox();
        else
            super.useItem(command, commandRecipient);
    }

}
