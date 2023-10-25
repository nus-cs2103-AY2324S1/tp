package seedu.address.model.applicant.predicate;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.applicant.Applicant;

import java.util.function.Predicate;

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

        PhoneContainsNumberPredicate otherNameContainsKeywordsPredicate = (PhoneContainsNumberPredicate) other;
        return number.equals(otherNameContainsKeywordsPredicate.number);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("number", number).toString();
    }
}
