package Characters;

import IO.Printer;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

//zayed
public class MortuaryAssistant extends Character {

    public MortuaryAssistant() {
        super("MortuaryAssistant", false);
    }

    @Override
    public void openQuestionStream() {
        try {
            setQuestionStream(new FileInputStream("CharacterLines\\MortuaryAssistantQuestions.txt"));
        } catch (FileNotFoundException e) {
            Printer.println("error");
        }

    }

    @Override
    public void openResponseStream() {
        try {
            setResponseStream(new FileInputStream("CharacterLines\\MortuaryAssistantResponses.txt"));
        } catch (FileNotFoundException e) {

        }
    }

    @Override
    public void introduceCharacter() {
        Printer.println("[" + this.getName() + "]" + " You look up to no good");
    }

    @Override
    public void endConversation() {
        Printer.println("[" + this.getName() + "]" + " If you need anything, tell me");
    }
}
