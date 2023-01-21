package Items;

import Commands.CommandExecution;
import Commands.CommandsDirectory;
import General.UserLocation;
import IO.Printer;
import Locations.LocationNames;
import Music.DJ;
import Music.Track;
import TimeTrackers.TimedQuest;

public class Harmonica extends Item {
    public Harmonica() {
        this.name = "Harmonica";
        this.commands = new String[] { "play", "stop" };
        this.commandFuncs = new CommandExecution[] {
                (command, commandRecipient) -> {
                    playHarmonica();
                },
                (command, commandRecipient) -> {
                    stopHarmonica();
                },
        };
        this.cd = new CommandsDirectory(this);
        this.takeable = true;
        this.reasonCantTake = "";
    }

    private void playHarmonica() {
        Printer.println("You begin playing the harmonica...");
        DJ.getInstance().play(Track.HARMONICA, true, false);
        if (UserLocation.getInstance().getCurrentLocation().getLocationName().equals(LocationNames.CELL.getName()))
            new TimedQuest(2, () -> {
                Printer.println("[INMATE] SOME OF US ARE TRYING TO SLEEP HERE");
            });
    }

    private void stopHarmonica() {
        Printer.println("No longer playing the harmonica");
        DJ.getInstance().stop();
    }

}
