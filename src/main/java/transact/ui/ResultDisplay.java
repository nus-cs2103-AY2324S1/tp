package transact.ui;

import static java.util.Objects.requireNonNull;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Region;

/**
 * A ui for the status bar that is displayed at the header of the application.
 */
public class ResultDisplay extends UiPart<Region> {

    private static final String FXML = "ResultDisplay.fxml";

    @FXML
    private TextArea resultDisplay;


    public ResultDisplay() {
        super(FXML);
    }

    public void setFeedbackToUser(String feedbackToUser) {
        requireNonNull(feedbackToUser);
        String currentText = resultDisplay.getText();
        if (!currentText.isEmpty()) {
            currentText += "\n"; // Add a newline to separate messages
        }
        resultDisplay.setText(currentText + feedbackToUser);
        //resultDisplay.appendText(currentText + feedbackToUser);

        // 将滚动条滚动到最底部
        resultDisplay.positionCaret(resultDisplay.getLength());
    }

}
