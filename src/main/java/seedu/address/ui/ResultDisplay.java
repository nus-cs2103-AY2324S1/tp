package seedu.address.ui;

import static java.util.Objects.requireNonNull;

import javafx.fxml.FXML;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.text.TextFlow;

/**
 * A UI for the status bar that is displayed at the header of the application.
 */
public class ResultDisplay extends UiPart<Region> {
    private static final String FXML = "ResultDisplay.fxml";

    @FXML
    private TextFlow resultDisplay;

    /**
     * Constructs a ResultDisplay.
     */
    public ResultDisplay() {
        super(FXML);
    }

    public void setFeedbackToUser(String feedbackToUser) {
        requireNonNull(feedbackToUser);
        MarkdownHelper.setTextWithMarkdown(resultDisplay, feedbackToUser, Color.BLACK);
    }
}
