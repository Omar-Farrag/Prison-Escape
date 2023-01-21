package Characters;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import IO.Printer;

public class Luke extends Character {
    public Luke() {
        super("Luke", false, true, "");
    }

    @Override
    public void endConversation() {
        Printer.println("[" + this.getName() + "] " + "Cya later man");
    }

    @Override
    public void introduceCharacter() {
        Printer.println("[" + this.getName() + "] " + "[ACTING ALL DEPRESSED]");
    }

    @Override
    public void openQuestionStream() {
        try {
            setQuestionStream(new FileInputStream("CharacterLines\\LukeQuestions.txt"));
        } catch (FileNotFoundException e) {
        }
    }

    @Override
    public void openResponseStream() {
        try {
            setResponseStream(new FileInputStream("CharacterLines\\LukeResponses.txt"));
        } catch (FileNotFoundException e) {
        }
    }
}
