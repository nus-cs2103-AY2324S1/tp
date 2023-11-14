package seedu.address.ui;

import java.time.format.DateTimeFormatter;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.appointment.Appointment;

/**
 * An UI component that displays information of a {@code Appointment}.
 */
public class AppointmentCard extends UiPart<Region> {
    private static final String FXML = "AppointmentListCard.fxml";
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Appointment appt;

    @FXML
    private HBox cardPane;
    @FXML
    private Label id;
    @FXML
    private Label name;
    @FXML
    private Label personName;
    @FXML
    private Label dateTime;


    /**
     * Creates a {@code AppointmentCode} with the given {@code Appointment} and index to display.
     */
    public AppointmentCard(Appointment appt, int displayedIndex) {
        super(FXML);
        this.appt = appt;

        id.setText(displayedIndex + ". ");
        name.setText(appt.getName());
        personName.setText(appt.getPerson().getName().toString());
        dateTime.setText(appt.getDateTime().format(DATE_FORMATTER));
    }
}
