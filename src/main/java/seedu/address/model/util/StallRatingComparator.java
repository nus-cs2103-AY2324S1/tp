package seedu.address.model.util;

import java.util.Comparator;

import seedu.address.model.stall.Stall;

/**
 * Compares two stalls based on their ratings.
 */
public class StallRatingComparator implements Comparator<Stall> {
    @Override
    public int compare(Stall stall1, Stall stall2) {
        if (stall1.getRating() == null && stall2.getRating() == null) {
            return 0;
        } else if (stall1.getRating() == null) {
            return 1;
        } else if (stall2.getRating() == null) {
            return -1;
        }
        return stall1.getRating().compareTo(stall2.getRating());
    }
}
