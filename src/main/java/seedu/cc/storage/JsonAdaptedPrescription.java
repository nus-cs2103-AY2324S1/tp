package seedu.cc.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.cc.commons.exceptions.IllegalValueException;
import seedu.cc.model.appointment.Prescription;

/**
 * Jackson-friendly version of {@link Prescription}.
 */
public class JsonAdaptedPrescription {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Appointment event's %s field is missing!";


    private final String prescription;

    /**
     * Constructs a {@code JsonAdaptedPrescription} with the given medical history e.
     */
    @JsonCreator
    public JsonAdaptedPrescription(@JsonProperty("prescription") String prescription) {
        this.prescription = prescription;
    }

    /**
     * Converts a given {@code Prescription} into this class for Jackson use.
     */
    public JsonAdaptedPrescription(Prescription source) {
        prescription = source.getPrescription();
    }

    /**
     * Converts this Jackson-friendly adapted appointment event object into the model's {@code AppointmentEvent}
     * object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted medical history event.
     */
    public Prescription toModelType() throws IllegalValueException {
        if (!Prescription.isValidPrescription(prescription)) {
            throw new IllegalValueException(Prescription.MESSAGE_CONSTRAINTS);
        }

        return new Prescription(prescription);
    }
}
