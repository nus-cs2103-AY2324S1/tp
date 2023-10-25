package seedu.address.model.band;

import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;

/**
 * Tests that a {@code Band} is equal to another {@code Band}.
 */

public class BandIsSamePredicate implements Predicate<Band> {
    private final Band bandToCheck;

    public BandIsSamePredicate(Band bandToCheck) {
        this.bandToCheck = bandToCheck;
    }

    @Override
    public boolean test(Band band) {
        return bandToCheck.equals(band);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof seedu.address.model.band.BandIsSamePredicate)) {
            return false;
        }

        seedu.address.model.band.BandIsSamePredicate otherBandIsSamePredicate = (seedu.address.model.band.BandIsSamePredicate) other;
        return bandToCheck.equals(otherBandIsSamePredicate.bandToCheck);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("bandToCheck", bandToCheck).toString();
    }
}
