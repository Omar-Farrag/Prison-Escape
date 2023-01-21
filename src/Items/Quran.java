package Items;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;

import Commands.CommandExecution;
import Commands.CommandsDirectory;
import General.GoodnessIndex;
import IO.Inputter;
import IO.Printer;

public class Quran extends Item {

    InputStream is;
    InputStreamReader isr;
    BufferedReader br;

    public Quran() {

        name = "Qur'an";
        commands = new String[] { "read", "observe" };
        commandFuncs = new CommandExecution[] {
                (command, commandRecipient) -> {
                    readQuran();
                },
                (command, commandRecipient) -> {
                    observeQuran();
                },
        };
        try {
            is = new FileInputStream("CharacterLines\\Qur'anic Verses.txt");
            isr = new InputStreamReader(is);
            br = new BufferedReader(isr);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        cd = new CommandsDirectory(this);
        takeable = true;
    }

    private void observeQuran() {
        Printer.println("You hold the Qur'an in your hands and observe its appealing Islamic cover");
    }

    public void readQuran() {
        Printer.println("You open the Qur'an to a random page and start reading...");

        String response = "continue reading";
        boolean continueReading = true;
        while (continueReading) {
            if (response.equals("stop reading")) {
                continueReading = false;
                return;
            } else if (!response.equals("continue reading"))
                Printer.println("Didn't catch that... You wanna continue reading or do you wanna stop???");
            else {
                readVerse();
                GoodnessIndex.getInstance().increaseGoodnessIndex(5);
            }
            response = Inputter.input().toLowerCase().trim();
        }

    }

    public void readVerse() {
        try {
            String verse = br.readLine().trim();
            while (!verse.equals("") && verse != null) {
                Printer.println(verse);
                verse = br.readLine().trim();
            }
            if (verse == null) {
                is = new FileInputStream("CharacterLines\\Qur'anic Verses.txt");
                isr = new InputStreamReader(is);
                br = new BufferedReader(isr);
            }
        } catch (Exception e) {
        }
    }
}
