package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.person.Student;

/**
 * An UI component that displays information of a {@code Student}.
 */
public class PersonCard extends UiPart<Region> {

    private static final String FXML = "PersonListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Student student;

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
    private Label gender;
    @FXML
    private Label secLevel;
    @FXML
    private Label nearestMrtStation;
    @FXML
    private FlowPane subjects;

    /**
     * Creates a {@code PersonCode} with the given {@code Student} and index to display.
     */
    public PersonCard(Student student, int displayedIndex) {
        super(FXML);
        this.student = student;
        id.setText(displayedIndex + ". ");
        name.setText(student.getName().fullName);
        phone.setText("Phone No: " + student.getPhone().value);
        address.setText("Address: " + student.getAddress().value);
        email.setText("Email: " + student.getEmail().value);
        gender.setText("Gender: " + student.getGender().value);
        secLevel.setText("Sec-Level: " + student.getSecLevel().value);
        nearestMrtStation.setText("Nearest MRT Station: " + student.getNearestMrtStation().mrtStationName);
        student.getSubjects().stream()
                .sorted(Comparator.comparing(subject -> subject.getSubjectName()))
                .forEach(subject -> subjects.getChildren().add(new Label(subject.getSubjectName()
                        + " (enrolled in: " + subject.getEnrolDate() + ")")));

    }
}
