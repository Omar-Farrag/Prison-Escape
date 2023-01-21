package General;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.AbstractMap.SimpleEntry;

import IO.Inputter;
import IO.Printer;
import Strategies.HealthBar;
import TimeTrackers.TimedQuest;
import Locations.*;
import Music.DJ;
import Music.Track;
import ObserversAndSubjects.Message;
import SensorControl.SensorCommand;
import SensorControl.TCP_Client;
import Commands.*;
import Items.*;
import Characters.Character;

public class UserLocation extends Location {

    // Don't alter this class
    // Only add more commands and their corresponding functions to the commands and
    // commandFuncs arrays if you want to

    private static UserLocation instance;
    private Location currentLocation;
    private DirectionsDirectory dd;
    private CommandControl cc;
    private SimpleEntry<String[], Location[]> nextPossibleLocations;
    private int totalNumOfInstructions;
    private Inventory mobileInventory;
    private HealthBar healthBar;
    private boolean hasAJob;
    private Location employmentLocation;
    private String host;
    private int port;
    private Fight brawl;
    private boolean currentlyConversating;
    private Character talkingWith;
    private boolean beingPunished;
    private String[] punishmentMessages;

    private ArrayList<ThreadTermination> activeThreads;

    private UserLocation(Location loc) {
        currentLocation = loc;
        dd = new DirectionsDirectory();
        cc = CommandControl.getInstance(this);
        nextPossibleLocations = dd.getNextPossibleLocations(currentLocation);
        totalNumOfInstructions = 0;
        mobileInventory = new Inventory(4);
        healthBar = new HealthBar("You");
        hasAJob = false;
        employmentLocation = null;
        currentlyConversating = false;
        talkingWith = null;
        beingPunished = false;

        host = "";
        port = 0;

        activeThreads = new ArrayList<>();

        this.commands = new String[] {
                "talk", "walk", "fast-travel",
                "take", "drop", "diagnostics?",
                "get", "update", "fight",
                "dodge", "punch", "look",
                "commands?", "items?", "persons?",
                "directions?", "map?" };
        this.commandFuncs = new CommandExecution[] {

                (command, commandRecipient) -> talkToCharacter(commandRecipient),
                (command, commandRecipient) -> setLocation(commandRecipient),
                (command, commandRecipient) -> fastTravel(commandRecipient),
                (command, commandRecipient) -> takeItem(commandRecipient),
                (command, commandRecipient) -> dropItem(commandRecipient),
                (command, commandRecipient) -> printDiagnostics(),
                (command, commandRecipient) -> giveHint(commandRecipient),
                (command, commandRecipient) -> updateSensorInfo(commandRecipient),
                (command, commandRecipient) -> startFight(commandRecipient),
                (command, commandRecipient) -> dodge(commandRecipient),
                (command, commandRecipient) -> punch(commandRecipient),
                (command, commandRecipient) -> lookAround(commandRecipient),
                (command, commandRecipient) -> printRoomContent("commands"),
                (command, commandRecipient) -> printRoomContent("items"),
                (command, commandRecipient) -> printRoomContent("persons"),
                (command, commandRecipient) -> showDirections(),
                (command, commandRecipient) -> showMap(),

        };

        this.cd = new CommandsDirectory(this);
        punishmentMessages = new String[] {
                "YOU MADE A BIG MISTAKE",
                "YOU'RE GONNA SPEND A LONG TIME IN SOLITARY",
                "I AM GOING TO END YOU",
                "YOU'RE IN BIG TROUBLE",
                "YOU'RE A DEAD MAN",
                "WAIT TILL THE WARDEN FINDS OUT",
                "WE ARE GOING TO ANNIHILATE YOU",
        };
    }

    private void showMap() {
        Printer.println(
                ""
                        + "\n                       _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _"
                        + "\n                      |               |                           |                                     |                  |"
                        + "\n                      |               |      Execution Room       |               Morgue                |                  |"
                        + "\n                      |               | _ _ _ _ _ _ _ _ _ _ _ _ _ |_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _|                  |"
                        + "\n                      |               |                   |                |                            |                  |"
                        + "\n                      |               |      Kitchen      |      Cell      |       Workshop             |                  |"
                        + "\n                      |               |_ _ _ _ _ _ _ _ _ _| _ _ _ _ _ _ _ _|_ _ _ _ _ _ _ _ _ _ _ _ _ _ |                  |"
                        + "\n                      |               |                                            |                    |                  |"
                        + "\n                      |               |                   Library                  |                    |                  |"
                        + "\n                      |               | _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _|                    |                  |"
                        + "\n                      |               |                    |                       |      Toilet        |                  |"
                        + "\n                      |    Armory     |                    |                       |                    |                  |"
                        + "\n                      |               |                    |                       |                    |                  |"
                        + "\n                      |               |    Laundry Room    |                       | _ _ _ _ _ _ _ _ _ _|                  |"
                        + "\n                      |               |                    |       Open Yard       |                    |    Underground   |"
                        + "\n                      |               |                    |                       |                    |      Storage     |"
                        + "\n                      |               | _ _ _ _ _ _ _ _ _ _|                       |     Infirmary      |                  |"
                        + "\n                      |               |                    |                       |                    |                  |"
                        + "\n                      |               |                    |_ _ _ _ _ _ _ _ _ _ _ _|_ _ _ _ _ _ _ _ _ _ |                  |"
                        + "\n                      |               |                    |                       |                    |                  |"
                        + "\n                      |               |                    |        Solitary       |                    |                  |"
                        + "\n                      |               |                    |       Confinement     |       Mosque       |                  |"
                        + "\n                      |               |                    |                       |                    |                  |"
                        + "\n                      |               |      Warden's      | _ _ _ _ _ _ _ _ _ _ _ |_ _ _ _ _ _ _ _ _ _ |                  |"
                        + "\n                      |_ _ _ _ _ _ _ _|       Office       |                                            |                  |"
                        + "\n                      |               |                    |                                            |                  |"
                        + "\n                      |               |                    |                                            |                  |"
                        + "\n                      |    Prison     |                    |                Coal Field                  |                  |"
                        + "\n                      |   Entrance    |                    |                                            |                  |"
                        + "\n                      |               |                    |                                            |                  |"
                        + "\n                      |_ _ _ _ _ _ _ _|_ _ _ _ _ _ _ _ _ _ | _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _| _ _ _ _ _ _ _ _ _|"

        );
    }

    private void showDirections() {
        String[] directions = nextPossibleLocations.getKey();
        Location[] correspondingLocations = nextPossibleLocations.getValue();

        for (int i = 0; i < directions.length - 1; i++)
            if (!directions[i].equals("here"))
                Printer.print(directions[i] + "(" + correspondingLocations[i].getLocationName() + ") / ");

        Printer.print(directions[directions.length - 1] + "("
                + correspondingLocations[directions.length - 1].getLocationName() + ")");
        Printer.println("");

    }

    public boolean addThread(ThreadTermination e) {
        if (activeThreads.size() >= 20)
            terminateActiveThreads();
        return activeThreads.add(e);
    }

    public void talkToCharacter(String commandRecipient) {
        for (Characters.Character c : currentLocation.getPersons()) {
            if (c.getName().toLowerCase().trim().equals(commandRecipient)) {
                c.conversate();
                return;
            }
        }
        Printer.println("'" + commandRecipient + "' is not in the room");
    }

    private void printRoomContent(String toPrint) {
        switch (toPrint) {
            case "commands":
                Printer.print(String.join("/", commands));
                Printer.print("/");
                Printer.print(String.join("/", currentLocation.getCommands()));
                Printer.println("");
                break;
            case "items":
                Printer.println(String.join("/", Arrays.toString(currentLocation.getItems())));
                break;
            case "persons":
                Printer.println(String.join("/", Arrays.toString(currentLocation.getPersons())));
                break;
        }
    }

    private void lookAround(String commandRecipient) {
        if (commandRecipient.equals("around"))
            Printer.println(currentLocation.getDescription());
        else
            Printer.println("Couldn't understand look '" + commandRecipient + "'. Did you mean 'look around'?");
    }

    private void punch(String commandRecipient) {
        if (brawl == null)
            Printer.println("Brother you're not even in a fight, you punching the air or something?");
        else if (commandRecipient.equals("right"))
            brawl.setUsersLatestCommand(SensorCommand.PUNCH_RIGHT);
        else if (commandRecipient.equals("left"))
            brawl.setUsersLatestCommand(SensorCommand.PUNCH_LEFT);
        else
            Printer.println("Didn't really understand what you said");
    }

    private void dodge(String commandRecipient) {
        if (brawl == null)
            Printer.println("Brother you're not even in a fight, who you dodgin?");
        else if (commandRecipient.equals("right"))
            brawl.setUsersLatestCommand(SensorCommand.DODGE_RIGHT);
        else if (commandRecipient.equals("left"))
            brawl.setUsersLatestCommand(SensorCommand.DODGE_LEFT);
        else
            Printer.println("Didn't really understand what you said");
    }

    public void startFight(String characterName) {
        for (Character c : currentLocation.getPersons()) {

            if (characterName.toLowerCase().trim().equals(c.getName().toLowerCase().trim())) {

                if (c.isPoliceOfficer()) {
                    GoodnessIndex.getInstance().decreaseGoodnessIndex(35);
                    getPunished();
                    return;
                }

                if (!c.canBeFought()) {
                    Printer.println(c.getReasonCantBeFought());
                    return;
                }

                brawl = new Fight(c);
                allowedToLeave = false;
                return;

            }
        }
        Printer.println("'" + characterName + "' is not in the room");
    }

    public void getPunished() {
        // terminateActiveThreads();
        // TimedQuest.terminateActiveTimedQuests();
        GoodnessIndex.getInstance().decreaseGoodnessIndex(20);
        Printer.println("[OFFICER] " + getPunishmentMessage());
        try {
            Thread.sleep(2000);
            DJ.getInstance().play(Track.FEW_MOMENTS_LATER, true, false).join();
            Printer.println("A few moments later....");

            // Thread.sleep(3000);

            beingPunished = false;
            fastTravel(LocationNames.SOLITARY_CONFINEMENT.getName());
            beingPunished = true;
            currentLocation.preventFromLeaving();
            new TimedQuest(60, () -> {
                currentLocation.allowToLeave();
                InternalClock.getInstance().passTime(24);
                Printer.println("[Officer] You are now free to go...Stay out of trouble or you will revisit");
            });

            DJ.getInstance().play(Track.GETTING_BEATEN, true, false);
            beingPunished = false;
            mobileInventory.clear();
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    private String getPunishmentMessage() {
        return punishmentMessages[new Random().nextInt(punishmentMessages.length)];
    }

    public void endFight() {
        brawl = null;
    }

    public boolean isBeingPunished() {
        return beingPunished;
    }

    public HealthBar getHealthBar() {
        return healthBar;
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    public void updateSensorInfo(String commandRecipient) {

        if (!commandRecipient.equals("sensor information"))
            Printer.println("Did you mean 'update sensor information'?");
        else {
            try {

                Printer.print("Enter Host IP Address: ");
                host = Inputter.input();

                InetAddress.getByName(host);

                Printer.print("Enter Port Number: ");
                port = Integer.parseInt(Inputter.input());

                TCP_Client.getInstance().updateSensorInfo(host, port);
                Printer.println("Sensor Information Updated Successfully");
            } catch (UnknownHostException e) {
                Printer.println(
                        "The IP you entered is invalid...Please enter a valid IP address");
                updateSensorInfo("sensor information");
            } catch (NumberFormatException e) {
                Printer.println(
                        "The port number you entered is invalid...Please enter an integer for the port number...");
                updateSensorInfo("sensor information");
            }
        }
    }

    public void perform(String userInput) {
        userInput = userInput.toLowerCase().trim();

        if (brawl != null) {
            if (!userInput.equals("dodge right") &&
                    !userInput.equals("dodge left") &&
                    !userInput.equals("punch right") &&
                    !userInput.equals("punch left")) {

                Printer.println("Bro you're in the middle of a fight...what are you trying to do");
                return;
            }
        }
        cc.executeCommand(userInput.toLowerCase().trim());
    }

    public void setLocation(String newDirection) {

        String[] possibleDirections = nextPossibleLocations.getKey();

        for (int i = 0; i < possibleDirections.length; i++) {

            if (newDirection.equals(possibleDirections[i].toLowerCase().trim())) {
                Location newLocation = nextPossibleLocations.getValue()[i];
                newLocation.getEntryPermissionState().enterLocation(newLocation);
                newLocation.nextVisitationState();
                return;
            }
        }
        Printer.println("Invalid direction: You can only go "
                + String.join(" ", String.join("/", nextPossibleLocations.getKey())));
    }

    @Override
    public void goodnessUpdate(boolean becameGood) {
        // Do Nothing
    }

    @Override
    public void timeUpdate(Message m) {
        // Do Nothing
    }

    public void fastTravel(String commandRecipient) {

        Location l = dd.getLocation(commandRecipient);
        if (l != null) {
            l.getVisitationState().fastTravel(l);
            return;
        }
        Printer.println("Don't know where '" + commandRecipient + "' is");
    }

    public void printDiagnostics() {
        Printer.println("Current Location: " + currentLocation.getLocationName()
                + "\nCurrent Time: " + currentTime
                + "\nGoodness Level: " + GoodnessIndex.getInstance().getGoodnessIndex()
                + "\nHealth: " + healthBar.getHealth()
                + "\nTotal Instruction Count: " + totalNumOfInstructions
                + "\nInventory: " + mobileInventory
                + "\nCell Stash: " + Cell.getStash()
                + "\nCurrent Sensor IP: " + host
                + "\nCurrent Sensor Port: " + port
                + "\nConnection State: " + (TCP_Client.getInstance().isConnectionEstablished() ? "Live" : "Dead"));
    }

    public void takeItem(String commandRecipient) {
        Item temp = this.findItem(commandRecipient);
        if (temp == null) {
            Printer.println("'" + commandRecipient + "' could not be found in this location");
            return;
        }

        if (!temp.isTakeable())
            Printer.println("You cannot take '" + temp.getName() + "': " + temp.getReasonCantTake());
        else if (mobileInventory.contains(temp.getName())) {
            Printer.println(temp.getName() + " is already in your inventory");
        } else if (!mobileInventory.addToInventory(temp)) {
            Printer.println("Mobile inventory full! Drop some items first to open up space");
        } else
            Printer.println(temp + " added to mobile inventory");

    }

    public void dropItem(String commandRecipient) {
        Item temp = mobileInventory.findItem(commandRecipient);
        if (temp == null)
            Printer.println("'" + commandRecipient + "' is not in your inventory");
        else if (!mobileInventory.removeFromInventory(temp)) {
            Printer.println("Can't do that. Inventory is empty.");
        } else
            Printer.println(temp + " dropped");

    }

    public void willStartConversation(Character talkingWith) {
        currentlyConversating = true;
        this.talkingWith = talkingWith;
    }

    public void willEndConversating() {
        currentlyConversating = false;
        this.talkingWith = null;
    }

    public void incrementInstructions() {
        totalNumOfInstructions++;
    }

    public static synchronized UserLocation getInstance() {
        if (instance == null) {
            return instance = new UserLocation(new Cell());
        }
        return instance;
    }

    public Location getCurrentLocation() {
        return currentLocation;
    }

    public DirectionsDirectory getDd() {
        return dd;
    }

    public CommandControl getCc() {
        return cc;
    }

    public void setCurrentLocation(Location currentLocation) {
        this.currentLocation = currentLocation;
    }

    public void setNextPossibleLocations(SimpleEntry<String[], Location[]> nextPossibleLocations) {
        this.nextPossibleLocations = nextPossibleLocations;
    }

    public Inventory getMobileInventory() {
        return mobileInventory;
    }

    public boolean alreadyEmployed() {
        return hasAJob;
    }

    public void quitJobAt(String commandRecipient) {
        commandRecipient = dd.extractLocation(commandRecipient);
        if (!hasAJob)
            Printer.println("You are already unemployed");
        else if (!commandRecipient.equals(employmentLocation.getLocationName().trim().toLowerCase()))
            Printer.println("You are not currently employed at '" + commandRecipient + "'");
        else {
            employmentLocation.quit();
            employmentLocation.nextEntryPermissionState();
            hasAJob = false;
            employmentLocation = null;
        }
    }

    public void getJobAt(String commandRecipient) {
        commandRecipient = dd.extractLocation(commandRecipient);
        Location targetEmploymentLocation = dd.getLocation(commandRecipient);
        if (targetEmploymentLocation == null)
            Printer.println("Could not find the location you want to work at");

        else if (hasAJob) {
            Printer.println("You already have a job. Quit your job first to work at "
                    + targetEmploymentLocation.getLocationName());
            return;
        } else {
            targetEmploymentLocation.employHere();
            targetEmploymentLocation.nextEntryPermissionState();
            hasAJob = true;
            employmentLocation = targetEmploymentLocation;
        }
    }

    public void giveHint(String commandRecipient) {
        if (!commandRecipient.equals("hint"))
            Printer.println("Could't understand what you wanted to get. Did you mean 'get hint' ?");
        else
            Printer.println(currentLocation.getHint());
    }

    public boolean isDead() {
        return healthBar.getHealth() == 0;
    }

    @Override
    public Item findItem(String itemName) {
        return currentLocation.findItem(itemName);
    }

    public void setHasAJob(boolean hasAJob) {
        this.hasAJob = hasAJob;
    }

    public void setEmploymentLocation(Location employmentLocation) {
        this.employmentLocation = employmentLocation;
    }

    public SimpleEntry<String[], Location[]> getNextPossibleLocations() {
        return nextPossibleLocations;
    }

    public int getTotalNumOfInstructions() {
        return totalNumOfInstructions;
    }

    public boolean isHasAJob() {
        return hasAJob;
    }

    public Location getEmploymentLocation() {
        return employmentLocation;
    }

    public Fight getBrawl() {
        return brawl;
    }

    public boolean isCurrentlyConversating() {
        return currentlyConversating;
    }

    public Character getTalkingWith() {
        return talkingWith;
    }

    public void terminateActiveThreads() {
        for (ThreadTermination t : activeThreads) {
            t.killThread();
        }
        activeThreads.clear();
    }

    public boolean isFighting() {
        return brawl != null;
    }

    // The lower the index the higher the chance to return true
    public boolean beatTheOdds() {
        Random r = new Random();
        int num = r.nextInt(0, 101 + GoodnessIndex.getInstance().getGoodnessIndex());
        if (num >= 0 && num <= 30)
            return true;
        else
            return false;
    }

    // The higher the index the higher the chance to return true
    public boolean reverseBeatTheOdds() {
        return !beatTheOdds();
    }

}
