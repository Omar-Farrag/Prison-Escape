package Locations;

import Commands.CommandExecution;
import Commands.CommandsDirectory;
import Constants.Days;
import Constants.GoodnessLevel;
import Constants.Prayers;
import General.GoodnessIndex;
import General.InternalClock;
import IO.Printer;
import Items.*;
import Music.DJ;
import Music.Track;
import ObserversAndSubjects.Message;
import ObserversAndSubjects.Topic;
import States.NotPermittedToEnter;
import States.NotVisitedState;
import TimeTrackers.Time;
import Characters.*;

public class Mosque extends Location {
    private boolean abluted;
    private Prayers currentPrayer;
    private boolean prayedCurrentPrayer;

    public Mosque() {
        description = "It is large mosque... far away in the the front there is a mimbar where Imam Hassan is sitting...\n"
                + "There are multiple Qur'an books distributed around the mosque...\n"
                + "You can also use one of the available sebhas";
        locationName = LocationNames.MOSQUE.getName();
        persons = new Characters.Character[] {
                new ImamHassan()
        };
        items = new Item[] {
                new Quran(),
                new Sebha(),
        };
        reasonCantEnter = "";

        commands = new String[] { "pray", "ablute" };
        commandFuncs = new CommandExecution[] {
                (command, commandRecipient) -> pray(),

                (command, commandRecipient) -> ablute(),

        };
        cd = new CommandsDirectory(this);

        visitationState = new NotVisitedState();
        entryPermissionState = new NotPermittedToEnter();

        needsEmployment = false;
        currentlyEmployed = false;
        hint = "This is a house of God and a place of worship...what are you expecting to find??";

        locationTrack = Track.NOTHING;

        requiredGoodness = GoodnessLevel.any;

        openingTime = new Time(Days.ALLDAYS, 4, 0, 0);
        currentTime = InternalClock.getInstance().getTime();
        closingTime = new Time(Days.ALLDAYS, 21, 30, 0);
        abluted = false;
        currentPrayer = Prayers.FAJR;
        prayedCurrentPrayer = true;

    }

    private void ablute() {
        Printer.println("You head up to the ablution room and begin abluting for the next prayer");
        abluted = true;
    }

    public void pray() {
        if (!abluted) {
            Printer.println("Mate you can't pray without abluting first");
        } else if (prayedCurrentPrayer) {
            Printer.println("You alread prayed " + currentPrayer.getName().toLowerCase()
                    + " come back later when it's time for the next prayer");
        } else if (!prayedCurrentPrayer) {
            Printer.println("You raise your hands and begin praying " + currentPrayer.getName().toLowerCase()
                    + " hoping that God forgives you for your sins");
            prayedCurrentPrayer = true;
            GoodnessIndex.getInstance().increaseGoodnessIndex(20);
        }
    }

    @Override
    public void timeUpdate(Message m) {
        super.timeUpdate(m);

        if (m.getTopic() == Topic.CURRENT_PRAYER_ADJUSTMENT) {
            currentPrayer = (Prayers) m.getPayload();
            prayedCurrentPrayer = false;
        }

        else if (m.getTopic() == Topic.PRAYER_TIMING) {
            if (!prayedCurrentPrayer) {
                Printer.println("You missed " + currentPrayer.getName().toLowerCase() + "...Not cool man not cool");
                GoodnessIndex.getInstance().decreaseGoodnessIndex(5);
            }

            currentPrayer = (Prayers) m.getPayload();
            abluted = false;
            prayedCurrentPrayer = false;
            DJ.getInstance().play(Track.ADTHAN, true, false);
        }
    }

}
