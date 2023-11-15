package seedu.cc.ui;

import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.cc.model.appointment.AppointmentEvent;
import seedu.cc.model.appointment.Prescription;

/**
 * An UI component that displays information of a {@code Appointment Event}.
 */
public class AppointmentEventCard extends UiPart<Region> {
    private static final String FXML = "AppointmentEventCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final AppointmentEvent appointmentEvent;

    @javafx.fxml.FXML
    private HBox cardPane;

    @FXML
    private Label id;
    @FXML
    private Label appointmentDate;
    @FXML
    private Label time;
    @FXML
    private Label prescription;

    /**
     * Creates a {@code PatientCode} with the given {@code Patient} and index to display.
     */
    public AppointmentEventCard(AppointmentEvent appointmentEvent, int displayedIndex) {
        super(FXML);
        this.appointmentEvent = appointmentEvent;
        id.setText(displayedIndex + ". ");

        appointmentDate.setText(appointmentEvent.getDate().toString());
        time.setText(appointmentEvent.getTime().toString());
        if (appointmentEvent.getPrescriptions().isEmpty()) {
            prescription.setText("No prescriptions");
        } else {
            ArrayList<String> prescriptions = new ArrayList<>();
            for (Prescription pres : appointmentEvent.getPrescriptions()) {
                String result = pres.toString().replace("[", "").replace("]", "");
                prescriptions.add(result);
            }
            prescription.setText(String.join(", ", prescriptions));
        }
    }
}
