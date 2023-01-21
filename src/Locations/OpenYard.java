package Locations;

import Commands.CommandExecution;
import Commands.CommandsDirectory;
import Constants.Days;
import Constants.GoodnessLevel;
import General.BasketballGame;
import General.Fight;
import General.InternalClock;
import General.UserLocation;
import General.WorkOutSession;
import IO.Printer;
import Items.*;
import Music.DJ;
import Music.Track;
import ObserversAndSubjects.Message;
import States.NotVisitedState;
import States.PermittedToEnter;
import TimeTrackers.Time;
import TimeTrackers.TimedQuest;

import java.util.Random;

import Characters.*;
import Characters.Character;

public class OpenYard extends Location {

    private BasketballGame basketballGame;
    private WorkOutSession workOutSession;
    private Dumbbell dumbbell;
    private Basketball basketball;
    private boolean allowedToPlayBasketBall;
    private boolean allowedToWorkOut;
    private String[] bmMessages;

    public OpenYard() {
        description = "This is the prison's open yard...Right in the center of the open yard is the gym..."
                + "\nYou will find a few dumbells there which you can use when nobody else is using them"
                + "\nFurther down the back there is a small basketball court where the other inmates play"
                + "\nApart from that, you can just go ahead and socialize with the other inmates";
        locationName = LocationNames.OPEN_YARD.getName();
        persons = new Character[] {
                new Chris(),
                new Andy(),
                new Luke(),
                new Red(),
                new Brock(),
        };
        items = new Item[] {
                basketball = new Basketball(),
                dumbbell = new Dumbbell(),
        };
        reasonCantEnter = "";
        commands = new String[] { "play", "quit", "train", "end" };
        commandFuncs = new CommandExecution[] {
                (command, commandRecipient) -> {
                    playBasketBall();
                },
                (command, commandRecipient) -> {
                    quitGame();
                },
                (command, commandRecipient) -> {
                    train();
                },
                (command, commandRecipient) -> {
                    endWorkOut(commandRecipient);
                },
        };
        cd = new CommandsDirectory(this);
        visitationState = new NotVisitedState();
        entryPermissionState = new PermittedToEnter();
        needsEmployment = false;
        currentlyEmployed = false;
        hint = "Just have fun here";
        locationTrack = Track.OPEN_YARD;
        requiredGoodness = GoodnessLevel.any;
        openingTime = new Time(Days.ALLDAYS, 10, 0, 0);
        currentTime = InternalClock.getInstance().getTime();
        closingTime = new Time(Days.ALLDAYS, 15, 0, 0);
        this.allowedToLeave = true;
        allowedToPlayBasketBall = true;
        allowedToWorkOut = true;
        bmMessages = new String[] {
                "Hey there boyyy...Are you scared?? You missing mommy?? PERHAPS WE HAVEN'T GIVEN YOU A PROPER WELCOME!!!",
                "AYOO GUYS...Has anyone told this newbie what we do to newcomers...Let me answer that...WE BREAK THEIR BONES!!",
                "You're a long way from home kiddo",
                "YOU MESSED WITH THE WRONG GUY!!!",
                "I RUN THIS PLACE...ALL NEWCOMERS GOTTA PAY HOMAGE",
                "LEMME HAVE SOME FUN WITH THIS NEWBIE",
                "I AM GOING TO DELETE YOU",
        };
    }

    private void playBasketBall() {
        if (workOutSession != null) {
            Printer.println("Are you some sort of magician who can both workout and play basketball??");
            return;
        }
        if (!allowedToPlayBasketBall) {
            Printer.println(
                    "So what, other inmates don't get to play because you,'THE KING', wants to play basketball...Come back later man");
            return;
        }
        if (basketballGame != null) {
            Printer.println("You are already in a basketball game");
            return;
        }
        if (UserLocation.getInstance().reverseBeatTheOdds()) {
            Printer.println("[Other Inmate] Go away barbie...This ain't the sport for you");
            DJ.getInstance().play(Track.INMATES_LAUGHING, false, false);
            return;
        }
        basketballGame = new BasketballGame(basketball);
        allowedToLeave = false;
        reasonCantLeave = "You're currently in the middle of a basketball game";
        allowedToPlayBasketBall = false;
    }

    private void quitGame() {
        if (basketballGame == null)
            Printer.println("You are not currently playing baketball so you can't quit");
        else {
            Printer.println("You just quit the basketball game");
            basketballGame.quitGame();
            basketballGame = null;
            allowedToLeave = true;
        }
    }

    private void train() {
        if (basketballGame != null) {
            Printer.println("Are you some sort of magician who can both workout and play basketball??");
            return;

        } else if (workOutSession != null) {
            Printer.println("You are already working out");
            return;
        } else if (!allowedToWorkOut) {
            Printer.println("Man don't be selfish...Give the other inmates a chance to work out too...Come back later");

        } else if (UserLocation.getInstance().reverseBeatTheOdds()) {
            Printer.println(
                    "[Other Inmate] Boy I swear to God if you don't leave I'll break your bones (says it while lifting 50kg in one hand");
        } else {
            workOutSession = new WorkOutSession(dumbbell, this);
            allowedToLeave = false;
            reasonCantLeave = "You're currently in the middle of a workout";
            allowedToWorkOut = false;
        }

    }

    public void endWorkOut() {
        endWorkOut("workout");
    }

    public void endWorkOut(String commandRecipient) {
        if (!commandRecipient.equals("workout"))
            return;
        if (workOutSession == null)
            Printer.println("You are not currently training so you can't quit");
        else {
            workOutSession.endWorkout();
            workOutSession = null;
            allowedToLeave = true;
            Printer.println("You ended your workout");
        }
    }

    @Override
    public void timeUpdate(Message m) {
        if (outsideWorkingHours() && basketballGame != null) {
            Printer.println("Well that's a bummer, the open yard is about to close so we gotta wrap things up");
            quitGame();
            allowedToLeave = true;
        }
        if (outsideWorkingHours() && workOutSession != null) {
            Printer.println("Well that's a bummer, the open yard is about to close so we gotta wrap things up");
            endWorkOut();
            allowedToLeave = true;
        }
        Fight brawl = UserLocation.getInstance().getBrawl();

        if (brawl != null) {
            try {
                brawl.getThread().join();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        super.timeUpdate(m);
        allowedToWorkOut = true;
        allowedToPlayBasketBall = true;
    }

    public void getJumped() {
        if (UserLocation.getInstance().reverseBeatTheOdds()) {
            new TimedQuest(10, () -> {
                if (UserLocation.getInstance().getBrawl() == null)
                    orchestrateFight();
            });
        }
    }

    private void orchestrateFight() {
        UserLocation ul = UserLocation.getInstance();
        if (ul.isCurrentlyConversating()) {
            ul.getTalkingWith().callMeWhenDone(() -> {
                orchestrateFight();
            });
            return;
        }
        if (basketballGame != null)
            quitGame();
        if (workOutSession != null)
            endWorkOut();
        if (ul.getCurrentLocation().equals(this)) {

            Character toFightYou = persons[new Random().nextInt(0, persons.length)];
            Printer.println("[" + toFightYou.getName() + "] " + getMessage());

            ul.startFight(toFightYou.getName());
        }
    }

    private String getMessage() {
        return bmMessages[new Random().nextInt(0, bmMessages.length)];
    }
}
