package seedu.address.model.internship;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;

/**
 * Tests that a {@code Internship}'s {@code Deadline} lies within the range given.
 */
public class DeadlineWithinRangePredicate implements Predicate<Internship> {
    private final List<Deadline> deadlineRange;
    public DeadlineWithinRangePredicate(List<Deadline> deadlineRange) {
        this.deadlineRange = deadlineRange;
    }

    @Override
    public boolean test(Internship internship) {
        Deadline internshipDeadline = internship.getDeadline();
        Deadline deadlineLower = deadlineRange.get(0);
        Deadline deadlineUpper = deadlineRange.get(1);

        return internshipDeadline.compareTo(deadlineLower) >= 0
                && internshipDeadline.compareTo(deadlineUpper) <= 0;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeadlineWithinRangePredicate)) {
            return false;
        }

        DeadlineWithinRangePredicate otherDeadlineWithinRangePredicate = (DeadlineWithinRangePredicate) other;
        return deadlineRange.equals(otherDeadlineWithinRangePredicate.deadlineRange);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("deadlineRange", deadlineRange).toString();
    }
}
