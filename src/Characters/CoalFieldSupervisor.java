package Characters;

import IO.Printer;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

//zayed
public class CoalFieldSupervisor extends Character {

    public CoalFieldSupervisor() {
        super("Dave", true, false, "Bro you're gonna lose you your job");

    }

    @Override
    public void openQuestionStream() {
        try {
            setQuestionStream(new FileInputStream("CharacterLines\\CoalFieldSupervisorQuestions.txt"));
        } catch (FileNotFoundException e) {
        }
    }

    @Override
    public void openResponseStream() {
        try {
            setResponseStream(new FileInputStream("CharacterLines\\CoalFieldSupervisorResponses.txt"));
        } catch (FileNotFoundException e) {
        }

    }

    @Override
    public void introduceCharacter() {
        Printer.println("[" + this.getName() + "]" + " Welcome, i see you are a new inmate");
    }

    @Override
    public void endConversation() {
        Printer.println("[" + this.getName() + "]" + " Go back to work or leave");
    }
}
