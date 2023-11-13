package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.address.model.person.Person;

/**
 * An UI component that displays information of a {@code Person}.
 */
public class PersonCard extends UiPart<Region> {

    private static final String FXML = "PersonListCard.fxml";
    private static final String TEXT_STYLE = "-fx-font-family:Segoe UI; -fx-font-size: 13px;";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Person person;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private FlowPane groups;
    @FXML
    private VBox mainBox;

    /**
     * Creates a {@code PersonCard} with the given {@code Person} and index to display.
     */
    public PersonCard(Person person, int displayedIndex) {
        super(FXML);
        this.person = person;
        id.setText(displayedIndex + ". ");
        name.setText(person.getName().fullName);
        fillInfo();
        person.getGroups().stream()
                .sorted(Comparator.comparing(group -> group.groupName))
                .forEach(group -> groups.getChildren().add(new Label(group.groupName)));
    }

    /**
     * Adds a {@code Person}'s info into {@code PersonCard}.
     */
    public void fillInfo() {
        Label info;

        if (!person.getPhone().toString().isEmpty()) {
            info = new Label(person.getPhone().toString());
            info.setStyle(TEXT_STYLE);
            mainBox.getChildren().add(info);
        }
        if (!person.getAddress().toString().isEmpty()) {
            info = new Label(person.getAddress().toString());
            info.setStyle(TEXT_STYLE);
            mainBox.getChildren().add(info);
        }
        if (!person.getEmail().toString().isEmpty()) {
            info = new Label(person.getEmail().toString());
            info.setStyle(TEXT_STYLE);
            mainBox.getChildren().add(info);
        }
        if (!person.getBirthday().toString().isEmpty()) {
            info = new Label(person.getBirthday().toString());
            info.setStyle(TEXT_STYLE);
            mainBox.getChildren().add(info);
        }
        if (!person.getRemark().toString().isEmpty()) {
            info = new Label(person.getRemark().toString());
            info.setStyle(TEXT_STYLE);
            mainBox.getChildren().add(info);
        }
    }
}
