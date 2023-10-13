package seedu.staffsnap.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.staffsnap.commons.exceptions.IllegalValueException;
import seedu.staffsnap.model.interview.Interview;

/**
 * Jackson-friendly version of {@link Interview}.
 */
class JsonAdaptedInterview {

    private final String type;

    /**
     * Constructs a {@code JsonAdaptedInterview} with the given {@code interviewName}.
     */
    @JsonCreator
    public JsonAdaptedInterview(String type) {
        this.type = type;
    }

    /**
     * Converts a given {@code Interview} into this class for Jackson use.
     */
    public JsonAdaptedInterview(Interview source) {
        type = source.type;
    }

    @JsonValue
    public String getType() {
        return type;
    }

    /**
     * Converts this Jackson-friendly adapted interview object into the model's {@code Interview} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted interview.
     */
    public Interview toModelType() throws IllegalValueException {
        if (!Interview.isValidType(type)) {
            throw new IllegalValueException(Interview.MESSAGE_CONSTRAINTS);
        }
        return new Interview(type);
    }

}
