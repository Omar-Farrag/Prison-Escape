package Characters;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import IO.Printer;

public class Brian extends Character {
    public Brian(String name) {
        super(name, false, false, "You wanna lose your job or wut?");
    }

    @Override
    public void endConversation() {
        Printer.println("[" + this.getName() + "]" + " Don't be late for tomorrow");
    }

    @Override
    public void introduceCharacter() {
        Printer.println("[" + this.getName() + "]" + " HEYY!! YOU'RE LATE!! THERE IS SO MUCH WORK WE NEED TO DO");
    }

    @Override
    public void openQuestionStream() {
        try {
            setQuestionStream(new FileInputStream("CharacterLines\\BrianQuestions.txt"));
        } catch (FileNotFoundException e) {
        }
    }

    @Override
    public void openResponseStream() {
        try {
            setResponseStream(new FileInputStream("CharacterLines\\BrianResponses.txt"));

        } catch (FileNotFoundException e) {
        }
    }
}
