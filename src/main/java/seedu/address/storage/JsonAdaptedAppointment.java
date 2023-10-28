package seedu.address.storage;

import static seedu.address.storage.JsonAdaptedPatient.MISSING_FIELD_MESSAGE_FORMAT;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Appointment;
import seedu.address.model.person.Doctor;
import seedu.address.model.person.Name;
import seedu.address.model.person.Patient;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Tag}.
 */
class JsonAdaptedAppointment {

    private final Doctor doctor;
    private final Patient patient;
    private final LocalDateTime appointmentTime;
    private final String status;

    /**
     * Constructs a {@code JsonAdaptedTag} with the given {@code tagName}.
     */
    @JsonCreator
    public JsonAdaptedAppointment(@JsonProperty("doctor") Doctor doctor, @JsonProperty("patient") Patient patient,
                                  @JsonProperty("appointmentTime") LocalDateTime appointmentTime,
                                  @JsonProperty("status") String status) {
        this.doctor = doctor;
        this.patient = patient;
        this.appointmentTime = appointmentTime;
        this.status = status;
    }

    /**
     * Converts a given {@code Tag} into this class for Jackson use.
     */
    public JsonAdaptedAppointment(Appointment source) {
        doctor = source.getDoctor();
        patient = source.getPatient();
        appointmentTime = source.getAppointmentTime();
        status = source.getStatus();
    }

    public String checkStatus() throws IllegalValueException {
        if (status == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        return status;
    }

    public LocalDateTime checkAppointmentTime() throws IllegalValueException {
        if (appointmentTime == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        return appointmentTime;
    }

    /**
     * Converts this Jackson-friendly adapted tag object into the model's {@code Tag} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted tag.
     */
    public Appointment toModelType() throws IllegalValueException {
        final Doctor modelDoctor = new JsonAdaptedDoctor(doctor).toModelType();
        final Patient modelPatient = new JsonAdaptedPatient(patient).toModelType();
        final LocalDateTime modelAppointmentTime = checkAppointmentTime();
        final String modelStatus = checkStatus();
        return new Appointment(modelDoctor, modelPatient, modelAppointmentTime, modelStatus);
    }
}

