package Characters;

import java.io.FileInputStream;

import IO.Printer;

public class inmatetwo extends Character {

    public inmatetwo() {
        super("inmatetwo", false);
    }

    @Override
    public void endConversation() {
        Printer.println("[" + this.getName() + "] go away!");
    }

    @Override
    public void introduceCharacter() {
        Printer.println("[" + this.getName() + "] What are you looking at! ");

    }

    @Override
    public void openQuestionStream() {
        try {
            setQuestionStream(new FileInputStream("CharacterLines\\inmatetwoQuestions.txt"));
        } catch (Exception e) {
            Printer.println(e.getMessage());
        }
    }

    @Override
    public void openResponseStream() {
        try {
            setResponseStream(new FileInputStream("CharacterLines\\inmatetwoResponses.txt"));
        } catch (Exception e) {
            Printer.println(e.getMessage());
        }

    }

}
