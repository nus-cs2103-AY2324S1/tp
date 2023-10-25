package seedu.lovebook.model.person;

import static java.util.Objects.requireNonNull;

/**
 * Represents a Date's starred status in the lovebook.
 * Guarantees: immutable; is always valid
 */
public class Star {
    public final String isStarred;

    /**
     * Constructor for the star class
     */
    public Star(String isStarred) {
        requireNonNull(isStarred);
        this.isStarred = isStarred;
    }

    @Override
    public String toString() {
        return isStarred;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Star // instanceof handles nulls
                && isStarred.equals(((Star) other).isStarred)); // state check
    }

    @Override
    public int hashCode() {
        return isStarred.hashCode();
    }
}
