package pl.edusnooker.webapp.component.progress.dto;

import lombok.Data;

@Data
public class ProgressExerciseDto implements Comparable<ProgressExerciseDto> {

    private int idExercise;
    private String nameExercise;
    private int numberOfAttempts;
    private int recentActivity;
    private int lastResult;
    private int theBestResult;
    private int resultToPass;
    private boolean isPass = false;


    @Override
    public int compareTo(ProgressExerciseDto e) {
        if (idExercise > e.idExercise)
            return 1;
        else if (idExercise < e.idExercise)
            return -1;
        return 0;
    }


}
