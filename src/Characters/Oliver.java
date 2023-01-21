package Characters;

import java.io.FileInputStream;

import IO.Printer;

public class Oliver extends Character {

    public Oliver() {
        super("Oliver", false, false, "Seems to me like you're in the mood to lose your job");
    }

    @Override
    public void endConversation() {
        Printer.println("[" + this.getName() + "] Have a nice day");
    }

    @Override
    public void introduceCharacter() {
        Printer.println("[" + this.getName() + "] Hello i am Olver, the Workshop supervisor");

    }

    @Override
    public void openQuestionStream() {
        try {
            setQuestionStream(new FileInputStream("CharacterLines\\OliverQuestions.txt"));
        } catch (Exception e) {
            Printer.println(e.getMessage());
        }
    }

    @Override
    public void openResponseStream() {
        try {
            setResponseStream(new FileInputStream("CharacterLines\\OliverResponses.txt"));
        } catch (Exception e) {
            Printer.println(e.getMessage());
        }

    }

}
