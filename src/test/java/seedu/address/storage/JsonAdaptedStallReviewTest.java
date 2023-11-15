package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalStallReviews.STALL_REVIEW_1;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.review.Rating;



public class JsonAdaptedStallReviewTest {
    private static final String INVALID_RATING = "-1";
    private static final String INVALID_DESCRIPTION = " ";
    private static final String VALID_RATING = STALL_REVIEW_1.getRating().toString();
    private static final String VALID_DESCRIPTION = STALL_REVIEW_1.getDescription().toString();

    // Heuristic: Each Valid Input at Least Once in a Positive Test Case
    @Test
    public void toModelType_validStallReviewDetails_returnsStallReview() throws Exception {
        JsonAdaptedStallReview stallReview = new JsonAdaptedStallReview(STALL_REVIEW_1);
        assertEquals(STALL_REVIEW_1, stallReview.toModelType());
    }

    // Heuristic: No More Than One Invalid Input In A Test Case
    @Test
    public void toModelType_invalidRating_throwsIllegalValueException() {
        JsonAdaptedStallReview stallReview =
                new JsonAdaptedStallReview(INVALID_RATING, VALID_DESCRIPTION);
        String expectedMessage = Rating.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, stallReview::toModelType);
    }

    @Test
    public void toModelType_nullRating_throwsIllegalValueException() {
        JsonAdaptedStallReview stallReview = new JsonAdaptedStallReview(null, VALID_DESCRIPTION);
        String expectedMessage = String.format(JsonAdaptedStallReview.MISSING_FIELD_MESSAGE_FORMAT,
                Rating.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, stallReview::toModelType);
    }

    @Test
    public void toModelType_invalidDescription_throwsIllegalValueException() {
        JsonAdaptedStallReview stallReview =
                new JsonAdaptedStallReview(VALID_RATING, INVALID_DESCRIPTION);
        String expectedMessage = seedu.address.model.review.Description.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, stallReview::toModelType);
    }

    @Test
    public void toModelType_nullDescription_throwsIllegalValueException() {
        JsonAdaptedStallReview stallReview = new JsonAdaptedStallReview(VALID_RATING, null);
        String expectedMessage = String.format(JsonAdaptedStallReview.MISSING_FIELD_MESSAGE_FORMAT,
                seedu.address.model.review.Description.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, stallReview::toModelType);
    }
}
