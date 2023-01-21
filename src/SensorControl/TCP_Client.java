package SensorControl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import Commands.CommandsDirectory;
import General.ThreadTermination;
import General.UserLocation;

public class TCP_Client implements Runnable, ThreadTermination {

    String host;
    int port;
    Thread t;

    SensorCommand[] commands;
    testingFunction[] testers;
    CommandsDirectory cd;

    SensorCommand[] commandsToTest;

    private static TCP_Client instance;

    boolean killServer = false;
    boolean connectionEstablished;
    boolean connectionLost;

    Socket s;

    InputStreamReader in;
    BufferedReader bin;
    JSONObject jsonOb;
    JSONParser jsonPar;

    SensorCommandExecution toExecute;

    private TCP_Client(String host, int port) {

        this.host = host;
        this.port = port;

        jsonOb = new JSONObject();
        jsonPar = new JSONParser();

        commands = SensorCommand.getSensorCommands();
        commandsToTest = commands;

        connectionEstablished = false;
        connectionLost = true;

        testers = new testingFunction[] {
                () -> {
                    return testPunchRight();
                },
                () -> {
                    return testPunchLeft();
                },
                () -> {
                    return testDodgeRight();
                },
                () -> {
                    return testDodgeLeft();
                },
                () -> {
                    return testLifting();
                },
                () -> {
                    return testShooting();
                },
                () -> {
                    return testSleeping();
                },
                () -> {
                    return testSwingingHammer();
                },
        };
        cd = new CommandsDirectory(this);
        t = new Thread(this);
        t.setName("TCP_CLIENT");
        t.start();
        toExecute = (sensorCommand) -> {
        };
    }

    public static TCP_Client getInstance() {
        if (instance == null)
            instance = new TCP_Client(UserLocation.getInstance().getHost(), UserLocation.getInstance().getPort());
        return instance;
    }

    public SensorCommand[] getCommands() {
        return commands;
    }

    public testingFunction[] getTesters() {
        return testers;
    }

    public void run() {
        while (connectionLost && !killServer) {

            establishConnection();

            try {
                readLines();
                if (!killServer) {
                    connectionLost = true;
                    connectionEstablished = false;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                connectionLost = true;
                connectionEstablished = false;
            }
        }

    }

    private void readLines() throws IOException, ParseException, InterruptedException {
        String response = bin.readLine();
        testingFunction tester;

        while (response != null && !killServer) {

            SensorCommand[] currentCommandsToTest = commandsToTest;
            SensorCommandExecution currentToExecute = toExecute;

            jsonOb = (JSONObject) jsonPar.parse(response);

            for (SensorCommand command : currentCommandsToTest) {
                if (cd.foundTestingCommand(command)) {
                    tester = cd.getTester(command);
                    if (tester.test()) {
                        bin.readLine();
                        bin.readLine();
                        currentToExecute.execute(command);
                    }
                }

            }
            response = bin.readLine();

        }
    }

    // private void printJSONOb() {
    // JSONObject accel = (JSONObject) jsonOb.get("accelerometer");
    // JSONArray accelValues = (JSONArray) accel.get("value");
    // JSONObject gyro = (JSONObject) jsonOb.get("gyroscope");
    // JSONArray gyroValues = (JSONArray) gyro.get("value");
    // JSONObject linearAccel = (JSONObject) jsonOb.get("linearAcceleration");
    // JSONObject rotationalVector = (JSONObject) jsonOb.get("rotationVector");
    // JSONArray linearAccelValues = (JSONArray) linearAccel.get("value");
    // JSONArray rotationalVectorValues = (JSONArray) rotationalVector.get("value");

    // System.out.println(rotationalVectorValues);

    // }

    private void establishConnection() {
        while (!connectionEstablished && !killServer) {
            try {
                // Thread.sleep(1000);
                s = new Socket(host, port);
                in = new InputStreamReader(s.getInputStream());
                bin = new BufferedReader(in);
                connectionEstablished = true;
                connectionLost = false;

            } catch (Exception e) {
                // Printer.println(e.getMessage());
            }
        }
    }

    public synchronized void updateSampling(SensorCommand[] s, SensorCommandExecution toExecute) {
        this.commandsToTest = s;
        this.toExecute = toExecute;
    }

    private boolean testPunchRight() {
        JSONObject accel = (JSONObject) jsonOb.get("accelerometer");
        JSONArray accelValues = (JSONArray) accel.get("value");

        if ((double) accelValues.get(0) > 25)
            return true;
        return false;
    }

    private boolean testPunchLeft() {
        JSONObject accel = (JSONObject) jsonOb.get("accelerometer");
        JSONArray accelValues = (JSONArray) accel.get("value");

        if ((double) accelValues.get(0) < -25)
            return true;
        return false;
    }

    private boolean testDodgeRight() {
        JSONObject gyro = (JSONObject) jsonOb.get("gyroscope");
        JSONArray gyroValues = (JSONArray) gyro.get("value");

        if ((double) gyroValues.get(0) < -15)
            return true;
        return false;
    }

    private boolean testDodgeLeft() {
        JSONObject gyro = (JSONObject) jsonOb.get("gyroscope");
        JSONArray gyroValues = (JSONArray) gyro.get("value");

        if ((double) gyroValues.get(0) > 15)
            return true;
        return false;
    }

    private boolean testLifting() {

        JSONObject accel = (JSONObject) jsonOb.get("accelerometer");
        JSONArray accelValues = (JSONArray) accel.get("value");

        if (((double) accelValues.get(2) > 20))
            return true;
        return false;
    }

    private boolean testShooting() {
        JSONObject rotationalVector = (JSONObject) jsonOb.get("rotationVector");
        JSONArray rotationalVectorValues = (JSONArray) rotationalVector.get("value");

        if ((double) rotationalVectorValues.get(0) > 0.5)
            return true;
        return false;
    }

    private boolean testSleeping() {
        JSONObject light = (JSONObject) jsonOb.get("light");
        double lightIntensity = (double) light.get("value");
        if (lightIntensity < 3)
            return true;
        return false;
    }

    private boolean testSwingingHammer() {
        JSONObject gyro = (JSONObject) jsonOb.get("gyroscope");
        JSONArray gyroValues = (JSONArray) gyro.get("value");

        if ((double) gyroValues.get(2) > 10)
            return true;
        return false;
    }

    public void killServer() {
        try {
            killServer = true;
            s.close();
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    @Override
    public void killThread() {
        // TODO Auto-generated method stub
        killServer();

    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    public Thread getT() {
        return t;
    }

    public CommandsDirectory getCd() {
        return cd;
    }

    public SensorCommand[] getCommandsToTest() {
        return commandsToTest;
    }

    public boolean isKillServer() {
        return killServer;
    }

    public boolean isConnectionEstablished() {
        return connectionEstablished;
    }

    public boolean isConnectionLost() {
        return connectionLost;
    }

    public Socket getS() {
        return s;
    }

    public InputStreamReader getIn() {
        return in;
    }

    public BufferedReader getBin() {
        return bin;
    }

    public JSONObject getJsonOb() {
        return jsonOb;
    }

    public JSONParser getJsonPar() {
        return jsonPar;
    }

    public SensorCommandExecution getToExecute() {
        return toExecute;
    }

    public void updateSensorInfo(String host2, int port2) {
        host = host2;
        port = port2;
    }

    // public static void main(String[] args) {
    // SensorCommand[] x = new SensorCommand[] { SensorCommand.PUNCH_LEFT };
    // // String[] x = new String[] { "punchLeft" };
    // // String[] x = new String[] { "Lift" };
    // // String[] x = new String[] { "Shoot" };
    // // String[] x = new String[] { "Sleep" };
    // // String[] x = new String[] { "SwingHammer" };
    // // String[] x = new String[] { "Lift" };
    // // String[] x = new String[] { "dodgeRight" };
    // // String[] x = new String[] { "dodgeLeft" };
    // // String[] x = new String[] { "punchRight", "punchLeft", "dodgeRight",
    // // "dodgeLeft" };
    // TCP_Client tc = new TCP_Client("192.168.137.89", 4242);
    // tc.updateSampling(new SensorCommand[] { SensorCommand.SHOOT_HOOP },
    // (sensorCommand) -> System.out.println("shoot"));

    // }

}
