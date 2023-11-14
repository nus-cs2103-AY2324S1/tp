package seedu.address.model.applicant.predicate;

import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.applicant.Applicant;

/**
 * Tests that an {@code Applicant}'s {@code Phone} contains the number given.
 */
public class PhoneContainsNumberPredicate implements Predicate<Applicant> {
    private final String number;

    public PhoneContainsNumberPredicate(String number) {
        this.number = number;
    }

    @Override
    public boolean test(Applicant applicant) {
        return applicant.getPhone().value.contains(number);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof PhoneContainsNumberPredicate)) {
            return false;
        }

        PhoneContainsNumberPredicate otherPhoneContainsNumberPredicate = (PhoneContainsNumberPredicate) other;
        return number.equals(otherPhoneContainsNumberPredicate.number);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("number", number).toString();
    }
}
