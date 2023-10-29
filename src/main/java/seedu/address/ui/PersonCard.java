package seedu.address.ui;

import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.person.Person;
import seedu.address.model.person.attendance.AttendanceType;

/**
 * An UI component that displays information of a {@code Person}.
 */
public class PersonCard extends UiPart<Region> {

    private static final String FXML = "PersonListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved
     * keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The
     *      issue on AddressBook level 4</a>
     */

    public final Person person;

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
    private Label bankAccount;
    @FXML
    private Label joinDate;
    @FXML
    private Label salary;
    @FXML
    private Label annualLeave;
    @FXML
    private Label attendance;
    @FXML
    private Label workingStatus;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to
     * display.
     */
    public PersonCard(Person person, int displayedIndex) {
        super(FXML);
        this.person = person;
        id.setText(displayedIndex + ". ");
        name.setText(person.getName().fullName);
        phone.setText(person.getPhone().value);
        address.setText(person.getAddress().value);
        email.setText(person.getEmail().value);
        bankAccount.setText(person.getBankAccount().value);
        joinDate.setText(person.getJoinDate().value);
        salary.setText(person.getSalary().value);
        annualLeave.setText(person.getAnnualLeave().value);
        attendance.setText(person.getAttendanceToday().toString().toLowerCase());
        setWorkingStatus(person.getWorkingStatusToday());
    }

    /**
     * Creates a {@code PersonCode} with the given {@code Person}, index to
     * display and list of indexes.
     */
    public PersonCard(Person person, int displayedIndex, List<Integer> indexes) {
        super(FXML);
        this.person = person;
        id.setText(indexes.get(displayedIndex - 1) + ". ");
        name.setText(person.getName().fullName);
        phone.setText(person.getPhone().value);
        address.setText(person.getAddress().value);
        email.setText(person.getEmail().value);
        bankAccount.setText(person.getBankAccount().value);
        joinDate.setText(person.getJoinDate().value);
        salary.setText(person.getSalary().value);
        annualLeave.setText(person.getAnnualLeave().value);
        attendance.setText(person.getAttendanceToday().toString().toLowerCase());
        setWorkingStatus(person.getWorkingStatusToday());
    }

    /**
     * Set the style of the workingStatus label based on test
     * @param status of the employee on whether they are On Leave or Working
     */
    public void setWorkingStatus(AttendanceType status) {
        System.out.println(status);
        switch (status) {
        case PRESENT:
            workingStatus.setStyle("-fx-background-color: green; -fx-padding: 2;"
                    + "-fx-font-family: 'Arial Black'; -fx-font-size:13; -fx-background-radius: 3");
            workingStatus.setText("Working");
            break;
        case LATE:
            workingStatus.setStyle("-fx-background-color: #eb5252; -fx-padding: 2;"
                    + "-fx-font-family: 'Arial Black'; -fx-font-size:13; -fx-background-radius: 3");
            workingStatus.setText("Late");
            break;
        case ABSENT:
            workingStatus.setStyle("-fx-background-color: #c23a3f; -fx-padding: 2;"
                    + "-fx-font-family: 'Arial Black'; -fx-font-size:13; -fx-background-radius: 3");
            workingStatus.setText("Absent");
            break;
        case ON_LEAVE:
            workingStatus.setStyle("-fx-background-color: #A50000; -fx-padding: 2;"
                    + "-fx-font-family: 'Arial Black'; -fx-font-size:13; -fx-background-radius: 3");
            workingStatus.setText("On Leave");
            break;
        }
    }
}
