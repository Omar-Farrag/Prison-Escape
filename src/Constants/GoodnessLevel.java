package Constants;

public enum GoodnessLevel {
    any(-100), minToBeGOOD(50), maxToBeBAD(-25);

    private int level;

    private GoodnessLevel(int l) {
        level = l;
    }

    public int getLevel() {
        return level;
    }
}
