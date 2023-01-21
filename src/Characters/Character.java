package Characters;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

import General.CBFunc;
import General.UserLocation;
import IO.Inputter;
import IO.Printer;
import Strategies.HealthBar;;

public abstract class Character implements Conversation {

    /*
     * Initialize name in all child classes
     * Write the needed functions in each child class
     */

    private String name;
    private HealthBar healthBar;
    private boolean isPoliceOfficer;
    private boolean fightable;
    private String reasonCantBeFought;

    private InputStream questionStream;
    private InputStream responseStream;

    private InputStreamReader questionStreamReader;

    private InputStreamReader responseStreamReader;
    private BufferedReader bQSR;
    private BufferedReader bRSR;

    private Queue<CBFunc> callBacks;

    public Character(String name, boolean isPoliceOfficer) {
        this.name = name;
        healthBar = new HealthBar(name);
        this.isPoliceOfficer = isPoliceOfficer;
        fightable = true;
        reasonCantBeFought = "";
        callBacks = new LinkedList<>();
    }

    public Character(String name, boolean isPoliceOfficer, boolean fightable, String reasonCantBeFought) {
        this.name = name;
        healthBar = new HealthBar(name);
        this.isPoliceOfficer = isPoliceOfficer;
        this.fightable = fightable;
        this.reasonCantBeFought = reasonCantBeFought;
        callBacks = new LinkedList<>();
    }

    public abstract void openQuestionStream();

    public abstract void openResponseStream();

    public abstract void introduceCharacter();

    public abstract void endConversation();

    final public void conversate() {

        UserLocation.getInstance().willStartConversation(this);

        openQuestionStream();

        openResponseStream();

        initReaderStreams();

        introduceCharacter();

        readLines();

        endConversation();

        closeAllStreams();

        UserLocation.getInstance().willEndConversating();

        executeCallBacks();

    }

    @Override
    final public void initReaderStreams() {

        questionStreamReader = new InputStreamReader(questionStream);
        responseStreamReader = new InputStreamReader(responseStream);

        bQSR = new BufferedReader(questionStreamReader);
        bRSR = new BufferedReader(responseStreamReader);
    }

    @Override
    final public void readLines() {
        try {
            String line = bQSR.readLine();

            while (line != null) {
                Printer.print("[You] " + line + "\n");

                line = bRSR.readLine();
                Printer.print("[" + name + "] " + line + "\n");

                if (!bQSR.ready()) {
                    Printer.println("");
                    return;
                }

                Printer.print("\n");
                Printer.print("> ");

                line = Inputter.input().toLowerCase().trim();

                while (!line.equals("talk")) {

                    if (line.equals("end conversation")) {
                        Printer.println("");
                        return;
                    }
                    Printer.println("[" + name + "]" + " What's that?? Do you want to to talk to me?");
                    Printer.print("> ");
                    line = Inputter.input().toLowerCase().trim();

                }
                line = bQSR.readLine();
            }
        } catch (Exception e) {
            Printer.print(e.getMessage());
        }

    }

    @Override
    public void closeAllStreams() {
        try {
            questionStream.close();
            responseStream.close();
            questionStreamReader.close();
            responseStreamReader.close();
            bQSR.close();
            bRSR.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    final private void executeCallBacks() {

        while (!callBacks.isEmpty())
            callBacks.remove().callBackFunction();

    }

    public String getName() {
        return name;
    }

    public boolean isDead() {
        if (healthBar.getHealth() == 0)
            return true;
        else
            return false;
    }

    public HealthBar getHealthBar() {
        return healthBar;
    }

    public boolean isPoliceOfficer() {
        return isPoliceOfficer;
    }

    public void setQuestionStream(InputStream questionStream) {
        this.questionStream = questionStream;
    }

    public void setResponseStream(InputStream responseStream) {
        this.responseStream = responseStream;
    }

    @Override
    public String toString() {
        return name;
    }

    public boolean canBeFought() {
        return fightable;
    }

    public String getReasonCantBeFought() {
        return reasonCantBeFought;
    }

    public void callMeWhenDone(CBFunc callback) {
        callBacks.add(callback);
    }

}