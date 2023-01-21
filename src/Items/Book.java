package Items;

import Commands.CommandExecution;
import Commands.CommandsDirectory;
import General.GoodnessIndex;
import General.UserLocation;
import IO.Printer;
import Locations.Library;

public class Book extends Item {
    private Library lib;

    public Book(Library lib) {
        this.name = "book";
        this.commands = new String[] { "read", "observe" };
        this.commandFuncs = new CommandExecution[] {
                (command, commandRecipient) -> {
                    readBook();
                },
                (command, commandRecipient) -> {
                    observeBookCover();
                },
        };
        this.cd = new CommandsDirectory(this);
        takeable = true;
        this.lib = lib;
    }

    private void readBook() {

        GoodnessIndex.getInstance().increaseGoodnessIndex(15);
        Printer.println("You start reading a book that grabbed your attention...the book goes... ");

        if (!lib.isLightOn()) {
            Printer.println(
                    "You struggle to read a little bit since its currently dim in the library but you still manage");
            UserLocation.getInstance().getHealthBar().decreaseHealthBy(5);
        }

        Printer.println(
                "It was a bright cold day in April, and the clocks were striking thirteen. Winston Smith,his chin nuzzledinto"
                        + "\nhis breast in an effort to escape the vile wind, slipped quickly through the glass doors of Victory."
                        + "\nMansions, though not quickly enough to prevent a swirl of gritty dust from entering along with him."
                        + "\nThe hallway smelt of boiled cabbage and old rag mats. At one end of it a coloured poster, too large for i"
                        + "\ndoor display, had been tacked to the wall. It depicted simply an enormous face, more than a metre wide: "
                        + "\nthe face of a man of about forty-five, with a heavy black moustache and ruggedly handsome features. Winston"
                        + "\nmade for the stairs. It was no use trying the lift. Even at the best of times it was seldom working, and at"
                        + "\npresent the electric current was cut off during daylight hours. It was part of the economy drive in "
                        + "\npreparation for Hate Week."

                        + "\n\nThe flat was seven flights up, and Winston, who was thirty-nine and had a varicose"
                        + "\nulcer above his right ankle, went slowly, resting several times on the way. On each landing, opposite the lift-shaft,"
                        + "\nthe poster with the enormous face gazed from the wall. It was one of those pictures which are so contrived that the "
                        + "\neyes follow you about when you move. BIG BROTHER IS WATCHING YOU, the caption beneath it ran. "
                        + "\nInside the flat a fruity voice was reading out a list of figures which had something to do with the "
                        + "\nprodution of pig-iron. The voice came from an oblong metal plaque like a dulled mirror which formed part of the surface of"
                        + "\nthe right-hand wall. Winston turned a switch and the voice sank somewhat, though the words were still distinguishable. "
                        + "\nThe instrument (the telescreen, it was called) could be dimmed, but there was no way of shutting it off completely. "
                        + "\nHe moved over to the window: a smallish, frail figure, the meagreness of his body merely emphasized by the blue overalls"
                        + "\nwhich were the uniform of the party. His hair was very fair, his face naturally sanguine, his skin roughened by coarse"
                        + "\nsoap and blunt razor blades and the cold of the winter that had just ended. "

                        + "\n\nOutside, even through the shut window-pane, the world looked cold. Down in the street little eddies of "
                        + "\nwid were whirling dust and torn paper into spirals, and though the sun was shining and the sky a harsh blue, there seemed"
                        + "\nto be no colour in anything, except the posters that were plastered everywhere. The black moustachiod face gazed down from"
                        + "\nevery commanding corner. There was one on the house-front immediately opposite. BIG BROTHER IS WATCHING YOU, the caption said,"
                        + "\nwhile the dark eyes looked deep into Winston's own. Down at streetlevel another poster, torn at one corner, flapped fitfully "
                        + "\nin the wind, alternately covering and uncovering the single word INGSOC. In the far distance a helicopter skimmed down between"
                        + "\nthe roofs, hovered for an instant like a bluebottle, and darted away again with a curving flight. It was the police patrol, "

                        + "\n\nsnooping into people's windows. The patrols did not matter, however. Only the Thought Police mattered. "
                        + "\nBehind Winston's back the voice from the telescreen was still babbling away about pig-iron and the overfu"
                        + "\nfilment of the Ninth Three-Year Plan. The telescreen received and transmitted simultaneously. Any sound that Winston made, "
                        + "\nabove the level of a very low whisper, would be picked up by it, moreover, so long as he remained within the field of vision "
                        + "\nwhich the metal plaque commanded, he could be seen as well as heard. There was of course no way of knowing whether you were being"
                        + "\nwatched at any given moment. How often, or on what system, the Thought Police plugged in on any individual wire was guesswork."
                        + "\nIt was even conceivable that they watched everybody all the time. But at any rate they could plug in your wire whenever they wanted"
                        + "\nto. You had to live -- did live, from habit that became instinct -- in the assumption that every sound you made was overheard, and, "
                        + "\nexcept in darkness, every movement scrutinized. "

                        + "\n\nWinston kept his back turned to the telescreen. It was safer, though, as he well knew, even a back can be"
                        + "\nrevealing. A kilometre away the Ministry of Truth, his place of work, towered vast and white above the grimy landscape. This, he"
                        + "\nthought with a sort of vague distaste -- this was London, chief city of Airstrip One, itself the third most populous of the provinces"
                        + "\nof Oceania. He tried to squeeze out some childhood memory that should tell him whether London had always been quite like this. Were "
                        + "\nthere always these vistas of rotting nineteenth-century houses, their sides shored up with baulks of timber, their windows patched "
                        + "\nwith cardboard and their roofs with corrugated iron, their crazy garden walls sagging in all directions? And the bombed sites where"
                        + "\nthe plaster dust swirled in the air and the willow-herb straggled over the heaps of rubble; and the places where the bombs had cleared"
                        + "\na larger patch and there had sprung up sordid colonies of wooden dwellings like chicken-houses? But it was no use, he could not remember: "
                        + "\nnothing remained of his childhood except a series of bright-lit tableaux occurring against no background and mostly unintelligible. "
                        + "\nThe Ministry of Truth -- Minitrue, in Newspeak -- was startlingly different from any other object in sigh"
                        + "\n. It was an enormous pyramidal structure of glittering white concrete, soaring up, terrace after terrace, 300 metres into the air. "
                        + "\nFrom where Winston stood it was just possible to read, picked out on its white face in elegant lettering, the three slogans of the Party:"
                        + "\nWAR IS PEACE"
                        + "\nFREEDOM IS SLAVERY"
                        + "\nIGNORANCE IS STRENGTH");
    }

    private void observeBookCover() {
        Printer.println("The front cover says Nineteen-eighty four written by George Orwell");
    }
}
