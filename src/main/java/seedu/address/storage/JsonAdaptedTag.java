package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.tag.Tag;


/**
 * Jackson-friendly version of {@link Tag}.
 */
class JsonAdaptedTag {

    private final String tagName;

    private final String tagCategory;

    /**
     * Constructs a {@code JsonAdaptedTag} with the given {@code tagName} and {@code tagCategory}.
     */
    @JsonCreator
    public JsonAdaptedTag(
            @JsonProperty("tagCategory") String tagCategory,
            @JsonProperty("tagName") String tagName) {
        this.tagCategory = tagCategory;
        this.tagName = tagName;
    }

    /**
     * Converts a given {@code Tag} into this class for Jackson use.
     */
    public JsonAdaptedTag(Tag source) {
        tagName = source.tagName;
        tagCategory = source.tagCategory;
    }

    @JsonValue
    public String getTagName() {
        return tagName;
    }

    public String getTagCategory() {
        return tagCategory;
    }

    /**
     * Converts this Jackson-friendly adapted tag object into the model's {@code Tag} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted tag.
     */
    public Tag toModelType() throws IllegalValueException {
        if (!Tag.isValidTagName(tagName)) {
            throw new IllegalValueException(Tag.MESSAGE_CONSTRAINTS);
        }
        return new Tag(tagName, tagCategory);
    }

}
