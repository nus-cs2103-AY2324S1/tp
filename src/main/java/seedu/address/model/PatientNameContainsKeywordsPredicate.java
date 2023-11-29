package seedu.address.model;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.person.patients.Patient;

/**
 * Tests that a {@code Person}'s {@code Name} matches any of the keywords given.
 */
public class PatientNameContainsKeywordsPredicate implements Predicate<Patient> {

    private final List<String> keywords;

    public PatientNameContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Patient person) {
        return keywords.stream()
            .anyMatch(
                keyword -> person.getName().fullName.toLowerCase().contains(keyword.toLowerCase()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof PatientNameContainsKeywordsPredicate)) {
            return false;
        }

        PatientNameContainsKeywordsPredicate otherNameContainsKeywordsPredicate =
            (PatientNameContainsKeywordsPredicate) other;
        return keywords.equals(otherNameContainsKeywordsPredicate.keywords);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("keywords", keywords).toString();
    }
}
