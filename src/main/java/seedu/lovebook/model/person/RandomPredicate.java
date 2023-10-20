package seedu.lovebook.model.person;

import java.util.function.Predicate;

import seedu.lovebook.commons.util.ToStringBuilder;

/**
 * Tests that a {@code Date}'s {@code Name} matches any of the keywords given.
 */
public class RandomPredicate implements Predicate<Date> {
    private final Date random;

    public RandomPredicate(Date random) {
        this.random = random;
    }

    @Override
    public boolean test(Date date) {
        return date.equals(random);
    }

    /**
     * Returns true if both random predicates have the same date object since two dates cannot have
     * the same data fields.
     *
     * @param other
     * @return
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof RandomPredicate)) {
            return false;
        }

        RandomPredicate otherRandomPredicate = (RandomPredicate) other;
        return random.equals(otherRandomPredicate.random);
    }

    /**
     * Returns a string representation of this {@code RandomPredicate} object.
     *
     * @return a string
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this).add("random person", random).toString();
    }
}
