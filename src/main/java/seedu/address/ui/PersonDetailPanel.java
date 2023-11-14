package seedu.address.ui;

import java.util.Optional;

import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.address.model.person.Person;

/**
 * An UI component that displays information of a {@code Person}.
 */
public class PersonDetailPanel extends UiPart<Region> {

    private static final String FXML = "PersonDetailPanel.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    @FXML
    private VBox contentPane;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to
     * display.
     */
    public PersonDetailPanel(ObservableValue<Optional<Person>> observablePerson) {
        super(FXML);

        observablePerson.addListener((observable, oldValue, newValue) -> {
            if (newValue.isPresent()) {
                PersonDetailContent content = new PersonDetailContent(newValue.get());
                contentPane.getChildren().setAll(content.getRoot());
            } else {
                PersonDetailEmptyState emptyState = new PersonDetailEmptyState();
                contentPane.getChildren().setAll(emptyState.getRoot());
            }
        });
    }
}
