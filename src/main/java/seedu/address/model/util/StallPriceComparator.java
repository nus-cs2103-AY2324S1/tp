package seedu.address.model.util;

import java.util.Comparator;

import seedu.address.model.stall.Stall;

/**
 * Compares two stalls by their average price.
 */
public class StallPriceComparator implements Comparator<Stall> {
    @Override
    public int compare(Stall stall1, Stall stall2) {
        if (stall1.getAveragePrice() == null && stall2.getAveragePrice() == null) {
            return 0;
        } else if (stall1.getAveragePrice() == null) {
            return 1;
        } else if (stall2.getAveragePrice() == null) {
            return -1;
        }

        return stall1.getAveragePrice().compareTo(stall2.getAveragePrice());
    }
}
