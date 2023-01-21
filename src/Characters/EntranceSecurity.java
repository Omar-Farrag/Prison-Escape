package Characters;

import IO.Printer;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class EntranceSecurity extends Character {
    public EntranceSecurity() {
        super("Entrance security", true, false, "MANNN YOU'RE SO CLOSE TO LIBERTY DONT RUIN IT!!!");
    }

    // i want the openquestionstream and openresponsestream to be when you are
    // escaping
    @Override
    public void openQuestionStream() {
        try {
            setQuestionStream(new FileInputStream("CharacterLines\\EntranceSecurityQuestions.txt"));
        } catch (FileNotFoundException e) {
        }
    };

    @Override
    public void openResponseStream() {
        try {
            setResponseStream(new FileInputStream("CharacterLines\\EntranceSecurityResponses.txt"));
        } catch (FileNotFoundException e) {
        }
    }

    @Override
    public void endConversation() {
        Printer.println("[" + this.getName() + "]" + " Have fun in prison");
    };

    @Override
    public void introduceCharacter() {
        Printer.println("[" + this.getName() + "]" + " New recruit, whats the update on the escaped convict");
    };
}
