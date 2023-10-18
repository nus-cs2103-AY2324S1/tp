package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.tag.Genre;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Genre}.
 */
public class JsonAdaptedGenre extends JsonAdaptedTag {

    @JsonCreator
    public JsonAdaptedGenre(String genre) {
        super(genre);
    }

    public JsonAdaptedGenre(Tag source) {
        super(source);
    }

    @Override
    public Tag toModelType() throws IllegalValueException {
        if (!Genre.isValidGenreName(super.getTagName())) {
            throw new IllegalValueException(Genre.MESSAGE_CONSTRAINTS);
        }
        return new Genre(super.getTagName());
    }


}
