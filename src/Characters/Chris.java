package Characters;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import IO.Printer;

public class Chris extends Character {
    public Chris() {
        super("Chris", false, true, "");
    }

    @Override
    public void endConversation() {
        Printer.println("[" + this.getName() + "]" + " May God have mercy on your soul");
    }

    @Override
    public void introduceCharacter() {
        Printer.println("[" + this.getName() + "]" + " [LOOKS LIKE HE IS ABOUT TO CRY]");
    }

    @Override
    public void openQuestionStream() {
        try {
            setQuestionStream(new FileInputStream("CharacterLines\\ChrisQuestions.txt"));
        } catch (FileNotFoundException e) {
        }
    }

    @Override
    public void openResponseStream() {
        try {
            setResponseStream(new FileInputStream("CharacterLines\\ChrisResponses.txt"));
        } catch (FileNotFoundException e) {
        }
    }

}
