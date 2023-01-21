package Characters;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import IO.Printer;

public class Steve extends Character {

    public Steve() {
        super("Steve", false);
    }

    @Override
    public void endConversation() {
        Printer.println("[" + this.getName() + "]" + " Aite I'ma holla at you later");
    }

    @Override
    public void introduceCharacter() {
        Printer.println("[" + this.getName() + "]" + " Wassup man");

    }

    @Override
    public void openQuestionStream() {
        try {
            setQuestionStream(new FileInputStream("CharacterLines\\SteveQuestions.txt"));
        } catch (FileNotFoundException e) {
        }
    }

    @Override
    public void openResponseStream() {
        try {
            setResponseStream(new FileInputStream("CharacterLines\\SteveResponses.txt"));
        } catch (FileNotFoundException e) {
        }
    }

}
