package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import seedu.address.model.card.Card;


/**
 * DisplayCard class to showcase the question and the answer
 */
public class DisplayCard extends UiPart<Region> {
    private static final String FXML = "DisplayListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */
    public final Card card;

    @javafx.fxml.FXML
    private HBox cardPane;
    @FXML
    private Label id;

    @FXML
    private TextFlow question;

    @FXML
    private FlowPane tags;

    @FXML
    private Label dueDate;

    @FXML
    private Label solveCount;

    /**
     * Creates a {@code DisplayCard} with the given {@code card} and index to display.
     */

    public DisplayCard(Card card, int displayedIndex) {
        super(FXML);
        this.card = card;
        id.setText(displayedIndex + ". ");
        setTextWithMarkdown(question, card.getQuestion().question);

        dueDate.setText("Due: " + card.getNextPracticeDate().getDisplayName());
        card.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
        solveCount.setText("Solved: " + card.getSolveCount().toString());
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
            addTextSegment(textFlowControl, currentSegment.toString(),
                    isBold, isItalic, isUnderlined);
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

        textSegment.setStyle("-fx-fill: white;");

        textFlowControl.getChildren().add(textSegment);
    }
}
