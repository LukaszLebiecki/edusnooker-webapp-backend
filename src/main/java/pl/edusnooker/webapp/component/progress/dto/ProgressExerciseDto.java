package pl.edusnooker.webapp.component.progress.dto;

import lombok.Data;

@Data
public class ProgressExerciseDto implements Comparable<ProgressExerciseDto> {

    private String idExercise;
    private String nameExercise;
    private int numberOfAttempts;
    private int recentActivity;
    private int lastResult;
    private int theBestResult;
    private int resultToPass;
    private boolean isPass = false;


    @Override
    public int compareTo(ProgressExerciseDto o) {
        return this.idExercise.compareTo(o.idExercise);
    }
}
