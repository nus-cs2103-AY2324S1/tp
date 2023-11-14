package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.record.Condition;

/**
 * Jackson-friendly version of {@link Condition}.
 */
public class JsonAdaptedCondition {

    private final String condition;

    /**
     * Constructs a {@code JsonAdaptedCondition} with the given {@code condition}.
     */
    @JsonCreator
    public JsonAdaptedCondition(String condition) {
        this.condition = condition;
    }
    /**
     * Converts a given {@code Condition} into this class for Jackson use.
     */
    public JsonAdaptedCondition(Condition source) {
        this.condition = source.condition;
    }

    @JsonValue
    public String getCondition() {
        return condition;
    }
    /**
     * Converts this Jackson-friendly adapted tag object into the model's {@code Condition} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted condition.
     */
    public Condition toModelType() throws IllegalValueException {
        if (condition == null) {
            throw new IllegalValueException(Condition.MESSAGE_CONSTRAINTS);
        }
        if (!Condition.isValidCondition(condition)) {
            throw new IllegalValueException(Condition.MESSAGE_CONSTRAINTS);
        }
        return new Condition(condition);
    }
}
