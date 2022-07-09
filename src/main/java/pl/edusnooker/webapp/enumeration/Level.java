package pl.edusnooker.webapp.enumeration;

public enum Level {
    WHITE(0, 2),
    RED(1,1),
    YELLOW(2, 1),
    GREEN(3,1),
    BROWN(4,1),
    BLUE(5, 1),
    PINK(6, 1),
    BLACK(7, 1);

    private int numberOfPointToTarget;
    private int numberLevel;

    Level(int numberLevel, int numberOfPointToTarget) {
        this.numberOfPointToTarget = numberOfPointToTarget;
        this.numberLevel = numberLevel;
    }

    public int getNumberOfPointToTarget() {
        return numberOfPointToTarget;
    }

    public int getNumberLevel() {
        return numberLevel;
    }

    public void setNumberOfPointToTarget(int numberOfPointToTarget) {
        this.numberOfPointToTarget = numberOfPointToTarget;
    }
}
