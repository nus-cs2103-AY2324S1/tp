package seedu.address.ui;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;

/**
 * A ui for the status bar that is displayed at the footer of the application.
 */
public class StatusBarFooter extends UiPart<Region> {

    private static final String FXML = "StatusBarFooter.fxml";
    private static final String TIME_DISPLAY_FORMAT = "yyyy-MM-dd HH:mm:ss";

    @FXML
    private Label saveLocationStatus;
    @FXML
    private Label currentTime;

    /**
     * Creates a {@code StatusBarFooter} with the given {@code Path}.
     */
    public StatusBarFooter(Path saveLocation) {
        super(FXML);
        saveLocationStatus.setText(Paths.get(".").resolve(saveLocation).toString());

        //@@author dishenggg-reused
        //Reused from https://stackoverflow.com/a/42384436
        // with minor modifications
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                currentTime.setText(LocalDateTime.now().format(DateTimeFormatter.ofPattern(TIME_DISPLAY_FORMAT)));
            }
        };
        //@@author
        timer.start();
    }
}
