package Locations;

import Commands.CommandExecution;
import Commands.CommandsDirectory;
import Constants.Days;
import Constants.GoodnessLevel;
import Constants.Tasks;
import General.GoodnessIndex;
import General.InternalClock;
import General.ThreadTermination;
import General.UserLocation;
import IO.Printer;
import Items.*;
import Music.Track;
import ObserversAndSubjects.Message;
import States.NotPermittedToEnter;
import States.NotVisitedState;
import Strategies.FastRegeneration;
import Strategies.NormalRegeneration;
import TimeTrackers.Time;
import TimeTrackers.TimedQuest;
import Characters.*;
import Characters.Character;

public class Kitchen extends Location implements Runnable, ThreadTermination {

    private Tasks taskToComplete;
    private TimedQuest tq;
    private Thread t;
    private boolean brianPresent;
    private boolean endShift;

    public Kitchen() {
        description = "This is the kitchen...As you walk through the door you Brian the"
                + "\nmaster chef cutting some vegetables on the central island."
                + "\nRight next to him is Matt doing the dishes..."
                + "\nThere is a a kettle and multiple cooking pots on the island..."
                + "\nFurther to the back there is the refridgerator with a built in water dispenser"
                + "\nOn the left side of the kitchen there is Walt brooming the floor"
                + "\nThere is a cabinet behind him that contains some spare cooking tools" +
                "\nThe oven is on the right side";
        locationName = LocationNames.KITCHEN.getName();
        persons = new Character[] {
                new Brian("Brian"),
                new Matt("Matt"),
                new Walt("Walt"),
        };
        items = new Item[] {
                new CookingPot(),
                new Kettle(this),
                new Oven(this),
                new Refridgerator(),
                new Tea(),
                new Cabinet(this),
                new CookingResources(),
        };
        reasonCantEnter = "You must first get a job here before you enter";
        commands = new String[] { "wash", "drink", "spread", "work" };
        commandFuncs = new CommandExecution[] {
                (command, commandRecipient) -> {
                    wash(commandRecipient);
                },
                (command, commandRecipient) -> {
                    drink(commandRecipient);
                },
                (command, commandRecipient) -> {
                    spreadDough(commandRecipient);
                },
                (command, commandRecipient) -> {
                    if (endShift) {
                        t = new Thread(this);
                        t.setName("Kitchen Thread");
                        UserLocation.getInstance().addThread(this);
                        t.start();
                    }
                },
        };
        cd = new CommandsDirectory(this);
        visitationState = new NotVisitedState();
        entryPermissionState = new NotPermittedToEnter();
        needsEmployment = true;
        currentlyEmployed = false;
        hint = "There is this interesting cabinet...You may wanna look inside when Brian is not watching...";
        locationTrack = Track.KITCHEN;
        requiredGoodness = GoodnessLevel.minToBeGOOD;
        openingTime = new Time(Days.ALLDAYS, 5, 0, 0);
        currentTime = InternalClock.getInstance().getTime();
        closingTime = new Time(Days.ALLDAYS, 18, 0, 0);
        brianPresent = true;
        endShift = false;

    }

    public boolean isBrianPresent() {
        return brianPresent;
    }

    @Override
    public void run() {
        try {
            allowedToLeave = false;
            reasonCantEnter = "You're currently doing some work for Brian...Finish your work first...";
            endShift = false;
            work();
            killThread();
        } catch (Exception e) {

        }
    }

    private void work() throws Exception {

        if (!endShift)
            task1();

        if (!endShift)
            task2();

        if (!endShift)
            brianLeaves();

        if (!endShift)
            task3();

        if (!endShift)
            task4();

        if (!endShift) {
            Printer.println("[Brian] Aite that's it for today");
            GoodnessIndex.getInstance().increaseGoodnessIndex(5);
        }

    }

    private void task4() throws InterruptedException {
        Printer.println("[Brian] Go ahead and bake the dough");
        taskToComplete = Tasks.BAKE_DOUGH;
        tq = new TimedQuest(15, () -> {
            Printer.println("[Brian] IF I HIRED A POTATO, IT WOULD HAVE BEEN MORE USEFUL...");
        });

        tq.getThread().join();
    }

    private void task3() throws InterruptedException {
        Printer.println("[Brian] Newbie!! Spread the dough");
        taskToComplete = Tasks.SPREAD_DOUGH;
        tq = new TimedQuest(15, () -> {
            Printer.println("[Brian] I DONT BELIEVE THIS...YOU CANT SPREAD THE DOUGH??????");
        });

        tq.getThread().join();
    }

    private void brianLeaves() throws InterruptedException {
        Printer.println("[Brian] I am gonna go to the toilet...I won't be long...");
        brianPresent = false;
        tq = new TimedQuest(30, () -> {
            brianPresent = true;
        });

        tq.getThread().join();
    }

    private void task2() throws InterruptedException {
        Printer.println("[Brian] Wash these vegetables for me");
        taskToComplete = Tasks.WASH_VEGETABLES;
        tq = new TimedQuest(15, () -> {
            Printer.println("[Brian] YOU ARE ABSOLUTELY USELESS...");
        });

        tq.getThread().join();
    }

    private void task1() throws InterruptedException {
        Printer.println("[Brian] Alright new kid lets get to work");

        Printer.println("[Brian] Get me some hot water in the kettle");
        taskToComplete = Tasks.FILL_KETTLE;
        tq = new TimedQuest(15, () -> {
            Printer.println("[Brian] HEYYY.. WHERE IS THE HOT WATER?? YOU KNOW WHAT, FORGET ABOUT IT");
        });

        tq.getThread().join();
    }

    private void wash(String commandRecipient) {
        String firstWord = commandRecipient.split(" ")[0];
        Printer.println("You begin washing " + firstWord + " in the sink");

        if (firstWord.equals("vegetables"))
            checkIfTaskCompleted(Tasks.WASH_VEGETABLES);
    }

    private void drink(String commandRecipient) {
        String firstWord = commandRecipient.split(" ")[0];
        if (firstWord.equals("water") || firstWord.equals("juice"))
            Printer.println("You pour yourself a glass of " + firstWord + " and drink");
        else
            Printer.println("Not quite sure if you can drink '" + firstWord + "' ");

        UserLocation.getInstance().getHealthBar().setRegenerationStrategy(new FastRegeneration());
        new TimedQuest(20,
                () -> UserLocation.getInstance().getHealthBar().setRegenerationStrategy(new NormalRegeneration()));

    }

    private void spreadDough(String commandRecipient) {
        String firstWord = commandRecipient.split(" ")[0];
        if (!firstWord.equals("dough")) {
            Printer.println("Spread what mate??");
            return;
        }
        Printer.println("You begin spreading the dough");
        checkIfTaskCompleted(Tasks.SPREAD_DOUGH);
    }

    public void checkIfTaskCompleted(Tasks task) {
        if (task == taskToComplete)
            tq.endTimedQuest();
    }

    public void kickUserOutOfKitchen() {
        Printer.println("[Brian] HEYY!!! DIDNT I TELL YOU NOT TO SNOOP AROUND...GET OUT!!!!!");
        GoodnessIndex.getInstance().decreaseGoodnessIndex(10);
        UserLocation.getInstance().fastTravel(LocationNames.CELL.getName());
    }

    @Override
    public void killThread() {
        // TODO Auto-generated method stub
        endShift = true;
        allowedToLeave = true;
    }

    @Override
    public void timeUpdate(Message m) {
        // TODO Auto-generated method stub
        if (this.outsideWorkingHours()) {
            killThread();
            if (tq != null)
                tq.endTimedQuest();
        }
        super.timeUpdate(m);
    }

}
