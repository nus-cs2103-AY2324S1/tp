

package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.MedicalHistory;

/**
 * Jackson-friendly version of {@link MedicalHistory}.
 */
class JsonAdaptedMedicalHistory {

    private final String medicalHistory;

    /**
     * Constructs a {@code JsonAdaptedMedicalHistory} with the given {@code medicalHistory} value.
     */
    @JsonCreator
    public JsonAdaptedMedicalHistory(String medicalHistory) {
        this.medicalHistory = medicalHistory;
    }

    /**
     * Converts a given {@code MedicalHistory} into this class for Jackson use.
     */
    public JsonAdaptedMedicalHistory(MedicalHistory source) {
        medicalHistory = source.value;
    }

    @JsonValue
    public String getMedicalHistoryName() {
        return medicalHistory;
    }

    /**
     * Converts this Jackson-friendly adapted MedicalHistory object into the model's {@code MedicalHistory} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted MedicalHistory.
     */
    public MedicalHistory toModelType() throws IllegalValueException {
        if (!MedicalHistory.isValidMedicalHistory(medicalHistory)) {
            throw new IllegalValueException(MedicalHistory.MESSAGE_CONSTRAINTS);
        }
        return new MedicalHistory(medicalHistory);
    }

}
