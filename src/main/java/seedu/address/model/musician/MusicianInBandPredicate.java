package seedu.address.model.musician;

import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.band.Band;

/**
 * Tests that a {@code Musician} is contained in a {@code Band}.
 */
public class MusicianInBandPredicate implements Predicate<Musician> {

    private final Band bandToCheck;

    public MusicianInBandPredicate(Band bandToCheck) {
        this.bandToCheck = bandToCheck;
    }

    @Override
    public boolean test(Musician musician) {
        return bandToCheck.getMusicians().contains(musician);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof MusicianInBandPredicate)) {
            return false;
        }

        MusicianInBandPredicate otherMusicianInBandPredicate = (MusicianInBandPredicate) other;
        return bandToCheck.equals(otherMusicianInBandPredicate.bandToCheck);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("bandToCheck", bandToCheck).toString();
    }
}
