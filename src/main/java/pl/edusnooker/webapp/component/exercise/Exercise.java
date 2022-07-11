package pl.edusnooker.webapp.component.exercise;

import lombok.Data;
import pl.edusnooker.webapp.enumeration.Level;

import javax.persistence.*;

@Entity
@Data
public class Exercise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String exerciseId;
    private String name;
    private String description;
    private String videoUrl;
    private String img;
    private int numberOfPointsToPassed;
    private int maxPoints;
    private int numberOfStrokesInOneAttempt;
    private int numberOfAttempts;
    private Level level;
    private boolean isWhite;
    private boolean isRed;
    private boolean isYellow;
    private boolean isGreen;
    private boolean isBrown;
    private boolean isBlue;
    private boolean isPink;
    private boolean isBlack;
    private boolean isButtonPass;
    private boolean isBonusPoint;
    private String bonusInfo;
    private int bonusNumberOfPoints;

}
