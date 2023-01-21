package Characters;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import IO.Printer;

public class Brock extends Character {
    public Brock() {
        super("Brock", false, true, "");
    }

    @Override
    public void introduceCharacter() {
        Printer.println("[" + this.getName() + "] " + "[EYES LOOKING RED");
    }

    @Override
    public void endConversation() {
        Printer.println("[" + this.getName() + "] " + "I got my red eyes on you");
    }

    @Override
    public void openQuestionStream() {
        try {
            setQuestionStream(new FileInputStream("CharacterLines\\BrockQuestions.txt"));
        } catch (FileNotFoundException e) {
        }
    }

    @Override
    public void openResponseStream() {
        try {
            setResponseStream(new FileInputStream("CharacterLines\\BrockResponses.txt"));
        } catch (FileNotFoundException e) {
        }
    }
}
