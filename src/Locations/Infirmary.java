package Locations;

import Commands.CommandExecution;
import Commands.CommandsDirectory;
import Constants.Days;
import Constants.GoodnessLevel;
import General.InternalClock;
import General.UserLocation;
import IO.Printer;
import Items.*;
import Music.Track;
import ObserversAndSubjects.Message;
import States.PermittedToEnter;
import States.VisitedState;
import TimeTrackers.Time;
import TimeTrackers.TimedQuest;
import Characters.*;

public class Infirmary extends Location {
    private boolean doctorDistracted;
    private int cupsOfWater;

    public Infirmary() {
        description = "This is the infirmary...whenever your are not feeling well you come here and get checked up on my Doctor Robert...";
        locationName = LocationNames.INFIRMARY.getName();
        persons = new Characters.Character[] {
                new Robert()
        };
        items = new Item[] {
                new AnestheticDrugs(this),
                new MedicalBed(),
                new MedicalTools()
        };
        reasonCantEnter = "";

        commands = new String[] {
                "distract",
        };

        commandFuncs = new CommandExecution[] {
                (command, commandRecipient) -> {
                    distract();
                }

        };
        cd = new CommandsDirectory(this);

        visitationState = new VisitedState();
        entryPermissionState = new PermittedToEnter();

        needsEmployment = false;
        currentlyEmployed = false;
        hint = "Find a way to get your hands on sleeping drugs to aid in your escape";

        locationTrack = Track.NOTHING;

        requiredGoodness = GoodnessLevel.any;

        openingTime = new Time(Days.ALLDAYS, 0, 0, 0);
        currentTime = InternalClock.getInstance().getTime();
        closingTime = new Time(Days.ALLDAYS, 23, 59, 59);
        doctorDistracted = false;
        cupsOfWater = 0;

    }

    private void distract() {
        if (isDoctorDistracted()) {
            Printer.println("The doctor is already getting you water");
            return;
        }
        if (gotPissed()) {
            Printer.println(
                    "[Robert] I ALREADY GOT YOU THREE CUPS OF WATER...ENOUGH IS ENOUGH...YOU WILL LOOK LIKE A BALOON IF YOU DRINK MORE WATER...GO BACK TO YOUR CELL");
            UserLocation.getInstance().fastTravel(LocationNames.CELL.getName());
            return;
        }
        doctorDistracted = true;
        cupsOfWater++;
        Printer.println("You asked the doctor for a cup of water...Doctor went to get you one");
        new TimedQuest(10, () -> {
            if (UserLocation.getInstance().getCurrentLocation().equals(this))
                Printer.println("[Robert] Here is your water");
            doctorDistracted = false;
        });
    }

    private boolean gotPissed() {
        return cupsOfWater >= 3;
    }

    public boolean isDoctorDistracted() {
        return doctorDistracted;
    }

    @Override
    public void timeUpdate(Message m) {
        doctorDistracted = false;
        cupsOfWater = 0;
        super.timeUpdate(m);
    }
}
