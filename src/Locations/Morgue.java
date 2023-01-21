package Locations;

import Characters.Character;
import Characters.MortuaryAssistant;
import Commands.CommandExecution;
import Commands.CommandsDirectory;
import Constants.Days;
import Constants.GoodnessLevel;
import General.InternalClock;
import IO.Printer;
import Items.Furnace;
import Items.Item;
import Items.Skeletons;
import Music.Track;
import States.NotPermittedToEnter;
import States.NotVisitedState;
import TimeTrackers.Time;

//zayed
public class Morgue extends Location {
        public Morgue() {
                description = "The Morgue is a place where dead bodies are stored or turned into ashes, the morgue assistant is available most of the time..."
                                + "\n There is a furnace and skeletons everywhere";
                locationName = LocationNames.MORGUE.getName();
                persons = new Character[] {
                                new MortuaryAssistant()
                };
                items = new Item[] {
                                new Furnace(),
                                new Skeletons()
                };
                reasonCantEnter = "";

                commands = new String[] {
                                "cremate"
                };

                commandFuncs = new CommandExecution[] {
                                (command, commandRecipient) -> {
                                        cremateBody();
                                }
                };
                cd = new CommandsDirectory(this);
                visitationState = new NotVisitedState();
                entryPermissionState = new NotPermittedToEnter();
                needsEmployment = true;
                currentlyEmployed = false;
                locationTrack = Track.NOTHING;
                hint = "This is a morgue, you can just look around and talk to the mortuary assistant";
                requiredGoodness = GoodnessLevel.any;
                openingTime = new Time(Days.ALLDAYS, 15, 0, 0);
                currentTime = InternalClock.getInstance().getTime();
                closingTime = new Time(Days.ALLDAYS, 22, 0, 0);
        }

        public void cremateBody() {
                Printer.println("You have turned the corpse into ash");
        }

}
