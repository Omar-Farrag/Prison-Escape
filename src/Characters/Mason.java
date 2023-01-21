package Characters;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import IO.Printer;

public class Mason extends Character {
    public Mason(String name) {
        super("Mason", true, true, "");
    }

    @Override
    public void endConversation() {
        Printer.println("[" + this.getName() + "]" + "GET LOST!");
    }

    @Override
    public void introduceCharacter() {
        Printer.println("[" + this.getName() + "]" + "HEY YOU!!!");
    }

    @Override
    public void openQuestionStream() {
        try {
            setQuestionStream(new FileInputStream("CharacterLines\\MasonQuestions.txt"));
        } catch (FileNotFoundException e) {
        }
    }

    @Override
    public void openResponseStream() {
        try {
            setResponseStream(new FileInputStream("CharacterLines\\MasonResponses.txt"));
        } catch (FileNotFoundException e) {
        }
    }
}
