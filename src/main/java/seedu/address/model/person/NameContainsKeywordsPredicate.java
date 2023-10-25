package seedu.address.model.person;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.booking.Booking;

/**
 * Tests that a {@code Person}'s {@code Name} matches any of the keywords given.
 */
public class NameContainsKeywordsPredicate implements Predicate<Booking> {
    private final List<String> keywords;

    /**
     * Constructs a {@code NameContainsKeywordsPredicate} with the specified list of keywords.
     *
     * @param keywords The list of keywords to search for in booking names and room values.
     */
    public NameContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    /**
     * Tests whether a given booking's name or room value matches any of the keywords.
     *
     * @param booking The booking to test.
     * @return True if the booking's name or room value matches any of the keywords, false otherwise.
     */
    @Override
    public boolean test(Booking booking) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(booking.getName().fullName, keyword)
                        || StringUtil.containsWordIgnoreCase(Integer.toString(booking.getRoom().value), keyword));
    }

    /**
     * Checks whether this predicate is equal to another object.
     *
     * @param other The object to compare with.
     * @return True if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof NameContainsKeywordsPredicate)) {
            return false;
        }

        NameContainsKeywordsPredicate otherNameContainsKeywordsPredicate = (NameContainsKeywordsPredicate) other;
        return keywords.equals(otherNameContainsKeywordsPredicate.keywords);
    }

    /**
     * Returns a string representation of this predicate.
     *
     * @return A string representation of this predicate.
     */
    @Override
    public String toString() {
        return new ToStringBuilder(this).add("keywords", keywords).toString();
    }
}
