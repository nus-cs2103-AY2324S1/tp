package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.Messages;
import seedu.address.model.tag.Tag;



/**
 * Immutable, Jackson-friendly version of {@link Tag}.
 *
 * The data it contains may be invalid if the instance was deserialized from
 * JSON. Checks are done when converting {@link #toModelType()}.
 */
public final class JsonTag {
    private final String value;

    /**
     * Constructs by converting the specified {@link Tag}.
     */
    public JsonTag(Tag tag) {
        this(tag.value);
    }

    @JsonCreator
    public JsonTag(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return this.value;
    }

    /**
     * Attempts to convert this to the model's {@link Tag} type.
     *
     * @throws IllegalValueException If any data this contains is invalid.
     */
    public Tag toModelType() throws IllegalValueException {
        if (!Tag.isValid(this.value)) {
            throw new IllegalValueException(
                Messages.tagInvalid(this.value)
            );
        }

        return new Tag(this.value);
    }
}
