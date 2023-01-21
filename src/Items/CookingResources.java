package Items;

import Commands.CommandExecution;
import Commands.CommandsDirectory;
import IO.Printer;

import java.util.Random;

public class CookingResources extends Item {
    public CookingResources() {
        name = "cooking resources";
        /*
         * I am not sure if we should be cooking in the underground storage
         * The cook meal function could be very useful in the kitchen tbh
         * For here though we can replace cooking with observing or something like that
         * There is also the default action in which we can take the cooking resources
         * and deliver them to the kitchen
         */
        this.commands = new String[] { "cook" };
        this.commandFuncs = new CommandExecution[] {
                (command, commandRecipient) -> {
                    cookMeal();
                }
        };
        this.cd = new CommandsDirectory(this);
        takeable = false;
    }

    private void cookMeal() {
        Random r = new Random(3);
        int i = r.nextInt();
        switch (i) {
            case 0:
                Printer.println("You made a ratatouille dish");
                break;
            case 1:
                Printer.println("You made a Biryani dish");
                break;
            case 2:
                Printer.println("You made a chicken salad pizza");
                break;

        }
    }

}
