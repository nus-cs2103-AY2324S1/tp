package seedu.address.model.schedule;

import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.person.Person;

/**
 * Tests that a {@code Schedule}'s {@code Status} matches any of the keywords given.
 */
public class StatusPredicate implements Predicate<Schedule> {
    private final Status status;
    private final Person tutor;

    /**
     * Default constructor for StatusPredicate.
     *
     * @param status  status value
     * @param tutor   tutor
     */
    public StatusPredicate(Status status, Person tutor) {
        this.status = status;
        this.tutor = tutor;
    }

    @Override
    public boolean test(Schedule schedule) {
        if (tutor != null) {
            return status.equals(schedule.getStatus())
                && schedule.getTutor().isSamePerson(tutor);
        } else {
            return status.equals(schedule.getStatus());
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof StatusPredicate)) {
            return false;
        }

        StatusPredicate otherStatusPredicate =
            (StatusPredicate) other;
        return status.equals(otherStatusPredicate.status);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("status", status).toString();
    }

}
