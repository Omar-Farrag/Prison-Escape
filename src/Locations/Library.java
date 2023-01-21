package Locations;

import Commands.CommandExecution;
import Commands.CommandsDirectory;
import Constants.Days;
import Constants.GoodnessLevel;
import General.InternalClock;
import IO.Printer;
import Items.*;
import Music.DJ;
import Music.Track;
import States.NotVisitedState;
import States.PermittedToEnter;
import TimeTrackers.Time;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

import Characters.*;

public class Library extends Location {

    private boolean lightOn;

    private Queue<Track> songs;

    public Library() {
        description = "This is is the library...The first thing encountering you is a set of shelves that"
                + "\neventually lead to the front desk...The librarian Willy is standing right there reading a book"
                + "\non the right side there is a table with a couple of things on it...Steve and Jason are sitting nearby";
        locationName = LocationNames.LIBRARY.getName();
        persons = new Characters.Character[] {
                new Willy(),
                new Steve(),
                new Jason(),
        };
        items = new Item[] {
                new Book(this),
                new CD_Player(),
                new Lamp(this),
                new Paper(),
                new Shelves(),
                new Table(),
                new Pen(),
        };
        reasonCantEnter = "";
        commands = new String[] { "play", "stop" };
        commandFuncs = new CommandExecution[] {
                (command, commandRecipient) -> {
                    playSong(commandRecipient);
                },
                (command, commandRecipient) -> {
                    stopPlaying(commandRecipient);
                },
        };
        cd = new CommandsDirectory(this);
        visitationState = new NotVisitedState();
        entryPermissionState = new PermittedToEnter();
        needsEmployment = false;
        currentlyEmployed = false;
        hint = "Bro...it's just a library...";
        locationTrack = Track.NOTHING;
        requiredGoodness = GoodnessLevel.any;
        openingTime = new Time(Days.ALLDAYS, 9, 0, 0);
        currentTime = InternalClock.getInstance().getTime();
        closingTime = new Time(Days.ALLDAYS, 18, 0, 0);
        songs = new LinkedList<>(Arrays.asList(new Track[] {
                Track.CREAM,
                Track.DARLIN,
                Track.IT_WAS_A_GOOD_DAY,
                Track.BILLIE_JEAN,
                Track.IT_AINT_HARD_TO_TELL,
                Track.SHOOK_ONES,
                Track.YOU_KNOW_HOW_WE_DO_IT,
                Track.STAYIN_ALIVE,
                Track.BABY_I_LOVE_YOUR_WAY,
                Track.WE_WILL_ROCK_YOU,
                Track.WAKA_WAKA,
        }));
        lightOn = false;

    }

    private void playSong(String commandRecipient) {
        if (!commandRecipient.equals("album")) {
            Printer.println("Did you mean play album?");
        } else {

            Track song = songs.remove();
            DJ.getInstance().play(song, true, false);
            songs.add(song);
            Printer.println("Playing " + song.getSongName() + " by " + song.getArtist());
        }
    }

    private void stopPlaying(String commandRecipient) {
        if (!commandRecipient.equals("album")) {
            Printer.println("Did you mean stop album?");
        } else {
            DJ.getInstance().stop();
            Printer.println("CD_Player stopped...");
        }
    }

    public boolean isLightOn() {
        return lightOn;
    }

    public void setLightOn(boolean lightOn) {
        this.lightOn = lightOn;
    }
}
