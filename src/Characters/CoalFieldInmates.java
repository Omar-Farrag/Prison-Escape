package Characters;

import IO.Printer;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

//zayed
//plural, not a single person
public class CoalFieldInmates extends Character {

    public CoalFieldInmates() {
        super("CoalField Inmates", true);

    }

    @Override
    public void openQuestionStream() {
        try {
            setQuestionStream(new FileInputStream("CharacterLines\\CoalFieldInmatesQuestions.txt"));
        } catch (FileNotFoundException e) {
        }
    }

    @Override
    public void openResponseStream() {
        try {
            setResponseStream(new FileInputStream("CharacterLines\\CoalFieldInmatesResponses.txt"));
        } catch (FileNotFoundException e) {
        }

    }

    @Override
    public void introduceCharacter() {
        Printer.println("[" + this.getName() + "]" + " Gringo, i never seen you before. We are the workers here");
    }

    @Override
    public void endConversation() {
        Printer.println("[" + this.getName() + "]" + " Nice meeting you homie");
    }
}
