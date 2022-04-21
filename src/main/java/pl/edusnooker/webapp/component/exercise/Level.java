package pl.edusnooker.webapp.component.exercise;

public enum Level {
    WHITE(0, 8),
    RED(1,8),
    YELLOW(2, 8),
    GREEN(3,8),
    BROWN(4,8),
    BLUE(5, 8),
    PINK(6, 8),
    BLACK(7, 8);

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
