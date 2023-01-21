package Characters;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import IO.Printer;

public class Walt extends Character {
    public Walt(String name) {
        super(name, false, false, "You shouldn't get in trouble in the kitchen otherwise you will lose your job");
    }

    @Override
    public void endConversation() {
        Printer.println("[" + this.getName() + "]" + " Let's get back to work before Brian gets angry");
    }

    @Override
    public void introduceCharacter() {
        Printer.println("[" + this.getName() + "]" + " Hello");
    }

    @Override
    public void openQuestionStream() {
        try {
            setQuestionStream(new FileInputStream("CharacterLines\\WaltQuestions.txt"));
        } catch (FileNotFoundException e) {
        }
    }

    @Override
    public void openResponseStream() {
        try {
            setResponseStream(new FileInputStream("CharacterLines\\WaltResponses.txt"));
        } catch (FileNotFoundException e) {
        }
    }

}
