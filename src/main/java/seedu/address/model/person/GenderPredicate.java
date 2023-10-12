package seedu.address.model.person;

import seedu.address.commons.util.StringUtil;
import seedu.address.commons.util.ToStringBuilder;

import java.util.List;
import java.util.function.Predicate;

/**
 * Tests that a {@code Person}'s {@code Gender} matches either male or female.
 */
public class GenderPredicate implements Predicate<Person> {
    private final String keywords;

    public GenderPredicate(String keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        return keywords.equalsIgnoreCase(person.getGender().toString());
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof GenderPredicate)) {
            return false;
        }

        GenderPredicate otherGenderPredicate = (GenderPredicate) other;
        return keywords.equals(otherGenderPredicate.keywords);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("keywords", keywords).toString();
    }
}
