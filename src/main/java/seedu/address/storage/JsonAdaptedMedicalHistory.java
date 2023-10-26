

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
     * Constructs a {@code JsonAdaptedTag} with the given {@code tagName}.
     */
    @JsonCreator
    public JsonAdaptedMedicalHistory(String medicalHistory) {
        this.medicalHistory = medicalHistory;
    }

    /**
     * Converts a given {@code Tag} into this class for Jackson use.
     */
    public JsonAdaptedMedicalHistory(MedicalHistory source) {
        medicalHistory = source.value;
    }

    @JsonValue
    public String getMedicalHistoryName() {
        return medicalHistory;
    }

    /**
     * Converts this Jackson-friendly adapted tag object into the model's {@code Tag} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted tag.
     */
    public MedicalHistory toModelType() throws IllegalValueException {
        if (!MedicalHistory.isValidMedicalHistory(medicalHistory)) {
            throw new IllegalValueException(MedicalHistory.MESSAGE_CONSTRAINTS);
        }
        return new MedicalHistory(medicalHistory);
    }

}
