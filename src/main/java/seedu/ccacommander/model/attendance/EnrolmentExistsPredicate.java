package seedu.ccacommander.model.attendance;

import java.util.function.Predicate;

import seedu.ccacommander.commons.util.ToStringBuilder;

/**
 * Tests that a {@code Enrolment} is the same as the {@code Enrolment} given.
 */
public class EnrolmentExistsPredicate implements Predicate<Attendance> {
    private final Attendance enrolmentToCheck;

    public EnrolmentExistsPredicate(Attendance enrolmentToCheck) {
        this.enrolmentToCheck = enrolmentToCheck;
    }

    @Override
    public boolean test(Attendance enrolment) {
        return enrolmentToCheck.isSameAttendance(enrolment);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EnrolmentExistsPredicate)) {
            return false;
        }

        EnrolmentExistsPredicate otherEventNameContainsKeywordsPredicate =
                (EnrolmentExistsPredicate) other;
        return enrolmentToCheck.equals(otherEventNameContainsKeywordsPredicate.enrolmentToCheck);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("enrolmentToCheck", enrolmentToCheck).toString();
    }
}
