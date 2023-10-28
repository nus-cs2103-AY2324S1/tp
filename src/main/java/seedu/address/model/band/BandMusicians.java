package seedu.address.model.band;

import java.util.Set;

import seedu.address.model.musician.Musician;

/**
 * Represents the list of musicians in a Band.
 */
public class BandMusicians {
    private Set<Musician> musicians;

    /**
     * Musician list can be empty.
     */
    public BandMusicians(Set<Musician> musicians) {
        this.musicians = musicians;
    }

    public void setMusician(Musician target, Musician editedMusician) {
        if (musicians.contains(target)) {
            musicians.remove(target);
        }

        if (editedMusician != null) {
            musicians.add(editedMusician);
        }
    }

    @Override
    public String toString() {
        return musicians.toString();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof BandMusicians)) {
            return false;
        }

        BandMusicians otherList = (BandMusicians) other;
        return musicians.equals(otherList);
    }

    @Override
    public int hashCode() {
        return musicians.hashCode();
    }
}
