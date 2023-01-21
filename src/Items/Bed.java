package Items;

import Commands.CommandExecution;
import Commands.CommandsDirectory;
import General.GoodnessIndex;
import General.InternalClock;
import IO.Inputter;
import IO.Printer;

public class Bed extends Item {

    public Bed() {
        this.name = "bed";
        this.commands = new String[] { "sit", "sleep", "make", "look" };
        this.commandFuncs = new CommandExecution[] {
                (command, commandRecipient) -> sit(),

                (command, commandRecipient) -> sleep(),

                (command, commandRecipient) -> make(),

                (command, commandRecipient) -> lookAtBed(commandRecipient),

        };
        this.cd = new CommandsDirectory(this);
        takeable = false;
    }

    private void lookAtBed(String commandRecipient) {
        Printer.println("It's a bed with a rough, brown blanket and a dirty pillow. Mattress doesn't seem very clean");
    }

    private void sit() {
        Printer.println("You sit on your bed and relax...");
    }

    private void sleep() {
        try {

            Printer.print("How many hours you planning to sleep?: ");
            int response = Integer.parseInt(Inputter.input().trim());

            if (response > 24)
                Printer.println("Calm down brother. You can't sleep for " + response
                        + " hours straight. You a koala or something?");

            else {
                InternalClock.getInstance().passTime(response);
                Printer.println("Wakey Wakey! " + response + " hours have passed");
            }
        } catch (Exception e) {
            // TODO: handle exception
        }

    }

    private void make() {
        Printer.println("You lift the blanket and pillow and start making your bed. Good man");
        GoodnessIndex.getInstance().increaseGoodnessIndex(1);
    }
}
