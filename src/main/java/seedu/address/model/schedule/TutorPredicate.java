package seedu.address.model.schedule;

import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.person.Person;

/**
 * Tests that a {@code Person} matches any of the tutors given.
 */
public class TutorPredicate implements Predicate<Schedule> {
    private final Person tutor;

    public TutorPredicate(Person tutor) {
        this.tutor = tutor;
    }

    @Override
    public boolean test(Schedule schedule) {
        return schedule.getTutor().isSamePerson(tutor);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof TutorPredicate)) {
            return false;
        }

        TutorPredicate otherTutorPredicate =
            (TutorPredicate) other;
        return tutor.equals(otherTutorPredicate.tutor);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("tutor", tutor).toString();
    }

}
