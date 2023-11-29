package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.appointments.Appointment;

/**
 * Represents a graphical user interface component to display details of an {@link Appointment}.
 */
public class AppointmentCard extends UiPart<Region> {
    private static final String FXML = "AppointmentListCard.fxml";

    public final Appointment appointment;

    @FXML
    private HBox cardPane;
    @FXML
    private Label dentist;
    @FXML
    private Label patient;
    @FXML
    private Label appointmentTime;
    @FXML
    private Label date;
    @FXML
    private Label treatment;
    @FXML
    private Label cost;
    @FXML
    private FlowPane appointmentId;

    /**
     * Constructs an {@code AppointmentCard} with the given {@code Appointment}.
     *
     * @param appointment The appointment to display details of.
     */
    public AppointmentCard(Appointment appointment) {
        super(FXML);
        this.appointment = appointment;
        Label idLabel = new Label("ID: " + String.valueOf(appointment.getId()));
        appointmentId.getChildren().add(idLabel);
        dentist.setText("Dentist: " + appointment.getDentistName());
        patient.setText("Patient: " + appointment.getPatientName());
        appointmentTime.setText(appointment.getAppointmentTime().appointmentTimeToString());
        date.setText(appointment.getAppointmentTime().dateToString());
        treatment.setText("Treatment: " + appointment.getTreatment());
        cost.setText("Cost: " + appointment.getCost());
    }
}
