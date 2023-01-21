package Characters;

import java.io.FileInputStream;

import IO.Printer;

public class ImamHassan extends Character {

    public ImamHassan() {
        super("Imam Hassan", false, false,
                "Out of all people, you wanna fight the Imam of the Mosque??? ASTAGHFURALLAH...");
    }

    @Override
    public void endConversation() {
        Printer.println("[" + this.getName() + "] May God be with you");
    }

    @Override
    public void introduceCharacter() {
        Printer.println("[" + this.getName() + "] Assalamu'aleikum my brother");

    }

    @Override
    public void openQuestionStream() {
        try {
            setQuestionStream(new FileInputStream("CharacterLines\\ImamHassanQuestions.txt"));
        } catch (Exception e) {
            Printer.println(e.getMessage());
        }
    }

    @Override
    public void openResponseStream() {
        try {
            setResponseStream(new FileInputStream("CharacterLines\\ImamHassanResponses.txt"));
        } catch (Exception e) {
            Printer.println(e.getMessage());
        }

    }

}
