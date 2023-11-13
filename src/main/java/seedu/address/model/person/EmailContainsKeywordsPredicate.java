package seedu.address.model.person;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;

/**
 * Tests that a {@code Person}'s {@code Email} matches any of the keywords given.
 */
public class EmailContainsKeywordsPredicate implements Predicate<Person> {
    private final List<String> keywords;

    public EmailContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        String email = person.getEmail().toString();
        Boolean result = false;
        for (String keyword : keywords) {
            result |= email.contains(keyword.toLowerCase());
        }
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EmailContainsKeywordsPredicate)) {
            return false;
        }

        EmailContainsKeywordsPredicate otherEmailContainsKeywordsPredicate = (EmailContainsKeywordsPredicate) other;
        return keywords.equals(otherEmailContainsKeywordsPredicate.keywords);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("keywords", keywords).toString();
    }
}
