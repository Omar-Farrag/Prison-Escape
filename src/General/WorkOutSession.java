package General;

import IO.Printer;
import Items.Dumbbell;
import Locations.OpenYard;
import SensorControl.SensorCommand;
import SensorControl.TCP_Client;

public class WorkOutSession implements Runnable, ThreadTermination {

    private boolean workingOut;
    private Dumbbell dumbbell;
    private OpenYard openYard;
    private int reps;
    private Thread t;
    private TCP_Client tc;

    public WorkOutSession(Dumbbell dumbbell, OpenYard openYard) {
        workingOut = true;
        this.dumbbell = dumbbell;
        dumbbell.setWorkOutSession(this);
        tc = TCP_Client.getInstance();
        t = new Thread(this);
        UserLocation.getInstance().addThread(this);
        t.setName("WORKOUT THREAD");
        t.start();
        this.openYard = openYard;
    }

    @Override
    public void run() {
        Printer.println("You will now begin working out ");
        workOut();

    }

    private void workOut() {

        tc.updateSampling(new SensorCommand[] { SensorCommand.Lift }, (sensorCommand) -> {
            if (workingOut)
                dumbbell.lift();
        });
        // tc.startSampling();

    }

    public void endWorkout() {
        workingOut = false;
        dumbbell.setWorkOutSession(null);
        // tc.endSampling();
    }

    public synchronized void liftedOnce() {
        reps++;
        if (reps >= 10) {
            Printer.println(
                    "Alright chief that's good enough for one workout session...have some rest and come back later ");

            openYard.endWorkOut();
        }
    }

    @Override
    public void killThread() {
        // TODO Auto-generated method stub
        endWorkout();

    }
}
