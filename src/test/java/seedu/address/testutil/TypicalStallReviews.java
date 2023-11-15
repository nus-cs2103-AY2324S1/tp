package seedu.address.testutil;

import seedu.address.model.stall.review.StallReview;

/**
 * A utility class containing a list of {@code StallReview} objects to be used in tests.
 */
public class TypicalStallReviews {
    public static final StallReview STALL_REVIEW_1 = new StallReviewBuilder().withDescription("Average")
            .withRating("3").build();

    public static final StallReview STALL_REVIEW_2 = new StallReviewBuilder().withDescription("Serves bad food")
            .withRating("2").build();
    public static final StallReview STALL_REVIEW_3 = new StallReviewBuilder()
            .withDescription("The ambience is fantastic auntie very chio.")
            .withRating("4").build();

    private TypicalStallReviews() {} // prevents instantiation
}
