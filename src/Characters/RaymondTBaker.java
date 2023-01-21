package Characters;

import java.io.FileInputStream;

import IO.Printer;

public class RaymondTBaker extends Character {

    public RaymondTBaker() {
        super("Warden Raymond T.Baker", false, true,
                "Bruv I seriously don't get you...how and why on earth would you try to fight the warden...Like what's your game plan??");
    }

    @Override
    public void endConversation() {
        Printer.println("[" + this.getName() + "] Take care ");
    }

    @Override
    public void introduceCharacter() {
        Printer.println("[" + this.getName() + "] Hello ");

    }

    @Override
    public void openQuestionStream() {
        try {
            setQuestionStream(new FileInputStream("CharacterLines\\RaymondTBakerQuestions.txt"));
        } catch (Exception e) {
            Printer.println(e.getMessage());
        }
    }

    @Override
    public void openResponseStream() {
        try {
            setResponseStream(new FileInputStream("CharacterLines\\RaymondTBakerResponses.txt"));
        } catch (Exception e) {
            Printer.println(e.getMessage());
        }

    }

}
