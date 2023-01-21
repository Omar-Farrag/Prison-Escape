package Escape;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import IO.Printer;
import Music.DJ;
import Music.Track;

public class EscapeSequence {
    FileInputStream fin;
    InputStreamReader isReader;
    BufferedReader buffReader;

    public EscapeSequence() {
        try {
            fin = new FileInputStream("src\\EscapeScript.txt");
            isReader = new InputStreamReader(fin);
            buffReader = new BufferedReader(isReader);
            Printer.println("");
            Printer.println("");
            beginEscapeSequence();
        } catch (Exception e) {
            Printer.println(e.getMessage());
        }
    }

    private void beginEscapeSequence() throws Exception {
        String line = buffReader.readLine();
        while (!line.equals(EscapeFileCommands.END.getName())) {

            if (line.equals(EscapeFileCommands.PAUSE.getName()))
                pause();
            else if (line.equals(EscapeFileCommands.TRACK.getName()))
                playTrack();
            else if (!line.equals(""))
                Printer.println(line);

            line = buffReader.readLine();
        }
        endEscapeSequence();
    }

    private void endEscapeSequence() {
        Printer.println("CONGRATULATIONS...YOU FINISHED THE GAME...WELL DONE!!!!");
    }

    private void playTrack() throws Exception {

        DJ.getInstance().play(Track.valueOf(buffReader.readLine()), false, false).join();

    }

    private void pause() throws Exception {
        Thread.sleep(Integer.parseInt(buffReader.readLine()) * 1000);
    }

}
