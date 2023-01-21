package Locations;

import IO.Printer;
import ObserversAndSubjects.*;
import States.*;
import TimeTrackers.Time;

import Commands.*;
import Items.*;
import Music.Track;
import Constants.*;
import General.*;

public abstract class Location extends ConcreteObserver {

    protected String description;
    protected String locationName;
    protected Characters.Character[] persons;
    protected Item[] items;
    protected String reasonCantEnter;

    protected String[] commands;
    protected CommandExecution[] commandFuncs;
    protected CommandsDirectory cd;

    protected VisitationState visitationState;
    protected EntryPermissionState entryPermissionState;

    protected boolean needsEmployment;
    protected boolean currentlyEmployed;
    protected boolean becameGood;
    protected String hint;

    protected Track locationTrack;

    protected GoodnessLevel requiredGoodness;

    // .....Rest of the CODE.....//
    protected Time openingTime;
    protected Time currentTime;
    protected Time closingTime;
    // .....Rest of the CODE.....//

    protected boolean allowedToLeave;
    protected String reasonCantLeave;
    protected boolean loopLocationTrack;

    public Location() {
        super(new Subject[] { InternalClock.getInstance(), GoodnessIndex.getInstance() });
        currentTime = InternalClock.getInstance().getTime();
        becameGood = false;
        allowedToLeave = true;
        reasonCantLeave = "";
        loopLocationTrack = false;
        locationTrack = Track.NOTHING;

    }

    public boolean isAllowedToLeave() {
        return allowedToLeave;
    }

    @Override
    public synchronized void update(Message m) {
        if (m.getTopic() == Topic.GOODNESS)
            goodnessUpdate((boolean) m.getPayload());

        else if (m.getTopic() == Topic.TIME || m.getTopic() == Topic.PRAYER_TIMING
                || m.getTopic() == Topic.CURRENT_PRAYER_ADJUSTMENT)
            timeUpdate(m);

    }

    public boolean outsideWorkingHours() {
        return currentTime.greaterThan(closingTime) || currentTime.lessThan(openingTime);
    }

    public boolean needsToWorkHereFirst() {
        return needsEmployment && !currentlyEmployed;
    }

    public boolean goodEnoughToEnter() {
        return requiredGoodness == GoodnessLevel.any || becameGood;
    }

    public void timeUpdate(Message m) {

        nextEntryPermissionState();

        if (UserLocation.getInstance().getCurrentLocation() == this)
            entryPermissionState.kickOutOfLocation();

    }

    public void goodnessUpdate(boolean newGoodness) {

        this.becameGood = newGoodness;
        nextEntryPermissionState();
    }

    public void nextVisitationState() {
        visitationState.next(this);
    }

    public Time getOpeningTime() {
        return openingTime;
    }

    public Time getClosingTime() {
        return closingTime;
    }

    public void setReasonCantEnter(String reasonCantEnter) {
        this.reasonCantEnter = reasonCantEnter;
    }

    public boolean isBecameGood() {
        return becameGood;
    }

    public Characters.Character[] getPersons() {
        return persons;
    }

    public Item[] getItems() {
        return items;
    }

    public synchronized EntryPermissionState getEntryPermissionState() {
        return entryPermissionState;
    }

    public Track getLocationTrack() {
        return locationTrack;
    }

    public String getDescription() {
        return description;
    }

    public synchronized void setEntryPermissionState(EntryPermissionState state) {
        this.entryPermissionState = state;
    }

    public synchronized VisitationState getVisitationState() {
        return visitationState;
    }

    public synchronized void setVisitationState(VisitationState state) {
        this.visitationState = state;
    }

    public Item findItem(String itemName) {
        for (Item i : items) {
            if (itemName.contains(i.getName().toLowerCase().trim()))
                return i;
        }
        return null;
    }

    public String getReasonCantEnter() {
        return reasonCantEnter;
    }

    public String[] getCommands() {
        return commands;
    }

    public CommandExecution[] getCommandFuncs() {
        return commandFuncs;
    }

    public CommandsDirectory getCd() {
        return cd;
    }

    public String getLocationName() {
        return locationName;
    }

    public String getHint() {
        return hint;
    }

    public void employHere() {
        if (!needsEmployment) {
            Printer.println("There are no jobs available here");
        } else {
            currentlyEmployed = true;
            Printer.println("Congratulations. You are now employed at " + locationName);
        }
    }

    public void quit() {
        currentlyEmployed = false;
        Printer.println("You have quit your job at " + locationName);
    }

    public void nextEntryPermissionState() {
        entryPermissionState.next(this);
    }

    public boolean isCurrentlyEmployed() {
        return currentlyEmployed;
    }

    public void setCurrentlyEmployed(boolean currentlyEmployed) {
        this.currentlyEmployed = currentlyEmployed;
    }

    public boolean isNeedsEmployment() {
        return needsEmployment;
    }

    public GoodnessLevel getRequiredGoodness() {
        return requiredGoodness;
    }

    public Time getCurrentTime() {
        return currentTime;
    }

    public boolean isLoopLocationTrack() {
        return loopLocationTrack;
    }

    public boolean equals(Location other) {
        return locationName.equals(other.getLocationName());
    }

    public String getReasonCantLeave() {
        return reasonCantLeave;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public void setPersons(Characters.Character[] persons) {
        this.persons = persons;
    }

    public void setItems(Item[] items) {
        this.items = items;
    }

    public void setCommands(String[] commands) {
        this.commands = commands;
    }

    public void setCommandFuncs(CommandExecution[] commandFuncs) {
        this.commandFuncs = commandFuncs;
    }

    public void setCd(CommandsDirectory cd) {
        this.cd = cd;
    }

    public void setNeedsEmployment(boolean needsEmployment) {
        this.needsEmployment = needsEmployment;
    }

    public void setBecameGood(boolean becameGood) {
        this.becameGood = becameGood;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    public void setLocationTrack(Track locationTrack) {
        this.locationTrack = locationTrack;
    }

    public void setRequiredGoodness(GoodnessLevel requiredGoodness) {
        this.requiredGoodness = requiredGoodness;
    }

    public void setOpeningTime(Time openingTime) {
        this.openingTime = openingTime;
    }

    public void setCurrentTime(Time currentTime) {
        this.currentTime = currentTime;
    }

    public void setClosingTime(Time closingTime) {
        this.closingTime = closingTime;
    }

    public void allowToLeave() {
        allowedToLeave = true;
    }

    public void preventFromLeaving() {
        allowedToLeave = false;
    }

    public void setReasonCantLeave(String reasonCantLeave) {
        this.reasonCantLeave = reasonCantLeave;
    }

    public void setLoopLocationTrack(boolean loopLocationTrack) {
        this.loopLocationTrack = loopLocationTrack;
    }

}
