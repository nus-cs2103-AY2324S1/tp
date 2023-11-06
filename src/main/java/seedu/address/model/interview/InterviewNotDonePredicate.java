package seedu.address.model.interview;

import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;

/**
 * Tests that {@code Interview} is not done by checking the {@code isDone} field.
 */
public class InterviewNotDonePredicate implements Predicate<Interview> {
    @Override
    public boolean test(Interview interview) {
        return !interview.isDone();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        return other instanceof InterviewNotDonePredicate;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).toString();
    }

}
