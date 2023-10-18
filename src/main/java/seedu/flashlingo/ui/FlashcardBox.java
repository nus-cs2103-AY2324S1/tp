package seedu.flashlingo.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.flashlingo.model.flashcard.FlashCard;

/**
 * An UI component that displays information of a {@code Person}.
 */
public class FlashcardBox extends UiPart<Region> {

    private static final String FXML = "FlashcardBox.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final FlashCard flashCard;

    @FXML
    private HBox cardPane;
    @FXML
    private Label id;
    @FXML
    private Label original;
    @FXML
    private Label translation;
    @FXML
    private Label level;

    @FXML
    private Button reveal;

    private boolean isRevealed = false;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public FlashcardBox(FlashCard fc, int displayedIndex) {
        super(FXML);
        this.flashCard = fc;
        id.setText(displayedIndex + ") ");
        original.setText(fc.getOriginalWord().toString() + ": ");
        translation.setText("");
        level.setText("Current Level: " + fc.getProficiencyLevel().getLevel());
    }

    @FXML
    public void success() {
        flashCard.handleUserInput(true);
        level.setText("Current Level: " + flashCard.getProficiencyLevel().getLevel());
    }

    @FXML
    public void failure() {
        flashCard.handleUserInput(false);
        level.setText("Current Level: " + flashCard.getProficiencyLevel().getLevel());
    }

    @FXML
    public void toggleReveal() {
        if (isRevealed) {
            translation.setText("");
            reveal.setText("Reveal");
        } else {
            translation.setText(flashCard.getTranslatedWord().toString());
            reveal.setText("Hide");
        }
        isRevealed = !isRevealed;
    }

    @FXML
    public void undo() {
        flashCard.undo();
        level.setText("Current Level: " + flashCard.getProficiencyLevel().getLevel());
    }

}
