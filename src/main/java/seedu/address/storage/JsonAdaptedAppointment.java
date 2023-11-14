package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.AppointmentTime;
import seedu.address.model.person.Ic;

/**
 * Json-friendly version of {@link Appointment}.
 */
class JsonAdaptedAppointment {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Appointment's %s field is missing!";
    public static final String DUPLICATE_PATIENT_AND_DOCTOR_IC =
            "Appointment's doctor IC and patients IC are the same!";
    private final String doctorIc;
    private final String patientIc;
    private final String appointmentTime;

    /**
     * Constructs a {@code JsonAdaptedAppointment} with the given appointment details.
     */
    @JsonCreator
    public JsonAdaptedAppointment(@JsonProperty("doctorIc") String doctorIc,
                                  @JsonProperty("patientIc") String patientIc,
                                  @JsonProperty("appointmentTime") String appointmentTime) {
        this.doctorIc = doctorIc;
        this.patientIc = patientIc;
        this.appointmentTime = appointmentTime;
    }

    /**
     * Converts a given {@code Appointment} into this class for Json use.
     */
    public JsonAdaptedAppointment(Appointment source) {
        doctorIc = source.getDoctor().value;
        patientIc = source.getPatient().value;
        appointmentTime = source.getAppointmentTime().toString();
    }

    /**
     * Checks and validates the appointment time field for correctness.
     *
     * @return A valid {@code AppointmentTime} object representing the appointment time.
     * @throws IllegalValueException If the appointment time is missing or does not meet the constraints.
     */
    public AppointmentTime checkAppointmentTime() throws IllegalValueException {
        if (appointmentTime == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, AppointmentTime.class.getSimpleName()));
        }
        if (!AppointmentTime.isValidAppointmentTime(appointmentTime)) {
            throw new IllegalValueException(AppointmentTime.MESSAGE_CONSTRAINTS);
        }
        return new AppointmentTime(appointmentTime);
    }

    /**
     * Checks the {@code ic} given by storage.
     *
     * @return a valid Ic
     * @throws IllegalValueException if Ic is not valid.
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
     * Converts this Json-friendly adapted Appointment object into the model's {@code Appointment} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted appointment.
     */
    public Appointment toModelType() throws IllegalValueException {
        //  doctorIc and patientIc should not be the same.
        if (doctorIc == patientIc) {
            throw new IllegalValueException(DUPLICATE_PATIENT_AND_DOCTOR_IC);
        }
        final Ic modelDoctor = checkIc(doctorIc);
        final Ic modelPatient = checkIc(patientIc);
        final AppointmentTime modelAppointmentTime = checkAppointmentTime();
        return new Appointment(modelDoctor, modelPatient, modelAppointmentTime);
    }
}

