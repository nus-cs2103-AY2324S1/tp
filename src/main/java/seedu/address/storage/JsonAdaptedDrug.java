package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.prescription.Name;

/**
 * Jackson-friendly version of {@link Name}.
 */
class JsonAdaptedDrug {

    private final String drugName;

    /**
     * Constructs a {@code JsonAdaptedDrug} with the given {@code drugName}.
     */
    @JsonCreator
    public JsonAdaptedDrug(String drugName) {
        this.drugName = drugName;
    }

    /**
     * Converts a given {@code Name} into this class for Jackson use.
     */
    public JsonAdaptedDrug(Name source) {
        drugName = source.toString();
    }

    @JsonValue
    public String getDrugName() {
        return drugName;
    }

    /**
     * Converts this Jackson-friendly adapted drug object into the model's {@code Drug} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted drug.
     */
    public Name toModelType() throws IllegalValueException {
        if (!Name.isValidName(drugName)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        return new Name(drugName);
    }

}
