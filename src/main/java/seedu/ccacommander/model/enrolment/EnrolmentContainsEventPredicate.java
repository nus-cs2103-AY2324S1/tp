package seedu.ccacommander.model.enrolment;

import java.util.function.Predicate;

import seedu.ccacommander.commons.util.ToStringBuilder;
import seedu.ccacommander.model.shared.Name;

/**
 * Tests that an {@code Enrolment} contains an {@code Event}'s {@code Name}
 */
public class EnrolmentContainsEventPredicate implements Predicate<Enrolment> {
    private final Name eventName;

    public EnrolmentContainsEventPredicate(Name eventName) {
        this.eventName = eventName;
    }

    @Override
    public boolean test(Enrolment enrolment) {
        return enrolment.getEventName().equals(eventName);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EnrolmentContainsEventPredicate)) {
            return false;
        }

        EnrolmentContainsEventPredicate otherEnrolmentContainsEventPredicate =
                (EnrolmentContainsEventPredicate) other;
        return eventName.equals(otherEnrolmentContainsEventPredicate.eventName);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("Event Name: ", eventName).toString();
    }
}
