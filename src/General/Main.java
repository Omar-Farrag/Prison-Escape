package General;

import Escape.EscapeSequence;
import IO.Inputter;
import IO.Printer;
// import Music.DJ;
// import Music.Track;
import Music.DJ;
import Music.Track;
import SensorControl.TCP_Client;
import TimeTrackers.TimedQuest;

public class Main {

    private static boolean beganEscape = false;

    public static void main(String[] args) {

        Printer.println("");
        Printer.println("");

        Printer.println("Welcome to Prison Escape...type start to begin the game");
        Printer.print("> ");
        String userInput = Inputter.input().toLowerCase();

        while (!userInput.equals("start")) {
            Printer.println("Incorrect phrase! Try again");
            Printer.print("> ");
            userInput = Inputter.input().toLowerCase();
        }

        UserLocation ul = UserLocation.getInstance();
        Printer.println("");

        Printer.println("Let's get you started. Your sensor information is needed");
        ul.updateSensorInfo("sensor information");

        DJ.getInstance().play(Track.SIREN, true, false);
        try {
            Printer.println("You just got busted for financial fraud... You are being transported to prison...");
            Thread.sleep(6000);
            DJ.getInstance().stop();
        } catch (Exception e) {
            // TODO: handle exception
        }
        TCP_Client tc = TCP_Client.getInstance();

        while (!beganEscape) {
            Printer.print("> ");
            userInput = Inputter.input();
            ul.perform(userInput);
        }
        UserLocation.getInstance().getHealthBar().killThread();
        UserLocation.getInstance().terminateActiveThreads();
        GoodnessIndex.getInstance().killThread();
        InternalClock.getInstance().killThread();
        DJ.getInstance().killThread();
        tc.killThread();
        TimedQuest.terminateActiveTimedQuests();

        new EscapeSequence();

    }

    public static void escape() {
        beganEscape = true;
    }
}
