package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.person.dentist.Dentist;

/**
 * An UI component that displays information of a {@code Dentist}.
 */
public class DentistCard extends UiPart<Region> {

    private static final String FXML = "DentistListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Dentist dentist;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label phone;
    @FXML
    private Label address;
    @FXML
    private Label email;
    @FXML
    private Label specialization;
    @FXML
    private Label yoe;
    @FXML
    private FlowPane dentistId;
    @FXML
    private FlowPane tags;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public DentistCard(Dentist dentist, int displayedIndex) {
        super(FXML);
        this.dentist = dentist;
        name.setText(dentist.getName().fullName);
        Label idLabel = new Label("ID: " + String.valueOf(dentist.getId()));
        dentistId.getChildren().add(idLabel);
        phone.setText("Phone: " + dentist.getPhone().value);
        address.setText("Address: " + dentist.getAddress().value);
        email.setText("Email: " + dentist.getEmail().value);
        specialization.setText("Specialization: " + dentist.getSpecialization().getValue());
        String experience = dentist.getYoe().getValue();
        yoe.setText("Experience: " + experience
                + ((experience.equals("0") || experience.equals("1"))
                ? " Year"
                : " Years"));

        dentist.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
    }
}
