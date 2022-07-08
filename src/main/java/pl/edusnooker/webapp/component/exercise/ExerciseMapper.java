package pl.edusnooker.webapp.component.exercise;

import pl.edusnooker.webapp.component.exercise.dto.ExerciseDto;

class ExerciseMapper {
    static ExerciseDto map(Exercise exercise) {
        ExerciseDto dto = new ExerciseDto();
        dto.setExerciseId(exercise.getExerciseId());
        dto.setName(exercise.getName());
        dto.setDescription(exercise.getDescription());
        dto.setVideoUrl(exercise.getVideoUrl());
        dto.setImg(exercise.getImg());
        dto.setNumberOfPointsToPassed(exercise.getNumberOfPointsToPassed());
        dto.setMaxPoints(exercise.getMaxPoints());
        dto.setNumberOfAttempts(exercise.getNumberOfAttempts());
        dto.setNumberOfStrokesInOneAttempt(exercise.getNumberOfStrokesInOneAttempt());
        dto.setLevel(exercise.getLevel());
        dto.setWhite(exercise.isWhite());
        dto.setRed(exercise.isRed());
        dto.setYellow(exercise.isYellow());
        dto.setGreen(exercise.isGreen());
        dto.setBrown(exercise.isBrown());
        dto.setBlue(exercise.isBlue());
        dto.setPink(exercise.isPink());
        dto.setBlack(exercise.isBlack());
        dto.setButtonPass(exercise.isButtonPass());
        dto.setBonusPoint(exercise.isBonusPoint());
        dto.setBonusInfo(exercise.getBonusInfo());
        dto.setBonusNumberOfPoints(exercise.getBonusNumberOfPoints());
        return dto;
    }
}
