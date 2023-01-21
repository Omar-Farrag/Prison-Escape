package Characters;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import IO.Printer;

public class Willy extends Character {
    public Willy() {
        super("Willy", false, false,
                "What kind of person just randomly starts a fight with the librarian??? What did the guy do to you???");
    };

    @Override
    public void endConversation() {
        Printer.println("[" + this.getName() + "]" + " Enjoy your time in the library");
    };

    @Override
    public void introduceCharacter() {
        Printer.println("[" + this.getName() + "]" + " Oh hello there..Welcome to the library");
    };

    @Override
    public void openQuestionStream() {
        try {
            setQuestionStream(new FileInputStream("CharacterLines\\WillyQuestionst.txt"));
        } catch (FileNotFoundException e) {
        }
    };

    @Override
    public void openResponseStream() {
        try {
            setResponseStream(new FileInputStream("CharacterLines\\WillyResponses.txt"));
        } catch (FileNotFoundException e) {
        }
    }
}
