package seedu.staffsnap.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.staffsnap.commons.exceptions.IllegalValueException;
import seedu.staffsnap.model.interview.Interview;
import seedu.staffsnap.model.interview.Rating;

/**
 * Jackson-friendly version of {@link Interview}.
 */
class JsonAdaptedInterview {

    private final String type;
    private final String rating;

    /**
     * Constructs a {@code JsonAdaptedInterview} with the given interview details.
     */
    @JsonCreator
    public JsonAdaptedInterview(@JsonProperty("type") String type, @JsonProperty("rating") String rating) {
        this.type = type;
        this.rating = rating;
    }

    /**
     * Converts a given {@code Interview} into this class for Jackson use.
     */
    public JsonAdaptedInterview(Interview source) {
        type = source.type;
        rating = source.getRating().value;
    }

    /**
     * Converts this Jackson-friendly adapted interview object into the model's {@code Interview} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted interview.
     */
    public Interview toModelType() throws IllegalValueException {
        if (!Interview.isValidType(type)) {
            throw new IllegalValueException(Interview.MESSAGE_CONSTRAINTS);
        }
        if (!Rating.isValidRating(rating)) {
            throw new IllegalValueException(Rating.MESSAGE_CONSTRAINTS);
        }
        final Rating modelRating = new Rating(rating);
        return new Interview(type, modelRating);
    }

}
