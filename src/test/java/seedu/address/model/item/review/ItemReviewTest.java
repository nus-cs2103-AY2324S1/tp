package seedu.address.model.item.review;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalItemReviews.ITEM_REVIEW_1;
import static seedu.address.testutil.TypicalItemReviews.ITEM_REVIEW_2;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.ItemReviewBuilder;

public class ItemReviewTest {
    @Test
    public void equals() {
        // same values -> returns true
        ItemReview review1Copy = new ItemReviewBuilder(ITEM_REVIEW_1).build();
        assertTrue(ITEM_REVIEW_1.equals(review1Copy));

        // same object -> returns true
        assertTrue(ITEM_REVIEW_1.equals(ITEM_REVIEW_1));

        // null -> returns false
        assertFalse(ITEM_REVIEW_1.equals(null));

        // different type -> returns false
        assertFalse(ITEM_REVIEW_1.equals(5));

        // different item review -> returns false
        assertFalse(ITEM_REVIEW_1.equals(ITEM_REVIEW_2));

        // different description -> returns false
        ItemReview editedReview1 = new ItemReviewBuilder(ITEM_REVIEW_1).withDescription("Not bad").build();
        assertFalse(ITEM_REVIEW_1.equals(editedReview1));

        // different rating -> returns false
        editedReview1 = new ItemReviewBuilder(ITEM_REVIEW_1).withRating("2").build();
        assertFalse(ITEM_REVIEW_1.equals(editedReview1));
    }

    @Test
    public void toStringMethod() {
        String expected = ItemReview.class.getCanonicalName() + "{"
                + "rating=" + ITEM_REVIEW_1.getRating() + ", "
                + "description=" + ITEM_REVIEW_1.getDescription() + "}";
        assertEquals(expected, ITEM_REVIEW_1.toString());
    }
}
