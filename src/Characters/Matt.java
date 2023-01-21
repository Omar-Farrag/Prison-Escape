package Characters;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import IO.Printer;

public class Matt extends Character {

    public Matt(String name) {
        super(name, false, false, "You shouldn't start trouble in the kitchen");
    }

    @Override
    public void endConversation() {
        Printer.println("[" + this.getName() + "]" + " Alright I'll go back to doing the dishes");
    }

    @Override
    public void introduceCharacter() {
        Printer.println("[" + this.getName() + "]" + " Yooo Yooo");
    }

    @Override
    public void openQuestionStream() {
        try {
            setQuestionStream(new FileInputStream("CharacterLines\\MattQuestions.txt"));
        } catch (FileNotFoundException e) {
        }
    }

    @Override
    public void openResponseStream() {
        try {
            setResponseStream(new FileInputStream("CharacterLines\\MattResponses.txt"));
        } catch (FileNotFoundException e) {
        }
    }

}
