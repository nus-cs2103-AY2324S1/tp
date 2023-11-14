package seedu.address.seplendidui;

import static java.util.Objects.requireNonNull;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Region;

/**
 * The UI component that is responsible for returning the feedback of the application to the user.
 */
public class ResultBox extends UiPart<Region> {

    private static final String FXML = "ResultBox.fxml";

    @FXML
    private TextArea resultTextArea;

    public ResultBox() {
        super(FXML);
    }

    public void setFeedbackToUser(String feedbackToUser) {
        requireNonNull(feedbackToUser);
        resultTextArea.setText(feedbackToUser);
    }
}
