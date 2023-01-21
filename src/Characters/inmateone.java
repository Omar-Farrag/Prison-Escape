package Characters;

import java.io.FileInputStream;

import IO.Printer;

public class inmateone extends Character {

    public inmateone() {
        super("inmateone", false);
    }

    @Override
    public void endConversation() {
        Printer.println("[" + this.getName() + "] see you in the next life...");
    }

    @Override
    public void introduceCharacter() {
        Printer.println("[" + this.getName() + "] Hello.. ");

    }

    @Override
    public void openQuestionStream() {
        try {
            setQuestionStream(new FileInputStream("CharacterLines\\inmateoneQuestions.txt"));
        } catch (Exception e) {
            Printer.println(e.getMessage());
        }
    }

    @Override
    public void openResponseStream() {
        try {
            setResponseStream(new FileInputStream("CharacterLines\\inmateoneResponses.txt"));
        } catch (Exception e) {
            Printer.println(e.getMessage());
        }

    }

}