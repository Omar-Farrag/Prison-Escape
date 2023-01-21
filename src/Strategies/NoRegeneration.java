package Strategies;

public class NoRegeneration implements RegenerationStrategy {

    @Override
    public int getRegeneratedAmount() {
        return 0;
    }
}
