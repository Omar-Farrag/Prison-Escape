package General;

import java.util.HashMap;
import java.util.Random;

import Characters.Character;
import IO.Printer;
import Music.DJ;
import Music.Track;
import SensorControl.SensorCommand;
import SensorControl.SensorCommandExecution;
import SensorControl.TCP_Client;
import Strategies.NoRegeneration;
import Strategies.NormalRegeneration;
import TimeTrackers.TimedQuest;

public class Fight implements Runnable, ThreadTermination {
    private Thread t;
    private UserLocation user;
    private Character otherInmate;
    private Random r;
    private HashMap<SensorCommand, SensorCommand> fightMechanics;
    private SensorCommand otherInmatePunch;
    private SensorCommand otherInmateDodge;
    private SensorCommand usersLatestCommand;
    private SensorCommandExecution tcpClientUpdate;
    private SensorCommand[] toSample = new SensorCommand[] { SensorCommand.PUNCH_RIGHT, SensorCommand.PUNCH_LEFT,
            SensorCommand.DODGE_LEFT, SensorCommand.DODGE_RIGHT };

    private TimedQuest tq;
    private TCP_Client tc;
    // private int x;
    private boolean userDead;
    private boolean otherInmateDead;
    private boolean endFight;

    public Fight(Character otherInmate) {
        this.otherInmate = otherInmate;
        userDead = false;
        otherInmateDead = false;
        user = UserLocation.getInstance();

        otherInmate.getHealthBar().setRegenerationStrategy(new NoRegeneration());
        user.getHealthBar().setRegenerationStrategy(new NoRegeneration());

        r = new Random();
        tc = TCP_Client.getInstance();

        tcpClientUpdate = (SensorCommand) -> {
            setUsersLatestCommand(SensorCommand);
        };

        fightMechanics = new HashMap<>();
        fightMechanics.put(SensorCommand.PUNCH_RIGHT, SensorCommand.DODGE_RIGHT);
        fightMechanics.put(SensorCommand.PUNCH_LEFT, SensorCommand.DODGE_LEFT);
        fightMechanics.put(SensorCommand.DODGE_RIGHT, SensorCommand.PUNCH_RIGHT);
        fightMechanics.put(SensorCommand.DODGE_LEFT, SensorCommand.PUNCH_LEFT);

        endFight = false;
        t = new Thread(this);
        t.setName("Fight Thread");
        user.addThread(this);
        t.start();
    }

    public void run() {
        DJ.getInstance().play(Track.BRAWL, true, true);
        // tc.startSampling();
        while (!userDead && !otherInmateDead && !endFight) {
            try {
                tc.updateSampling(new SensorCommand[] { SensorCommand.DODGE_RIGHT, SensorCommand.DODGE_LEFT },
                        tcpClientUpdate);
                defend();

                setUsersLatestCommand(null);

                if (userDead)
                    break;

                tc.updateSampling(new SensorCommand[] { SensorCommand.PUNCH_RIGHT, SensorCommand.PUNCH_LEFT },
                        tcpClientUpdate);
                attack();

                if (otherInmateDead)
                    break;
                setUsersLatestCommand(null);

                Printer.println("user: " + user.getHealthBar().getHealth() + "\n" + "other: "
                        + otherInmate.getHealthBar().getHealth());

            } catch (Exception e) {
                Printer.println(e.getMessage());
            }

        }

        if (!endFight) {
            try {

                if (!userDead) {
                    DJ.getInstance().play(Track.VICTORY, true, false).join();
                }
                user.getHealthBar().setRegenerationStrategy(new NormalRegeneration());
                user.endFight();
                // tc.endSampling();
                DJ.getInstance().stop();

                if (!userDead)
                    user.getPunished();

            } catch (Exception e) {
                // TODO: handle exception
            }
        }
    }

    public synchronized void setUsersLatestCommand(SensorCommand s) {
        usersLatestCommand = s;
    }

    private void defend() throws Exception {
        otherInmatePunch = r.nextBoolean() ? SensorCommand.PUNCH_RIGHT : SensorCommand.PUNCH_LEFT;

        Printer.println(otherInmate.getName() + " is coming at you with a punch from the "
                + (otherInmatePunch == SensorCommand.PUNCH_RIGHT ? "right" : "left"));

        Thread.sleep(6000);

        if (cantDodge())
            getPunched();
        else
            dodge();
    }

    private boolean cantDodge() {
        return usersLatestCommand != fightMechanics.get(otherInmatePunch)
                || UserLocation.getInstance().reverseBeatTheOdds();
    }

    private void getPunched() {
        DJ.getInstance().play(Track.PUNCH, false, false);

        Printer.println("OUCH....THE PUNCH LANDED...");
        if (user.getHealthBar().decreaseHealthBy(25))
            userDead = true;

    }

    private void dodge() {
        DJ.getInstance().play(Track.DODGE, false, false);
        Printer.println("OOF...THAT WAS CLOSE BUT YOU DODGED IT");
    }

    private void attack() throws Exception {
        otherInmateDodge = r.nextBoolean() ? SensorCommand.DODGE_LEFT : SensorCommand.DODGE_RIGHT;
        Printer.println("go on..hit him up");

        Thread.sleep(6000);
        if (cantAttack())
            getDodged();
        else
            punch();
    }

    private boolean cantAttack() {
        return !isPunch() || usersLatestCommand == fightMechanics.get(otherInmateDodge)
                || UserLocation.getInstance().reverseBeatTheOdds();
    }

    private boolean isPunch() {
        return (usersLatestCommand == SensorCommand.PUNCH_LEFT) || (usersLatestCommand == SensorCommand.PUNCH_RIGHT);
    }

    private void punch() {
        DJ.getInstance().play(Track.PUNCH, false, false);
        if (otherInmate.getHealthBar().decreaseHealthBy(25))
            otherInmateDead = true;

        Printer.println("AT IT TIGER... THAT KNOCK WILL MAKE HIM SUFFER");
    }

    private void getDodged() {
        DJ.getInstance().play(Track.DODGE, false, false);
        Printer.println("WELL..." + otherInmate.getName() + " DODGED YOUR ATTACK...");
    }

    public Thread getThread() {
        return t;
    }

    public UserLocation getUser() {
        return user;
    }

    public Character getOtherInmate() {
        return otherInmate;
    }

    public Random getR() {
        return r;
    }

    public HashMap<SensorCommand, SensorCommand> getFightMechanics() {
        return fightMechanics;
    }

    public SensorCommand getOtherInmatePunch() {
        return otherInmatePunch;
    }

    public SensorCommand getOtherInmateDodge() {
        return otherInmateDodge;
    }

    public SensorCommand getUsersLatestCommand() {
        return usersLatestCommand;
    }

    public SensorCommandExecution getTcpClientUpdate() {
        return tcpClientUpdate;
    }

    public SensorCommand[] getToSample() {
        return toSample;
    }

    public TimedQuest getTq() {
        return tq;
    }

    public TCP_Client getTc() {
        return tc;
    }

    public boolean isUserDead() {
        return userDead;
    }

    public boolean isOtherInmateDead() {
        return otherInmateDead;
    }

    @Override
    public void killThread() {
        // TODO Auto-generated method stub
        endFight = true;

    }

    // public static void main(String[] args) {
    // UserLocation ul = UserLocation.getInstance();
    // ul.updateSensorInfo("sensor information");
    // Character otherInmate = new Steve("Steve");

    // Fight f = new Fight(otherInmate);
    // String response = "";
    // while (true) {
    // response = Inputter.input();
    // if (response.equals("dodge right"))
    // f.usersLatestCommand = SensorCommand.DODGE_RIGHT;
    // }
    // }
}
