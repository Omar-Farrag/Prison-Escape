package Commands;
@FunctionalInterface
public interface CommandExecution {
    void execute(String command, String commandRecipient);
}