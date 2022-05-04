package pl.edusnooker.webapp.component.exercise;

public enum Level {
    WHITE(0, 3),
    RED(1,1),
    YELLOW(2, 1),
    GREEN(3,7),
    BROWN(4,7),
    BLUE(5, 7),
    PINK(6, 7),
    BLACK(7, 7);

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
