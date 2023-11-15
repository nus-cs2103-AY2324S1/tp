package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.review.Description;
import seedu.address.model.review.Rating;
import seedu.address.model.stall.review.StallReview;

/**
 * Jackson-friendly version of {@link StallReview}.
 */
public class JsonAdaptedStallReview {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Stall Review's %s field is missing!";
    private String rating;
    private String description;

    /**
     * Constructs a {@code JsonAdaptedStallReview} with the given stall review details.
     */
    @JsonCreator
    public JsonAdaptedStallReview(@JsonProperty("rating") String rating,
                                  @JsonProperty("description") String description) {
        this.rating = rating;
        this.description = description;
    }

    /**
     * Converts a given {@code StallReview} into this class for Jackson use.
     */
    public JsonAdaptedStallReview(StallReview source) {
        if (source == null) {
            return;
        }
        description = source.getDescription().description;
        rating = source.getRating().rating;
    }

    /**
     * Converts this Jackson-friendly adapted stall review object into the model's {@code StallReview} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted stall review.
     */
    public StallReview toModelType() throws IllegalValueException {
        if (description != null && rating == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Rating.class.getSimpleName()));
        } else if (description == null && rating != null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Description.class.getSimpleName()));
        } else if (description == null) {
            return null;
        }

        if (!Description.isValidDescription(description)) {
            throw new IllegalValueException(Description.MESSAGE_CONSTRAINTS);
        }
        if (!Rating.isValidRating(rating)) {
            throw new IllegalValueException(Rating.MESSAGE_CONSTRAINTS);
        }

        final Description modelDescription = new Description(description);
        final Rating modelRating = new Rating(rating);
        return new StallReview(modelRating, modelDescription);
    }

}
