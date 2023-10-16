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
 * JSON. Checks are done when converting to model type.
 */
public final class JsonTag {
    private final String name;

    /**
     * Constructs a JsonTag with the specified name.
     *
     * @param _name Name.
     */
    @JsonCreator
    public JsonTag(String _name) {
        this.name = _name;
    }

    /**
     * Constructs a JsonTag by converting the specified {@code Tag}.
     */
    public JsonTag(Tag tag) {
        this.name = tag.name;
    }

    @JsonValue
    public String getName() {
        return this.name;
    }

    /**
     * Attempts to convert this to the model's {@code Tag} type.
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
