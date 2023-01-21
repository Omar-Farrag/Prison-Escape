package Characters;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import IO.Printer;

public class LaundryMan extends Character {

    public LaundryMan() {
        super("laundryman", false, false, "He is a poor laundryman, what are you doing??");
    }

    @Override
    public void openQuestionStream() {
        try {
            setQuestionStream(new FileInputStream("CharacterLines\\LaundrymanQuestions.txt"));
        } catch (FileNotFoundException e) {
        }

    }

    @Override
    public void openResponseStream() {
        try {
            setQuestionStream(new FileInputStream("CharacterLines\\LaundrymanResponses.txt"));
        } catch (FileNotFoundException e) {
        }
    }

    @Override
    public void introduceCharacter() {
        Printer.println("Hard day at prison today huh? Go look after your laundry");
    }

    @Override
    public void endConversation() {
        Printer.println("Anyway, See you later :)");
    }
}
