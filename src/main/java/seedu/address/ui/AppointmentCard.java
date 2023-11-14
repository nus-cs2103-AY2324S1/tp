package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.tag.PriorityTag;


/**
 * An UI component that displays information of a {@code Person}.
 */
public class AppointmentCard extends UiPart<Region> {

    private static final String FXML = "AppointmentListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Appointment appointment;


    @FXML
    private HBox cardPane;
    @FXML
    private Label patientName;
    @FXML
    private Label id;
    @FXML
    private Label appointmentTime;
    @FXML
    private Label appointmentDescription;
    @FXML
    private Label priorityTag;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public AppointmentCard(Appointment appointment, int displayedIndex) {
        super(FXML);
        this.appointment = appointment;
        id.setText(displayedIndex + ". ");
        patientName.setText("PATIENT: " + appointment.getPatientName());
        appointmentTime.setText(appointment.getAppointmentTime().toString());
        appointmentDescription.setText(appointment.getAppointmentDescription().value);

        PriorityTag appointmentPriorityTag = appointment.getPriorityTag();
        priorityTag.setText(appointmentPriorityTag.priority);
        priorityTag.getStyleClass().add(appointmentPriorityTag.priority);
    }
}
