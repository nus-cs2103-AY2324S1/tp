package seedu.address.model.person;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.commons.util.ToStringBuilder;

/**
 * Tests that a {@code Person}'s {@code Licence Plate} matches any of the keywords given.
 */
public class LicenceContainsKeywordsPredicate implements Predicate<Person> {
    private final List<String> keywords;

    public LicenceContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(person.getLicencePlate().value, keyword));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof LicenceContainsKeywordsPredicate)) {
            return false;
        }

        LicenceContainsKeywordsPredicate otherNameContainsKeywordsPredicate = (LicenceContainsKeywordsPredicate) other;
        return keywords.equals(otherNameContainsKeywordsPredicate.keywords);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("keywords", keywords).toString();
    }

    /**
     * Checks if the list of keywords contain an empty string.
     *
     * @return true if the list of keywords contain an empty string, false otherwise.
     */
    public boolean isEmpty() {
        return keywords.contains("");
    }
}
