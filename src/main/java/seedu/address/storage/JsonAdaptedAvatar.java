package seedu.address.storage;

import java.io.IOException;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Avatar;

/**
 * Jackson-friendly version of {@link Avatar}.
 */
class JsonAdaptedAvatar {
    private final String path;

    /**
     * Constructs a {@code JsonAdaptedTag} with the given {@code tagName}.
     */
    @JsonCreator
    public JsonAdaptedAvatar(String path) {
        this.path = path;
    }

    /**
     * Converts a given {@code Avatar} into this class for Jackson use.
     */
    public JsonAdaptedAvatar(Avatar source) {
        path = source.getPath();
    }

    @JsonValue
    public String getPath() {
        return path;
    }

    /**
     * Converts this Jackson-friendly adapted avatar object into the model's {@code Avatar} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted avatar.
     */
    public Avatar toModelType() throws IllegalValueException {
        try {
            if (this.path.equals("")) {
                return new Avatar();
            } else {
                return new Avatar(this.path);
            }
        } catch (IOException e) {
            throw new IllegalValueException("Error encountered while loading avatar");
        }
    }

}
