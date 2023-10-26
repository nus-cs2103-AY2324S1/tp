package seedu.address.ui;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.util.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ClockComponent extends Label {
    public ClockComponent() {
        getStyleClass().add("clock-label");
        initializeClock();
    }

    private void initializeClock() {
        Timeline clockTimeline = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
            setText(LocalDateTime.now().format(dtf));
        }), new KeyFrame(Duration.seconds(1)));
        clockTimeline.setCycleCount(Timeline.INDEFINITE);
        clockTimeline.play();
    }
}
