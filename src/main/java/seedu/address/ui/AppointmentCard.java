package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.util.DateTimeParser;

/**
 * An UI component that displays information of a {@code Appointment}.
 */
public class AppointmentCard extends UiPart<Region> {

    private static final String FXML = "AppointmentListCard.fxml";

    public final Appointment appointment;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label date;
    @FXML
    private Label startTime;
    @FXML
    private Label endTime;
    @FXML
    private Label description;

    /**
     * Creates a {@code StudentCode} with the given {@code Student} and index to display.
     */
    public AppointmentCard(Appointment appointment, int displayedIndex) {
        super(FXML);
        this.appointment = appointment;
        id.setText(displayedIndex + ". ");
        name.setText(appointment.getName().value);
        date.setText("Date: " + DateTimeParser.formatDate(appointment.getDate().value));
        startTime.setText("From: " + DateTimeParser.formatTime(appointment.getStartTime().value));
        endTime.setText("To: " + DateTimeParser.formatTime(appointment.getEndTime().value));
        description.setText("Description: " + appointment.getDescription().value);
    }
}
