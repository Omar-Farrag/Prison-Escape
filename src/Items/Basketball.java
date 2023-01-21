package Items;

import Commands.CommandExecution;
import Commands.CommandsDirectory;
import General.BasketballGame;
import General.GoodnessIndex;
import General.UserLocation;
import IO.Printer;
import Music.DJ;
import Music.Track;

public class Basketball extends Item {

    private BasketballGame basketballGame;
    private boolean received;

    public Basketball() {
        this.name = "basketball";
        this.commands = new String[] { "dribble", "shoot" };
        this.commandFuncs = new CommandExecution[] {
                (command, commandRecipient) -> {
                    dribble();
                },
                (command, commandRecipient) -> {
                    shoot();
                },
        };
        this.cd = new CommandsDirectory(this);
        this.takeable = true;
        this.reasonCantTake = "";
        received = false;
    }

    private void dribble() {
        Printer.println("You grab a basketball and begin dribbling...");
        DJ.getInstance().play(Track.DRIBBLE, false, false);
    }

    public void shoot() {

        if (basketballGame == null) {
            Printer.println("You can't shoot the basketball if you're not even playing");
            return;
        }
        if (!received) {
            Printer.println("You don't have the ball with you right now...wait until someone passes you the ball");
            return;
        }

        basketballGame.ballWasShot();
        Printer.print("[BASKETBALL GAME] You take aim and shoot the ball...\n");
        if (UserLocation.getInstance().beatTheOdds()) {

            Printer.println("[BASKETBALL GAME] And +1");
            try {
                DJ.getInstance().play(Track.BASKETBALL_HOOP, false, false).join();
                DJ.getInstance().play(Track.CHEERS, false, false).join();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            GoodnessIndex.getInstance().decreaseGoodnessIndex(5);

        } else {
            Printer.println("[BASKETBALL GAME] Unfortunately you missed");
            DJ.getInstance().play(Track.BASKETBALL_RIM, false, false);
            DJ.getInstance().play(Track.BOOS, false, false);
            GoodnessIndex.getInstance().increaseGoodnessIndex(1);
        }
    }

    public void receiveBasketball() {
        Printer.println("[BASKETBALL GAME] A fellow inmate passed the ball to you");
        received = true;
    }

    public void resetReceivedBasketball() {
        received = false;
    }

    public void setBasketballGame(BasketballGame basketballGame) {
        this.basketballGame = basketballGame;
    }

    public void setReceived(boolean received) {
        this.received = received;
    }

}
