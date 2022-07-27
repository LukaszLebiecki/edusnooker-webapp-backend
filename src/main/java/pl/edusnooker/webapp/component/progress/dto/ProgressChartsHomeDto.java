package pl.edusnooker.webapp.component.progress.dto;

import lombok.Data;

@Data
public class ProgressChartsHomeDto {
    private int[] chartsHome;

    private int[] pointsScoredToYear;
    private int[] exercisesPerformedToYear;
    private int[] exercisesCompletedToYear;

    private int[] pointsScoredToMonth;
    private int[] exercisesPerformedToMonth;
    private int[] exercisesCompletedToMonth;

    private int[] pointsScoredToWeek;
    private int[] exercisesPerformedToWeek;
    private int[] exercisesCompletedToWeek;

    private int[] pointsScoredToHour;
    private int[] exercisesPerformedToHour;
    private int[] exercisesCompletedToHour;
}
