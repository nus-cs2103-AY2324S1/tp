package seedu.address.testutil;

import seedu.address.model.review.Description;
import seedu.address.model.review.Rating;
import seedu.address.model.stall.review.StallReview;

/**
 * A utility class to help with building StallReview objects.
 */
public class StallReviewBuilder {
    public static final String DEFAULT_RATING = "5";
    public static final String DEFAULT_DESCRIPTION = "This stall is good!";
    private Rating rating;
    private Description description;

    /**
     * Creates a {@code StallReviewBuilder} with the default details.
     */
    public StallReviewBuilder() {
        rating = new Rating(DEFAULT_RATING);
        description = new Description(DEFAULT_DESCRIPTION);
    }

    /**
     * Initializes the StallReviewBuilder with the data of {@code stallReviewToCopy}.
     */
    public StallReviewBuilder(StallReview stallReviewToCopy) {
        rating = stallReviewToCopy.getRating();
        description = stallReviewToCopy.getDescription();
    }

    /**
     * Sets the {@code Rating} of the {@code StallReview} that we are building.
     */
    public StallReviewBuilder withRating(String rating) {
        this.rating = new Rating(rating);
        return this;
    }

    /**
     * Sets the {@code Description} of the {@code StallReview} that we are building.
     */
    public StallReviewBuilder withDescription(String description) {
        this.description = new Description(description);
        return this;
    }

    public StallReview build() {
        return new StallReview(rating, description);
    }

}
