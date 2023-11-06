package seedu.address.model.applicant.predicate;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.applicant.Applicant;
import seedu.address.model.applicant.Email;

/**
 * Tests that an {@code Applicant}'s {@code Email} matches any of the keywords given.
 */
public class EmailContainsKeywordsPredicate implements Predicate<Applicant> {
    private final List<String> keywords;

    public EmailContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Applicant applicant) {
        Email email = applicant.getEmail();
        String localPart = email.getLocalPart();
        String domain = email.getDomain();

        return keywords.stream()
                .anyMatch(keyword -> keyword.equalsIgnoreCase(localPart)
                        || keyword.equalsIgnoreCase(domain)
                        || keyword.equalsIgnoreCase(email.value));
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
