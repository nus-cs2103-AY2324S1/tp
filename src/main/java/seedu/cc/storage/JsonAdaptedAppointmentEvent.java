package seedu.cc.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.cc.commons.exceptions.IllegalValueException;
import seedu.cc.model.appointment.AppointmentEvent;
import seedu.cc.model.appointment.Prescription;
import seedu.cc.model.util.Date;
import seedu.cc.model.util.Time;

/**
 * Jackson-friendly version of {@link AppointmentEvent}.
 */
public class JsonAdaptedAppointmentEvent {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Appointment event's %s field is missing!";


    private final List<JsonAdaptedPrescription> prescriptions = new ArrayList<>();
    private final String date;
    private final String time;

    /**
     * Constructs a {@code JsonAdaptedAppointmentEvent} with the given appointment event details.
     */
    @JsonCreator
    public JsonAdaptedAppointmentEvent(@JsonProperty("date") String date,
                                       @JsonProperty("localTime") String time,
                                       @JsonProperty("prescriptions") List<JsonAdaptedPrescription> prescriptions) {
        this.date = date;
        this.time = time;
        if (prescriptions != null) {
            this.prescriptions.addAll(prescriptions);
        }

    }

    /**
     * Converts a given {@code AppointmentEvent} into this class for Jackson use.
     */
    public JsonAdaptedAppointmentEvent(AppointmentEvent source) {
        date = source.getDate().toString();
        time = source.getTime().toString();
        this.prescriptions.addAll(source.getPrescriptions().stream()
                .map(JsonAdaptedPrescription::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted appointment event object into the model's {@code AppointmentEvent}
     * object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted appointment event.
     */
    public AppointmentEvent toModelType() throws IllegalValueException {

        if (date == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "Local Date"));
        }

        if (time == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "Local Time"));
        }

        Date modelDate = new Date(this.date);
        Time modelTime = new Time(this.time);

        if (prescriptions.isEmpty()) {
            return new AppointmentEvent(modelDate, modelTime);
        }

        final List<Prescription> patientPrescriptions = new ArrayList<>();
        for (JsonAdaptedPrescription prescription : prescriptions) {
            patientPrescriptions.add(prescription.toModelType());
        }
        final Set<Prescription> prescriptionSet = new HashSet<>(patientPrescriptions);
        return new AppointmentEvent(modelDate, modelTime, prescriptionSet);

    }
}
