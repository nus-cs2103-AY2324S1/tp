package seedu.address.storage;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.AddressBook;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.AppointmentDescription;
import seedu.address.model.appointment.AppointmentTime;

/**
 * Jackson-friendly version of {@link Appointment}.
 */
class JsonAdaptedAppointment {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Patient's %s field is missing!";

    private final String patientId;
    private final String appointmentTime;
    private LocalDateTime start;
    private LocalDateTime end;
    private final String description;

    /**
     * Constructs a {@code JsonAdaptedAppointment} with the given appointment details.
     */
    @JsonCreator
    public JsonAdaptedAppointment(@JsonProperty("patientId") String patientId,
                                  @JsonProperty("appointmentTime") String appointmentTime,
                                  @JsonProperty("description") String description) {
        this.patientId = patientId;
        this.appointmentTime = appointmentTime;
        this.description = description;
    }

    /**
     * Converts a given {@code Appointment} into this class for Jackson use.
     */
    public JsonAdaptedAppointment(Appointment source) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        patientId = String.valueOf(source.getPatientId());
        appointmentTime = source.getAppointmentTime().toString();
        start = source.getStartTime();
        end = source.getEndTime();
        description = source.getAppointmentDescription().value;
    }

    /**
     * Converts this Jackson-friendly adapted appointment object into the model's {@code Appointment} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted appointment.
     */
    public Appointment toModelType(AddressBook addressBook) throws IllegalValueException {
        if (appointmentTime == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, AppointmentTime.class.getSimpleName()));
        }
        if (!AppointmentTime.isValidAppointmentTime(start, end)) {
            throw new IllegalValueException(AppointmentTime.MESSAGE_CONSTRAINTS);
        }
        final AppointmentTime modelAppointmentTime = new AppointmentTime(start, end);

        if (description == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, AppointmentDescription.class.getSimpleName()));
        }
        if (!AppointmentDescription.isValidAppointmentDescription(description)) {
            throw new IllegalValueException(AppointmentDescription.MESSAGE_CONSTRAINTS);
        }
        final AppointmentDescription modelAppointmentDescription = new AppointmentDescription(description);
        Appointment appointment = new Appointment(
                Integer.parseInt(patientId), modelAppointmentTime, modelAppointmentDescription);
        appointment.setPatient(addressBook);
        return appointment;
    }

}
