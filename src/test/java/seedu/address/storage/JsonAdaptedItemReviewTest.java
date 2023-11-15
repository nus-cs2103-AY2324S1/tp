package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalItemReviews.ITEM_REVIEW_1;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.review.Description;
import seedu.address.model.review.Rating;



public class JsonAdaptedItemReviewTest {
    private static final String INVALID_RATING = "-1";
    private static final String INVALID_DESCRIPTION = " ";

    private static final String VALID_RATING = ITEM_REVIEW_1.getRating().toString();
    private static final String VALID_DESCRIPTION = ITEM_REVIEW_1.getDescription().toString();

    @Test
    public void toModelType_validItemReviewDetails_returnsItemReview() throws Exception {
        JsonAdaptedItemReview itemReview = new JsonAdaptedItemReview(ITEM_REVIEW_1);
        assertEquals(ITEM_REVIEW_1, itemReview.toModelType());
    }


    // Heuristic: No More Than One Invalid Input In A Test Case
    @Test
    public void toModelType_invalidRating_throwsIllegalValueException() {
        JsonAdaptedItemReview itemReview =
                new JsonAdaptedItemReview(INVALID_RATING, VALID_DESCRIPTION);
        String expectedMessage = Rating.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, itemReview::toModelType);
    }
    @Test
    public void toModelType_nullRating_throwsIllegalValueException() {
        JsonAdaptedItemReview itemReview = new JsonAdaptedItemReview(null, VALID_DESCRIPTION);
        String expectedMessage = String.format(JsonAdaptedItemReview.MISSING_FIELD_MESSAGE_FORMAT,
                Rating.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, itemReview::toModelType);
    }
    @Test
    public void toModelType_invalidDescription_throwsIllegalValueException() {
        JsonAdaptedItemReview itemReview =
                new JsonAdaptedItemReview(VALID_RATING, INVALID_DESCRIPTION);
        String expectedMessage = Description.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, itemReview::toModelType);
    }
    @Test
    public void toModelType_nullDescription_throwsIllegalValueException() {
        JsonAdaptedItemReview itemReview = new JsonAdaptedItemReview(VALID_RATING, null);
        String expectedMessage = String.format(JsonAdaptedItemReview.MISSING_FIELD_MESSAGE_FORMAT,
                Description.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, itemReview::toModelType);
    }
}
