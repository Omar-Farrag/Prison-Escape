package Characters;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import IO.Printer;

public class Robert extends Character {
    public Robert() {
        super("Robert", false);
    };

    @Override
    public void endConversation() {
        Printer.println("[ " + this.getName() + " ]" + " Stay safe!");
    };

    @Override
    public void introduceCharacter() {
        Printer.println("[ " + this.getName() + " ]" + " Hello...how can i assist you?");
    };

    @Override
    public void openQuestionStream() {
        try {
            setQuestionStream(new FileInputStream("CharacterLines\\RobertQuestions.txt"));
        } catch (FileNotFoundException e) {
        }
    };

    @Override
    public void openResponseStream() {
        try {
            setResponseStream(new FileInputStream("CharacterLines\\RobertResponses.txt"));
        } catch (FileNotFoundException e) {
        }
    }
}
