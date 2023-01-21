package Characters;

public interface Conversation {

    public void conversate();

    public void openQuestionStream();

    public void openResponseStream();

    public void initReaderStreams();

    public void introduceCharacter();

    public void readLines();

    public void endConversation();

    public void closeAllStreams();

}
