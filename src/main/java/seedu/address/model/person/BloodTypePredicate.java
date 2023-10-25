package seedu.address.model.person;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.commons.util.ToStringBuilder;

/**
 * Tests that a {@code Patient}'s {@code BloodType} matches any of the keywords given.
 */
public class BloodTypePredicate implements Predicate<Person> {
    private final String keywords;

    public BloodTypePredicate(String keywords) {
        String[] trimmed = keywords.split(" ");
        this.keywords = trimmed[2];
    }

    @Override
    public boolean test(Person person) {
        if (person.isPatient()) {
            Patient patient = (Patient) person;
            return keywords.equalsIgnoreCase(patient.getBloodType().toString());
        }
        return false;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof BloodTypePredicate)) {
            return false;
        }

        BloodTypePredicate otherBloodTypePredicate = (BloodTypePredicate) other;
        return keywords.equals(otherBloodTypePredicate.keywords);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("keywords", keywords).toString();
    }
}