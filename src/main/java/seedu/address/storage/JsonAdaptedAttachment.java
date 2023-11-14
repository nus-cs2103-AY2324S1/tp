package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.attachment.Attachment;

/**
 * Jackson-friendly version of {@link Attachment}.
 */
class JsonAdaptedAttachment {

    private final String path;

    /**
     * Constructs a {@code JsonAdaptedFile} with the given {@code path}.
     */
    @JsonCreator
    public JsonAdaptedAttachment(String path) {
        this.path = path;
    }

    /**
     * Converts a given {@code File} into this class for Jackson use.
     */
    public JsonAdaptedAttachment(Attachment source) {
        path = source.file.getAbsolutePath();
    }

    @JsonValue
    public String getPath() {
        return path;
    }

    /**
     * Converts this Jackson-friendly adapted tag object into the model's {@code Attachment} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted tag.
     */
    public Attachment toModelType() throws IllegalValueException {
        if (!Attachment.isValidAttachment(path)) {
            throw new IllegalValueException(Attachment.MESSAGE_CONSTRAINTS);
        }
        return new Attachment(path);
    }

}
