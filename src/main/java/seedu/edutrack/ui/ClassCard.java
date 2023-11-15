package seedu.edutrack.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.edutrack.model.module.Class;

/**
 * A UI component that displays information of a {@code Class}.
 */
public class ClassCard extends UiPart<Region> {

    private static final String FXML = "ClassListCard.fxml";
    /**
     * Memo: Certain keywords such as "location" and "resources" are reserved
     * keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The
     *      issue on AddressBook level 4</a>
     */

    public final Class c;

    @javafx.fxml.FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to
     * display.
     */
    public ClassCard(Class c, int displayedIndex) {
        super(FXML);
        this.c = c;
        id.setText(displayedIndex + ". ");
        name.setText(c.getClassName().toString());
    }
}
