package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.record.Medication;

/**
 * Jackson-friendly version of {@link Medication}
 */
public class JsonAdaptedMedication {
    private final String medication;

    /**
     * Constructs a {@code JsonAdaptedMedication} with the given {@code medication}.
     */
    @JsonCreator
    public JsonAdaptedMedication(String medication) {
        this.medication = medication;
    }
    /**
     * Converts a given {@code Medication} into this class for Jackson use.
     */
    public JsonAdaptedMedication(Medication source) {
        this.medication = source.medication;
    }
    @JsonValue
    public String getMedication() {
        return medication;
    }

    /**
     * Converts this Jackson-friendly adapted medication object into the model's {@code Medication} object.
     * @throws IllegalValueException if there were any data constraints violated in the adapted condition.
     */
    public Medication toModelType() throws IllegalValueException {
        if (medication == null) {
            throw new IllegalValueException(Medication.MESSAGE_CONSTRAINTS);
        }
        if (!Medication.isValidMedication(medication)) {
            throw new IllegalValueException(Medication.MESSAGE_CONSTRAINTS);
        }
        return new Medication(medication);
    }
}
