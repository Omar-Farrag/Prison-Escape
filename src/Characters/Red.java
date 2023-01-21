package Characters;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import IO.Printer;

public class Red extends Character {
    public Red() {
        super("Red", false, true, "");
    }

    @Override
    public void introduceCharacter() {
        Printer.println("[" + this.getName() + "] " + "Hey there new boy...you need anything");
    }

    @Override
    public void endConversation() {
        Printer.println("[" + this.getName() + "] " + "Lemme know if you need anything");
    }

    @Override
    public void openQuestionStream() {
        try {
            setQuestionStream(new FileInputStream("CharacterLines\\RedQuestions.txt"));
        } catch (FileNotFoundException e) {
        }
    }

    @Override
    public void openResponseStream() {
        try {
            setResponseStream(new FileInputStream("CharacterLines\\RedResponses.txt"));
        } catch (FileNotFoundException e) {
        }
    }
}
