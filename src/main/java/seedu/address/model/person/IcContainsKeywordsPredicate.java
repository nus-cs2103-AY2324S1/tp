package seedu.address.model.person;

import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;

/**
 * Tests that a {@code Person}'s {@code Ic} matches any of the keywords given.
 */
public class IcContainsKeywordsPredicate implements Predicate<Person> {
    private final String keywords;

    public IcContainsKeywordsPredicate(String keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        return keywords.equalsIgnoreCase(person.getIc().toString());
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof IcContainsKeywordsPredicate)) {
            return false;
        }

        IcContainsKeywordsPredicate otherIcContainsKeywordsPredicate = (IcContainsKeywordsPredicate) other;
        return keywords.equals(otherIcContainsKeywordsPredicate.keywords);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("keywords", keywords).toString();
    }
}
