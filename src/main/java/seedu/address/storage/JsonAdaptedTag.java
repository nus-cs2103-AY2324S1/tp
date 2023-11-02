package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.booking.RoomTypeTag;

/**
 * Jackson-friendly version of {@link RoomTypeTag}.
 */
class JsonAdaptedTag {

    private final String tagName;

    /**
     * Constructs a {@code JsonAdaptedTag} with the given {@code tagName}.
     */
    @JsonCreator
    public JsonAdaptedTag(String tagName) {
        this.tagName = tagName;
    }

    /**
     * Converts a given {@code Tag} into this class for Jackson use.
     */
    public JsonAdaptedTag(RoomTypeTag source) {
        tagName = source.roomTypeTagName;
    }

    @JsonValue
    public String getTagName() {
        return tagName;
    }

    /**
     * Converts this Jackson-friendly adapted tag object into the model's {@code Tag} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted tag.
     */
    public RoomTypeTag toModelType() throws IllegalValueException {
        if (!RoomTypeTag.isValidRoomTypeTagName(tagName)) {
            throw new IllegalValueException(RoomTypeTag.MESSAGE_CONSTRAINTS);
        }
        return new RoomTypeTag(tagName);
    }

}
