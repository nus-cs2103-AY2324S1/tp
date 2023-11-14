package seedu.address.model.interview;

import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;

/**
 * Tests that {@code Interview} is done by checking the {@code isDone} field.
 */
public class InterviewIsDonePredicate implements Predicate<Interview> {
    @Override
    public boolean test(Interview interview) {
        return interview.isDone();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        return other instanceof InterviewIsDonePredicate;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).toString();
    }

}
