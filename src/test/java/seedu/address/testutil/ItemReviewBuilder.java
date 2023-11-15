package seedu.address.testutil;

import seedu.address.model.item.review.ItemReview;
import seedu.address.model.review.Description;
import seedu.address.model.review.Rating;

/**
 * A utility class to help with building ItemReview objects.
 */
public class ItemReviewBuilder {
    public static final String DEFAULT_RATING = "4";
    public static final String DEFAULT_DESCRIPTION = "This item is quite good!";
    private Rating rating;
    private Description description;

    /**
     * Creates a {@code ItemReviewBuilder} with the default details.
     */
    public ItemReviewBuilder() {
        rating = new Rating(DEFAULT_RATING);
        description = new Description(DEFAULT_DESCRIPTION);
    }

    /**
     * Initializes the ItemReviewBuilder with the data of {@code itemReviewToCopy}.
     */
    public ItemReviewBuilder(ItemReview itemReviewToCopy) {
        rating = itemReviewToCopy.getRating();
        description = itemReviewToCopy.getDescription();
    }

    /**
     * Sets the {@code Rating} of the {@code ItemReview} that we are building.
     */
    public ItemReviewBuilder withRating(String rating) {
        this.rating = new Rating(rating);
        return this;
    }

    /**
     * Sets the {@code Description} of the {@code ItemReview} that we are building.
     */
    public ItemReviewBuilder withDescription(String description) {
        this.description = new Description(description);
        return this;
    }

    public ItemReview build() {
        return new ItemReview(rating, description);
    }
}
