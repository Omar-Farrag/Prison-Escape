package General;

import IO.Printer;
import Items.Basketball;
import Music.DJ;
import Music.Track;
import SensorControl.SensorCommand;
import SensorControl.TCP_Client;
import TimeTrackers.TimedQuest;

public class BasketballGame implements Runnable, ThreadTermination {
    private boolean inGame;
    private Basketball basketball;
    private boolean ballShot;
    private Thread t;
    private TimedQuest tq;

    public BasketballGame(Basketball basketball) {
        inGame = false;
        ballShot = false;
        this.basketball = basketball;
        basketball.setBasketballGame(this);
        t = new Thread(this);
        t.setName("BasketBall Thread");
        UserLocation.getInstance().addThread(this);
        t.start();

    }

    public void ballWasShot() {
        synchronized (this) {
            ballShot = true;
        }
        tq.endTimedQuest();
    }

    public boolean isInGame() {
        return inGame;
    }

    @Override
    public void run() {
        try {
            playBasketBall();
        } catch (Exception e) {
            // TODO: handle exception
        }

    }

    private void playBasketBall() throws Exception {
        Printer.println("You will now start playing basketball with the other inmates");
        TCP_Client tc = TCP_Client.getInstance();
        tc.updateSampling(new SensorCommand[] { SensorCommand.SHOOT_HOOP }, (sensorCommand) -> {
            if (inGame)
                basketball.shoot();
        });
        // tc.startSampling();
        inGame = true;
        while (inGame) {
            basketball.resetReceivedBasketball();
            ballShot = false;
            // int waitingTime = -100 * 1000 /
            // GoodnessIndex.getInstance().getGoodnessIndex();
            int counter = 0;
            while (inGame && counter < 3) {
                Thread.sleep(1000);
                counter++;
            }
            basketball.receiveBasketball();
            tq = new TimedQuest(10, () -> {
                checkIfLostBall();
            });
            tq.getThread().join();
        }
        // tc.endSampling();

    }

    private void checkIfLostBall() {
        if (!ballShot) {
            Printer.println("Someone stole the ball from you...Your team is not happy");
            GoodnessIndex.getInstance().increaseGoodnessIndex(5);
            DJ.getInstance().play(Track.BOOS, false, false);
            ballShot = false;
        }
    }

    public void setBasketball(Basketball basketball) {
        this.basketball = basketball;
    }

    public void quitGame() {
        inGame = false;
        basketball.setBasketballGame(null);
        basketball.resetReceivedBasketball();
        tq.endTimedQuest();
    }

    @Override
    public void killThread() {
        quitGame();
    }
}
