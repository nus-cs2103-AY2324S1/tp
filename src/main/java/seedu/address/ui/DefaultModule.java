package seedu.address.ui;

import static java.util.Objects.requireNonNull;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Region;
import seedu.address.model.course.Course;

/**
 * A ui for the default course that is displayed at the header of the application.
 */
public class DefaultModule extends UiPart<Region> {
    private static final String FXML = "DefaultModule.fxml";

    @FXML
    private TextArea defaultModule;

    public DefaultModule(Course defaultModule) {
        super(FXML);
        this.defaultModule.setText("Module:" + defaultModule);
    }

    public void setFeedbackToUser(String feedbackToUser) {
        requireNonNull(feedbackToUser);
        defaultModule.setText(feedbackToUser);
    }
}
