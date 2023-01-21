package Commands;

import java.util.HashMap;

import General.*;
import IO.Printer;
import Items.Item;

public class CommandControl {

    UserLocation ul;
    String[] unwantedWords;
    HashMap<String, Integer> string_to_length;

    private static CommandControl instance;

    private CommandControl(UserLocation ul) {
        this.ul = ul;
        initUnwantedWordsMap();
    }

    public static synchronized CommandControl getInstance(UserLocation ul) {
        if (instance == null)
            return instance = new CommandControl(ul);
        return instance;
    }

    public void executeCommand(String userInput) {

        String[] words = userInput.split(" ", 2);
        String command = words[0];
        String commandRecipient = words.length == 1 ? "" : words[1];

        // Removes words such as "the", "on","from","in", etc
        commandRecipient = extractKeyText(commandRecipient);

        // Checks if the user's input contains the name of an item in current location
        Item itemInLocation = ul.findItem(commandRecipient);

        // Checks if the user's input contains the name of an item in player's inventory
        Item itemInInventory = ul.getMobileInventory().findItem(commandRecipient);

        // Command is searched in the currentLocation's list of commands
        // Executed if found
        if (ul.getCurrentLocation().getCd().foundCommand(command)) {
            CommandExecution func = ul.getCurrentLocation().getCd().getCommandFunc(command);
            func.execute(command, commandRecipient);
        }

        // Command not found in currentLocation's list of commands
        // Therefore if the item was found in the current location or inventory it's
        // executed
        else if (itemInLocation != null) {
            itemInLocation.useItem(command, commandRecipient);
        } else if (itemInInventory != null) {
            itemInInventory.useItem(command, commandRecipient);
        }

        else if (ul.getCd().foundCommand(command)) {
            CommandExecution func = ul.getCd().getCommandFunc(command);
            func.execute(command, commandRecipient);
        } else if (command.endsWith("?")) {

            String itemName = command.substring(0, command.length() - 1);
            Item itemLoc = ul.findItem(itemName);
            Item itemInv = ul.getMobileInventory().findItem(itemName);

            if (itemLoc != null)
                itemLoc.printItemCommands();
            else if (itemInv != null)
                itemInv.printItemCommands();
            else
                Printer.println("Unrecognized Command in this location");

        } else {
            Printer.println("Unrecognized Command in this location");
            return;
        }
        ul.incrementInstructions();

    }

    private String extractKeyText(String commandRecipient) {

        if (commandRecipient.isBlank())
            return "";
        String firstWord = commandRecipient.split(" ")[0];
        if (string_to_length.containsKey(firstWord))
            return extractKeyText(commandRecipient.substring(firstWord.length()).trim());

        else
            return (firstWord += " " + extractKeyText(commandRecipient.substring(firstWord.length()).trim())).trim();

    }

    private void initUnwantedWordsMap() {
        unwantedWords = new String[] {
                "to", "a", "in",
                "the", "teeth",
                "through", "with", "on",
                "at", "up", "using", "your",
                "from", "inside", "food"
        };
        string_to_length = new HashMap<>(unwantedWords.length);

        for (String word : unwantedWords) {
            string_to_length.put(word, word.length());
        }
    }
}
