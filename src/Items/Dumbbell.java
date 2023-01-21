package Items;

import General.GoodnessIndex;
import General.WorkOutSession;
import IO.Printer;

import java.util.Random;

import Commands.CommandExecution;
import Commands.CommandsDirectory;
import Music.DJ;
import Music.Track;

public class Dumbbell extends Item {
    private WorkOutSession workOutSession;
    private String[] messages;

    public Dumbbell() {
        this.name = "dumbbell";
        this.commands = new String[] { "lift" };
        this.commandFuncs = new CommandExecution[] {
                (command, commandRecipient) -> {
                    lift();
                },
        };
        this.cd = new CommandsDirectory(this);
        this.takeable = false;
        this.reasonCantTake = "Nobody is allowed to take the dumbbells away from the open yard...";
        messages = new String[] {
                "+1 At it boss...show the other inmates how it's done",
                "+1 Dem muscles are getting better",
                "+1 Other inmates are like is this guy even human",
                "+1 Starting to look in shape keep it up",
                "+1 Nobody is messing around with you from now on",
                "+1 Cmon man you're making the other inmates feel bad",
        };
    }

    public void setWorkOutSession(WorkOutSession workOutSession) {
        this.workOutSession = workOutSession;
    }

    public void lift() {
        if (workOutSession == null) {
            Printer.println("You're not currently training...start training first then we can talk");
        } else {
            Printer.println("[WORKOUT SESSION] " + getMessage());
            DJ.getInstance().play(Track.REP, false, false);
            GoodnessIndex.getInstance().decreaseGoodnessIndex(1);
            workOutSession.liftedOnce();
        }

    }

    private String getMessage() {
        return messages[new Random().nextInt(0, messages.length)];
    }

}
