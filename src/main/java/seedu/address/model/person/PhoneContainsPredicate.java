package seedu.address.model.person;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.commons.util.ToStringBuilder;

/**
 * Tests that a {@code Person}'s {@code Phone} matches any of the values given.
 */
public class PhoneContainsPredicate implements Predicate<Person> {
    private final List<String> values;

    public PhoneContainsPredicate(List<String> values) {
        this.values = values;
    }

    @Override
    public boolean test(Person person) {
        return values.stream()
                .anyMatch(value -> value.isEmpty()
                        || person.getPhone().toString().contains(value));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof PhoneContainsPredicate)) {
            return false;
        }

        PhoneContainsPredicate otherPhoneContainsPredicate = (PhoneContainsPredicate) other;
        return values.equals(otherPhoneContainsPredicate.values);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("values", values).toString();
    }
}
