package Commands;

import java.util.HashMap;

import Items.Item;
import Locations.*;
import SensorControl.SensorCommand;
import SensorControl.TCP_Client;
import SensorControl.testingFunction;

public class CommandsDirectory {

    // Don't alter this class
    Location currentLocation;
    HashMap<String, CommandExecution> ce;

    TCP_Client tcp_client;
    HashMap<SensorCommand, testingFunction> testingFuncsMap;

    public CommandsDirectory(Location loc) {
        this.currentLocation = loc;
        ce = new HashMap<>();
        String[] commands = currentLocation.getCommands();
        CommandExecution[] commandFuncs = currentLocation.getCommandFuncs();
        for (int i = 0; i < currentLocation.getCommands().length; i++)
            ce.put(commands[i], commandFuncs[i]);
    }

    public CommandsDirectory(TCP_Client tc) {
        tcp_client = tc;
        testingFuncsMap = new HashMap<>();
        SensorCommand[] commands = tc.getCommands();
        testingFunction[] testers = tc.getTesters();

        for (int i = 0; i < tc.getCommands().length; i++)
            testingFuncsMap.put(commands[i], testers[i]);

    }

    public CommandsDirectory(Item i) {
        ce = new HashMap<>();
        String[] commands = i.getCommands();
        CommandExecution[] commandFuncs = i.getCommandFuncs();
        for (int j = 0; j < commands.length; j++)
            ce.put(commands[j], commandFuncs[j]);
    }

    public boolean foundCommand(String command) {
        return ce.containsKey(command);
    }

    public boolean foundTestingCommand(SensorCommand command) {
        return testingFuncsMap.containsKey(command);
    }

    public CommandExecution getCommandFunc(String command) {
        return ce.get(command);
    }

    public testingFunction getTester(SensorCommand command) {
        return testingFuncsMap.get(command);
    }

}
