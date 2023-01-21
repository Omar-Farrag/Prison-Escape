package Strategies;

import General.ThreadTermination;
import General.UserLocation;
import IO.Printer;
import Locations.LocationNames;
import Music.DJ;
import Music.Track;

public class HealthBar implements Runnable, ThreadTermination {
    private int health;
    private RegenerationStrategy regenerationStrategy;
    private Thread t;
    private String characterName;
    private boolean stop;

    // private static HealthBar instance;

    public HealthBar(String characterName) {
        this.characterName = characterName;
        health = 100;
        regenerationStrategy = new NormalRegeneration();
        stop = false;
        if (!characterName.equals("You"))
            return;
        t = new Thread(this);
        t.setName("HEALTH BAR");
        t.start();
    }

    @Override
    public void run() {
        while (!stop) {
            try {
                int counter = 0;
                while (counter < 10 && !stop) {
                    Thread.sleep(1000);
                    counter++;
                }
                increaseHealthBy(regenerationStrategy.getRegeneratedAmount());
            } catch (Exception e) {
                Printer.println(e.getMessage());
            }
        }
    }

    public int getHealth() {
        return health;
    }

    public synchronized void increaseHealthBy(int amount) {
        health = health + amount > 100 ? 100 : health + amount;
    }

    public synchronized boolean decreaseHealthBy(int amount) {

        if (amount < 0)
            return false;
        if (health - amount <= 0) {
            health = 0;
            die();
            return true;
        } else {
            health -= amount;
            return false;
        }
    }

    private void die() {
        Printer.println(characterName + " just died...." + characterName + " will now be taken to the infirmary.");
        if (characterName.toLowerCase().trim().equals("you")) {
            try {
                // DJ.getInstance().stop();
                DJ.getInstance().play(Track.DEATH_SOUND, true, false).join();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            UserLocation.getInstance().fastTravel(LocationNames.INFIRMARY.getName());
            UserLocation.getInstance().getMobileInventory().clear();
        }
        health = 100;
    }

    public void setRegenerationStrategy(RegenerationStrategy regenerationStrategy) {
        this.regenerationStrategy = regenerationStrategy;
    }

    @Override
    public void killThread() {
        // TODO Auto-generated method stub
        stop = true;
    }

}
