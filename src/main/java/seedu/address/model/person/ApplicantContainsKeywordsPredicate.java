package seedu.address.model.person;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.commons.util.ToStringBuilder;


/**
 * Tests that a {@code Applicant}'s {@code Field's} matches any of the keywords given.
 */
public class ApplicantContainsKeywordsPredicate implements Predicate<Applicant> {
    private final List<String> keywords;

    /**
     * Constructs a predicate that returns true if the applicant's name or phone number contains any of the keywords.
     *
     * @param keywords The keywords to check.
     */
    public ApplicantContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Applicant applicant) {
        return keywords.stream()
            .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(applicant.getName().fullName, keyword)
                || StringUtil.containsWordIgnoreCase(applicant.getPhone().value, keyword)
                || StringUtil.containsWordIgnoreCase((applicant.getInterviewTime()).toString(), keyword));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ApplicantContainsKeywordsPredicate)) {
            return false;
        }

        ApplicantContainsKeywordsPredicate otherApplicantContainsKeywordsPredicate =
            (ApplicantContainsKeywordsPredicate) other;
        return keywords.equals(otherApplicantContainsKeywordsPredicate.keywords);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("keywords", keywords).toString();
    }
}
