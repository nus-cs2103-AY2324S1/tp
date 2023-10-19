package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.Messages;
import seedu.address.model.tag.Tag;



/**
 * Jackson-friendly version of {@link Tag}.
 *
 * The data it contains may be invalid if the instance was deserialized from
 * JSON. Checks are done when converting {@link #toModelType()}.
 */
public final class JsonTag {
    private final String name;

    /**
     * Constructs by converting the specified {@link Tag}.
     */
    public JsonTag(Tag tag) {
        this(tag.name);
    }

    /**
     * Constructs for the specified name.
     */
    @JsonCreator
    public JsonTag(String name) {
        this.name = name;
    }

    @JsonValue
    public String getName() {
        return this.name;
    }

    /**
     * Attempts to convert this to the model's {@link Tag} type.
     *
     * @throws IllegalValueException If any data this contains is invalid.
     */
    public Tag toModelType() throws IllegalValueException {
        if (!Tag.isValidName(this.name)) {
            throw new IllegalValueException(
                Messages.tagInvalid(this.name)
            );
        }

        return new Tag(this.name);
    }
}
