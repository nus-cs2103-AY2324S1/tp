package seedu.address.model.stall.review;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalStallReviews.STALL_REVIEW_1;
import static seedu.address.testutil.TypicalStallReviews.STALL_REVIEW_2;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.StallReviewBuilder;

public class StallReviewTest {

    @Test
    public void equals() {
        // same values -> returns true
        StallReview review1Copy = new StallReviewBuilder(STALL_REVIEW_1).build();
        assertTrue(STALL_REVIEW_1.equals(review1Copy));

        // same object -> returns true
        assertTrue(STALL_REVIEW_1.equals(STALL_REVIEW_1));

        // null -> returns false
        assertFalse(STALL_REVIEW_1.equals(null));

        // different type -> returns false
        assertFalse(STALL_REVIEW_1.equals(5));

        // different stall review -> returns false
        assertFalse(STALL_REVIEW_1.equals(STALL_REVIEW_2));

        // different description -> returns false
        StallReview editedReview1 = new StallReviewBuilder(STALL_REVIEW_1).withDescription("Not bad").build();
        assertFalse(STALL_REVIEW_1.equals(editedReview1));

        // different rating -> returns false
        editedReview1 = new StallReviewBuilder(STALL_REVIEW_1).withRating("2").build();
        assertFalse(STALL_REVIEW_1.equals(editedReview1));
    }

    @Test
    public void toStringMethod() {
        String expected = StallReview.class.getCanonicalName() + "{"
                + "rating=" + STALL_REVIEW_1.getRating() + ", "
                + "description=" + STALL_REVIEW_1.getDescription() + "}";
        assertEquals(expected, STALL_REVIEW_1.toString());
    }
}
