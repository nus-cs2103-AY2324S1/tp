package seedu.address.storage;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.person.Ic;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Tag}.
 */
class JsonAdaptedAppointment {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Appointment's %s field is missing!";
    public static final String INVALID_FIELD_MESSAGE_FORMAT = "Appointment's %s field is invalid!";
    private final String doctorIc;
    private final String patientIc;
    private final String appointmentTime;
    private final String status;

    /**
     * Constructs a {@code JsonAdaptedTag} with the given {@code tagName}.
     */
    @JsonCreator
    public JsonAdaptedAppointment(@JsonProperty("doctorIc") String doctorIc,
                                  @JsonProperty("patientIc") String patientIc,
                                  @JsonProperty("appointmentTime") String appointmentTime,
                                  @JsonProperty("status") String status) {
        this.doctorIc = doctorIc;
        this.patientIc = patientIc;
        this.appointmentTime = appointmentTime;
        this.status = status;
    }

    /**
     * Converts a given {@code Tag} into this class for Jackson use.
     */
    public JsonAdaptedAppointment(Appointment source) {
        doctorIc = source.getDoctor().value;
        patientIc = source.getPatient().value;
        appointmentTime = source.getAppointmentTime().toString();
        status = source.getStatus();
    }

    public String checkStatus() throws IllegalValueException {
        if (status == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "Status"));
        }
        return status;
    }

    public LocalDateTime checkAppointmentTime() throws IllegalValueException {
        if (appointmentTime == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, LocalDateTime.class.getSimpleName()));
        }
        LocalDateTime result;
        try {
            result = LocalDateTime.parse(appointmentTime);
        } catch (DateTimeParseException e) {
            throw new IllegalValueException(String.format(INVALID_FIELD_MESSAGE_FORMAT,
                    LocalDateTime.class.getSimpleName()));
        }
        return result;
    }

    /**
     * Checks the ic given by storage.
     *
     * @return a valid ic
     * @throws IllegalValueException if ic is not valid.
     */
    private Ic checkIc(String ic) throws IllegalValueException {
        if (ic == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Ic.class.getSimpleName()));
        }
        if (!Ic.isValidIc(ic)) {
            throw new IllegalValueException(Ic.MESSAGE_CONSTRAINTS);
        }
        return new Ic(ic);
    }

    /**
     * Converts this Jackson-friendly adapted tag object into the model's {@code Tag} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted tag.
     */
    public Appointment toModelType() throws IllegalValueException {
        final Ic modelDoctor = checkIc(doctorIc);
        final Ic modelPatient = checkIc(patientIc);
        final LocalDateTime modelAppointmentTime = checkAppointmentTime();
        final String modelStatus = checkStatus();
        return new Appointment(modelDoctor, modelPatient, modelAppointmentTime, modelStatus);
    }
}

