package Items;

import Commands.CommandExecution;
import Commands.CommandsDirectory;
import General.UserLocation;
import IO.Printer;

public class EntranceDoor extends Item {
    boolean locked;

    public EntranceDoor() {
        this.name = "entrace door";
        this.commands = new String[] { "open", "break" };
        this.commandFuncs = new CommandExecution[] {
                (command, commandRecipient) -> {
                    openDoor();
                },
                (command, commandRecipient) -> {
                    breakDoor();
                },
        };
        cd = new CommandsDirectory(this);
        takeable = false;
        locked = true;
    }

    // Function needed to escape
    public void openDoor() {

        if (locked) {
            Printer.println("The door is locked. Bash the entrance door knob with a hammer or find a key");
        } else
            Printer.println("The door is now open, freedom is yours");
    }

    public void breakDoor() {
        // check if the player has a hammer, please correct me if im wrong. The hammer
        // will be added later
        UserLocation u2 = UserLocation.getInstance();
        Inventory freedom = u2.getMobileInventory();
        if (freedom.findItem("hammer").getName().equals("hammer")) { // check this one{
            Printer.println("You have successfully broken open the door, enjoy your freedom");
        } else {
            Printer.println("You dont have a hammer");
        }
    }

}
