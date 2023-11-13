package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.assignment.Assignment;
import seedu.address.model.assignment.Title;

/**
 * Jackson-friendly version of {@link Assignment}.
 */
public class JsonAdaptedAssignment {
    private final String title;

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedAssignment(@JsonProperty("title") String title) {
        this.title = title;
    }

    /**
     * Converts a given {@code Assignment} into this class for Jackson use.
     */
    public JsonAdaptedAssignment(Assignment source) {
        this.title = source.getTitle().fullTitle;
    }

    /**
     * Converts this Jackson-friendly adapted assignment object into the model's {@code Assignment} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted assignment.
     */
    public Assignment toModelType() throws IllegalValueException {
        if (!Title.isValidTitle(this.title)) {
            throw new IllegalValueException(Title.MESSAGE_CONSTRAINTS);
        }

        final Title modelTitle = new Title(this.title);
        return new Assignment(modelTitle);
    }
}
