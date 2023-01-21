package IO;

import General.CBFunc;
import ObserversAndSubjects.ConcreteSubject;
import ObserversAndSubjects.Message;
import ObserversAndSubjects.Topic;
import SensorControl.SensorCommand;

public class InputThread extends ConcreteSubject implements Runnable {

    Thread t;
    boolean end;
    CBFunc cb;

    public InputThread() {
        t = new Thread(this);
        t.setName("INPUT-THREAD");
        end = false;
        t.start();
    }

    public void run() {
        String response;
        while (!end) {
            Printer.print("> ");
            response = Inputter.input().toLowerCase().trim();
            switch (response) {
                case "dodge right":
                    publishMessage(new Message(this, Topic.ReceivedConsoleInput, SensorCommand.DODGE_RIGHT));
                    break;
                case "dodge left":
                    publishMessage(new Message(this, Topic.ReceivedConsoleInput, SensorCommand.DODGE_LEFT));
                    break;
                case "punch right":
                    publishMessage(new Message(this, Topic.ReceivedConsoleInput, SensorCommand.PUNCH_RIGHT));
                    break;
                case "punch left":
                    publishMessage(new Message(this, Topic.ReceivedConsoleInput, SensorCommand.PUNCH_LEFT));
                    break;
            }
        }
    }

    public void killThread() {
        end = true;
    }
}
