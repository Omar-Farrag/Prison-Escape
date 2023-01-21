package Items;

import Commands.CommandExecution;
import Commands.CommandsDirectory;
import General.UserLocation;
import IO.Printer;
import Locations.Infirmary;

public class AnestheticDrugs extends Item {

    private Infirmary infirmary;

    public AnestheticDrugs(Infirmary infirmary) {

        name = "Anesthetic Drugs";
        commands = new String[] { "spike drink" };
        commandFuncs = new CommandExecution[] {
                (command, commandRecipient) -> {
                    Spikedrink();
                },

        };
        cd = new CommandsDirectory(this);
        takeable = true;
        this.infirmary = infirmary;
    }

    private void Spikedrink() {
        Printer.println("You are now spiking a drink with the Anesthetic Drugs");
    }

    @Override
    public void useItem(String command, String commandRecipient) {
        if (command.equals("take") && !infirmary.isDoctorDistracted()) {
            Printer.println("[DOCTOR] WHAT DO YOU THINK YOU'RE DOING??");
            UserLocation.getInstance().getPunished();
        } else {
            super.useItem(command, commandRecipient);
        }
    }

}
