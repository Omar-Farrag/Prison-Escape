package Music;

import javax.sound.sampled.*;

import General.ThreadTermination;
import IO.*;
import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

public class DJ implements Runnable, ThreadTermination {

    String audioFilePath;
    Thread t;
    boolean stop = false;
    boolean currentlyPlaying = false;
    boolean loop = false;
    Queue<Boolean> loopingInformation = new LinkedList<>();

    private static DJ instance;

    private DJ() {
    }

    public synchronized Thread play(Track track, boolean playSolo, boolean loop) {

        loopingInformation.add(loop);

        if (currentlyPlaying && playSolo) {
            stop = true;
        }
        try {
            Thread.sleep(500);
        } catch (Exception e) {
            Printer.print(e.getMessage());
        }
        this.audioFilePath = track.getTrackName();
        stop = false;
        currentlyPlaying = true;
        t = new Thread(this);
        t.setName("DJ THREAD");
        t.start();
        return t;
    }

    public void stop() {
        stop = true;
    }

    public void run() {

        boolean currentlyLooping = loopingInformation.remove();
        boolean playedOnce = false;
        String currentAudioFilePath = audioFilePath;
        while ((currentlyLooping || !playedOnce) && !stop) {
            try {
                playedOnce = true;
                InputStream inputStream = DJ.class.getClassLoader().getResourceAsStream(currentAudioFilePath);
                AudioInputStream audioStream = AudioSystem.getAudioInputStream(inputStream);

                AudioFormat audioFormat = audioStream.getFormat();

                SourceDataLine sourceDataLine = AudioSystem.getSourceDataLine(audioFormat);
                sourceDataLine.open(audioFormat);
                sourceDataLine.start();

                final int BUFFER_SIZE = 4096;
                byte[] bufferBytes = new byte[BUFFER_SIZE];
                int readBytes = audioStream.read(bufferBytes);
                while (readBytes != -1 && !stop) {
                    sourceDataLine.write(bufferBytes, 0, readBytes);
                    readBytes = audioStream.read(bufferBytes);
                }
                if (stop)
                    currentlyLooping = false;
                sourceDataLine.drain();
                sourceDataLine.close();
                audioStream.close();

            } catch (Exception e) {
                // Printer.print(e.getMessage());
            }
        }
    }

    public static DJ getInstance() {
        if (instance == null)
            instance = new DJ();
        return instance;
    }

    @Override
    public void killThread() {
        // TODO Auto-generated method stub
        stop();
    }

    public static void main(String[] args) {
        DJ d = new DJ();
        // d.play(Track.BRAWL, false, false);
        // d.play(Track.PUNCH, false, true);
        // d.play(Track.DODGE, false, true);
        try {
            // Thread.sleep(5000);
            // d.play(Track.BILLIE_JEAN, true, true);
            // d.play(Track.VICTORY, true, true);
            d.play(Track.BRAWL, true, true);
            Thread.sleep(4000);
            d.play(Track.DEATH_SOUND, true, false).join();
            // d.play(Track.GETTING_BEATEN, false, false);
        } catch (Exception e) {
            Printer.println(e.getMessage());
        }

    }

}
