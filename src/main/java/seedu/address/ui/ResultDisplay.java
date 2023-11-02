package seedu.address.ui;

import static java.util.Objects.requireNonNull;

import javafx.fxml.FXML;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
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
        setTextWithMarkdown(resultDisplay, feedbackToUser);
    }
    private void setTextWithMarkdown(TextFlow textFlowControl, String content) {
        textFlowControl.getChildren().clear(); // clear existing children

        // Initialize flags for formatting
        boolean isBold = false;
        boolean isItalic = false;
        boolean isUnderlined = false;

        StringBuilder currentSegment = new StringBuilder();

        for (int i = 0; i < content.length(); i++) {
            char c = content.charAt(i);

            // Check for underline tags
            if (i + 2 < content.length() && content.substring(i, i + 3).equals("<u>")) {
                if (currentSegment.length() > 0) {
                    addTextSegment(textFlowControl, currentSegment.toString(), isBold, isItalic, isUnderlined);
                    currentSegment.setLength(0);
                }
                isUnderlined = true;
                i += 2; // Skip over the <u> tag
            } else if (i + 3 < content.length() && content.substring(i, i + 4).equals("</u>")) {
                if (currentSegment.length() > 0) {
                    addTextSegment(textFlowControl, currentSegment.toString(), isBold, isItalic, isUnderlined);
                    currentSegment.setLength(0);
                }
                isUnderlined = false;
                i += 3; // Skip over the </u> tag
            } else if (c == '*') {
                if (i < content.length() - 1 && content.charAt(i + 1) == '*') {
                    // Double asterisks indicate bold
                    if (currentSegment.length() > 0) {
                        addTextSegment(textFlowControl, currentSegment.toString(), isBold, isItalic, isUnderlined);
                        currentSegment.setLength(0); // Clear the current segment
                    }
                    isBold = !isBold;
                    i++; // Skip the next asterisk
                } else {
                    // Single asterisk indicates italic
                    if (currentSegment.length() > 0) {
                        addTextSegment(textFlowControl, currentSegment.toString(), isBold, isItalic, isUnderlined);
                        currentSegment.setLength(0); // Clear the current segment
                    }
                    isItalic = !isItalic;
                }
            } else {
                currentSegment.append(c);
            }
        }

        // Handle any remaining text
        if (currentSegment.length() > 0) {
            addTextSegment(textFlowControl, currentSegment.toString(), isBold, isItalic, isUnderlined);
        }
    }

    private void addTextSegment(TextFlow textFlowControl, String content,
                                boolean isBold, boolean isItalic, boolean isUnderlined) {
        Text textSegment = new Text(content);

        if (isBold && isItalic) {
            textSegment.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.ITALIC, 15));
        } else if (isBold) {
            textSegment.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        } else if (isItalic) {
            textSegment.setFont(Font.font("Arial", FontPosture.ITALIC, 15));
        } else {
            textSegment.setFont(Font.font("Arial", 15));
        }

        if (isUnderlined) {
            textSegment.setUnderline(true);
        }

        textSegment.setFill(Color.BLACK); // Set text color to black

        textFlowControl.getChildren().add(textSegment);
    }
}
