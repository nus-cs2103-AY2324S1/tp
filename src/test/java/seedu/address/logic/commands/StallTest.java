package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import seedu.address.model.review.Description;
import seedu.address.model.review.Rating;
import seedu.address.model.stall.Location;
import seedu.address.model.stall.Name;
import seedu.address.model.stall.Stall;
import seedu.address.model.stall.review.StallReview;

public class StallTest {

    @Test
    public void getStallReviewString_withReview_returnsExpectedString() {
        Stall stall = new Stall(new Name("name"), new Location("location"));
        StallReview review = new StallReview(new Rating("5"), new Description("Great food"));
        stall.setStallReview(review);

        String result = stall.getStallReviewString();

        assertEquals("★★★★★Great food", result);
    }

    @Test
    public void getStallReviewString_withoutReview_returnsNoReviewString() {
        Stall stall = new Stall(new Name("name"), new Location("location"));

        String result = stall.getStallReviewString();

        assertEquals("No review added.", result);
    }
}
