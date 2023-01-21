package Items;

import Commands.CommandExecution;
import Commands.CommandsDirectory;
import IO.Printer;

public class Note extends Item {

    public Note() {
        name = "note";
        commands = new String[] { "read" };
        commandFuncs = new CommandExecution[] {
                (command, commmandRecipient) -> {
                    readNote();
                }
        };
        cd = new CommandsDirectory(this);
        takeable = false;
        reasonCantTake = "Man...Didn't the guy tell you not to take the note? huh?";
    }

    private void readNote() {
        Printer.println(""
                + "\nIf you found this note then congrats: you may have a chance to make it out of this hellhole alive..."
                + "\nMy name is Ray Breslin[not my real name] and I used to be an inmate here...I had a plan to escape but"
                + "\ndidn't need it...I was freed on parole so I am now a free man...Since I don't want my meticulous escape "
                + "\nplan to go to waste, I decided to write it down on a note and hide for future inmates...Use this knowledge"
                + "\nto escape and keep the note where it is for future inmates...");

        Printer.println(""
                + "\nThere are 7 tools you need that are scattered around the prison...To escape, you will need to find each"
                + "\none of them then use them all in your escape...These are the items:"
                + "\nRope"
                + "\nMatches"
                + "\nSmoke Bombs"
                + "\nGuard Uniform"
                + "\nDuplicate Key"
                + "\nHammer");

        Printer.println("You will also need to boobytrap the underground storage");

        Printer.println("Good Luck"
                + "\nRay Breslin"
                + "\n20/4/1992");
    }
}
