package Characters;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import IO.Printer;

public class Jason extends Character {
    public Jason() {
        super("Jason", false);
    };

    @Override
    public void endConversation() {
        Printer.println("[" + this.getName() + "]" + " See you later main");
    };

    @Override
    public void introduceCharacter() {
        Printer.println("[" + this.getName() + "]" + " Eyyy wut up cuzz ");
    };

    // i want the openquestionstream and openresponsestream to be when you are
    // escaping
    @Override
    public void openQuestionStream() {
        try {
            setQuestionStream(new FileInputStream("CharacterLines\\JasonQuestions.txt"));
        } catch (FileNotFoundException e) {
        }
    };

    @Override
    public void openResponseStream() {
        try {
            setResponseStream(new FileInputStream("CharacterLines\\JasonResponses.txt"));
        } catch (FileNotFoundException e) {
        }
    }
}
