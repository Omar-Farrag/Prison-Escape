package Characters;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import IO.Printer;

public class Andy extends Character {
    public Andy() {
        super("Andy", false, true, "");
    }

    @Override
    public void endConversation() {
        Printer.println("[" + this.getName() + "] " + "Remember to stay out of trouble");
    }

    @Override
    public void introduceCharacter() {
        Printer.println("[" + this.getName() + "] " + "Hey newbiew come here");
    }

    @Override
    public void openQuestionStream() {
        try {
            setQuestionStream(new FileInputStream("CharacterLines\\AndyQuestions.txt"));
        } catch (FileNotFoundException e) {
        }
    }

    @Override
    public void openResponseStream() {
        try {
            setResponseStream(new FileInputStream("CharacterLines\\AndyResponses.txt"));
        } catch (FileNotFoundException e) {
        }
    }

}
