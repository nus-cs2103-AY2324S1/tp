package seedu.address.storage;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.AddressBook;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.AppointmentDescription;
import seedu.address.model.appointment.AppointmentTime;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;

/**
 * Jackson-friendly version of {@link Appointment}.
 */
class JsonAdaptedAppointment {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Patient's %s field is missing!";

    private final String patientName;
    private final String appointmentTime;
    private LocalDateTime start;
    private LocalDateTime end;
    private final String description;

    /**
     * Constructs a {@code JsonAdaptedAppointment} with the given appointment details.
     */
    @JsonCreator
    public JsonAdaptedAppointment(@JsonProperty("patientName") String patientName,
                                  @JsonProperty("appointmentTime") String appointmentTime,
                                  @JsonProperty("description") String description) {
        this.patientName = patientName;
        this.appointmentTime = appointmentTime;
        this.description = description;
    }

    /**
     * Converts a given {@code Appointment} into this class for Jackson use.
     */
    public JsonAdaptedAppointment(Appointment source) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
        patientName = String.valueOf(source.getPatientName());
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
        if (patientName == null) {
            throw new IllegalValueException("Patient name");
        }
        if (!addressBook.hasPerson(new Name(patientName))) {
            throw new IllegalValueException("Patient does not exist");
        }
        if (!Name.isValidName(patientName)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Person patient = addressBook
                .getPersonList().stream().filter(person -> person.getName().fullName.equals(patientName))
                .collect(Collectors.toList()).get(0);

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

        return new Appointment(
                patient, modelAppointmentTime, modelAppointmentDescription);
    }

}
