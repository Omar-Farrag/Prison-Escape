package Items;

import Commands.CommandExecution;
import Commands.CommandsDirectory;
import General.UserLocation;
import IO.Inputter;
import IO.Printer;

public class Paper extends Item {

    String content;

    public Paper() {
        content = "";
        this.name = "paper";
        this.commands = new String[] { "write", "read", "draw", "clear" };
        this.commandFuncs = new CommandExecution[] {
                (command, commandRecipient) -> {
                    writeOnPaper();
                },
                (command, commandRecipient) -> {
                    readPaper();
                },
                (command, commandRecipient) -> {
                    drawOnPaper();
                },
                (command, commandRecipient) -> {
                    clearPaper();
                }
        };
        this.cd = new CommandsDirectory(this);
        takeable = true;
    }

    private void clearPaper() {
        content = "";
        Printer.println("Paper Content Erased");
    }

    private void writeOnPaper() {

        if (!UserLocation.getInstance().getMobileInventory().contains("Pen"))
            Printer.println("So what you're gonna write on the paper with your fingers?? Man grab a pen first");
        else {
            Printer.print("What do you wanna write on the paper?:\n");
            content += Inputter.input() + "\n";
            Printer.println("");
        }
    }

    private void readPaper() {
        Printer.print("[Paper Content]\n");
        Printer.println(content);
    }

    private void drawOnPaper() {
        Printer.print("What would you like to draw on the paper?:\n");
        content += "\n[Drawing]\n" + Inputter.input() + "\n";
    }
}
