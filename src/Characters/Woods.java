package Characters;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import IO.Printer;

public class Woods extends Character {
    public Woods(String name) {
        super(name, true, true, "");
    }

    @Override
    public void endConversation() {
        Printer.println("[" + this.getName() + "]" + "LEAVE BEFORE I CHANGE MY MIND");
    }

    @Override
    public void introduceCharacter() {
        Printer.println("[" + this.getName() + "]" + "WHAT ARE YOU DOING HERE?");
    }

    @Override
    public void openQuestionStream() {
        try {
            setQuestionStream(new FileInputStream("CharacterLines\\WoodsQuestions.txt"));
        } catch (FileNotFoundException e) {
        }
    }

    @Override
    public void openResponseStream() {
        try {
            setResponseStream(new FileInputStream("CharacterLines\\WoodsResponses.txt"));
        } catch (FileNotFoundException e) {
        }
    }
}
