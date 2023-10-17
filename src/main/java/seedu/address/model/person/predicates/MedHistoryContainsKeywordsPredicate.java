package seedu.address.model.person.predicates;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.person.Patient;
import seedu.address.model.person.Person;

/**
 * Tests that a {@code Patient}'s {@code Medical History} matches any of the keywords given.
 */
public class MedHistoryContainsKeywordsPredicate implements Predicate<Person> {

    private final List<String> keywords;

    public MedHistoryContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        if (!(person instanceof Patient)) {
            return false;
        }

        // It is safe to type cast Person to Patient due to the guard clause above.
        Patient patient = (Patient) person;
        return patient.getMedicalHistory()
                .stream()
                .map(medicalHistory -> keywords
                        .stream()
                        .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(medicalHistory.value, keyword)))
                .reduce(false, (x, y) -> x || y);
    }
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof MedHistoryContainsKeywordsPredicate)) {
            return false;
        }

        MedHistoryContainsKeywordsPredicate otherMedHistoryContainsKeywordsPredicate =
                (MedHistoryContainsKeywordsPredicate) other;
        return keywords.equals(otherMedHistoryContainsKeywordsPredicate.keywords);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("keywords", keywords).toString();
    }

}
