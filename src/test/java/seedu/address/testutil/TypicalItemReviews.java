package seedu.address.testutil;

import seedu.address.model.item.review.ItemReview;

/**
 * A utility class containing a list of {@code ItemReview} objects to be used in tests.
 */
public class TypicalItemReviews {
    public static final ItemReview ITEM_REVIEW_1 = new ItemReviewBuilder().withDescription("Average")
            .withRating("3").build();
    public static final ItemReview ITEM_REVIEW_2 = new ItemReviewBuilder().withDescription("Tastes bad")
            .withRating("2").build();

    private TypicalItemReviews() {} // prevents instantiation
}
