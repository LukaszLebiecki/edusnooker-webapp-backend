package pl.edusnooker.webapp.component.exercise;

public enum Level {
    WHITE(0, 8),
    RED(1,12),
    YELLOW(2, 5),
    GREEN(3,13),
    BROWN(4,23),
    BLUE(5, 14),
    PINK(6, 21),
    BLACK(7, 27);

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
