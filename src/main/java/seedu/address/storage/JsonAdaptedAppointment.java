package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.appointments.Appointment;
import seedu.address.model.appointments.AppointmentTime;

/**
 * Jackson-friendly version of {@link Appointment}.
 */
public class JsonAdaptedAppointment {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Appointment's %s field is missing!";

    private final String dentistId;
    private final String patientId;
    private final String dentistName;
    private final String patientName;
    private final String start;
    private final String duration;
    private final String treatment;
    private final String cost;
    private final String id;

    /**
     * Constructs a {@code JsonAdaptedAppointment} with the given appointment details.
     *
     * @param dentistId The dentist ID.
     * @param patientId The patient ID.
     * @param dentistName The name of the dentist.
     * @param patientName The name of the patient.
     * @param start The start time of the appointment in string format.
     * @param duration The duration of the appointment in string format.
     * @param treatment The treatment provided during the appointment.
     */
    @JsonCreator
    public JsonAdaptedAppointment(@JsonProperty("dentistId") String dentistId,
                                  @JsonProperty("patientId") String patientId,
                                  @JsonProperty("dentistName") String dentistName,
                                  @JsonProperty("patientName") String patientName,
                                  @JsonProperty("start") String start,
                                  @JsonProperty("duration") String duration,
                                  @JsonProperty("treatment") String treatment,
                                  @JsonProperty("cost") String cost,
                                  @JsonProperty("id") String id) {
        this.dentistId = dentistId;
        this.patientId = patientId;
        this.dentistName = dentistName;
        this.patientName = patientName;
        this.start = start;
        this.duration = duration;
        this.treatment = treatment;
        this.cost = cost;
        this.id = id;

    }

    /**
     * Converts a given {@code Appointment} into this class for Jackson use.
     *
     * @param source The source appointment to be adapted.
     */
    public JsonAdaptedAppointment(Appointment source) {
        dentistId = String.valueOf(source.getDentistId());
        patientId = String.valueOf(source.getPatientId());
        dentistName = source.getDentistName();
        patientName = source.getPatientName();
        start = source.getAppointmentTime().startToString();
        duration = source.getAppointmentTime().durationToString();
        treatment = source.getTreatment();
        cost = source.getCost();
        id = String.valueOf(source.getId());
    }

    /**
     * Converts this Jackson-friendly adapted appointment object into the model's {@code Appointment} object.
     *
     * @return The corresponding {@code Appointment} object.
     * @throws IllegalValueException If there were any data constraints violated in the adapted appointment.
     */
    public Appointment toModelType() throws IllegalValueException {
        if (dentistId == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, "dentist id"));
        }
        long did = Long.parseLong(dentistId);
        if (patientId == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, "patient id"));
        }
        long pid = Long.parseLong(patientId);
        if (dentistName == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, "dentist name"));
        }
        if (patientName == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, "patient name"));
        }
        if (start == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, "start time"));
        }
        if (duration == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, "duration"));
        }
        if (treatment == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, "treatment"));
        }
        if (cost == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, "cost"));
        }
        if (id == null) {
            throw new IllegalValueException("id value does not exist!");
        }
        long lid = Long.parseLong(id);

        final AppointmentTime appointmentTime = new AppointmentTime(start, duration);
        Appointment newAppointment = new Appointment(did, pid, start, treatment, lid);
        newAppointment.setDentistName(dentistName);
        newAppointment.setPatientName(patientName);
        newAppointment.setAppointmentTime(appointmentTime);
        newAppointment.setCost(cost);
        return newAppointment;
    }
}
