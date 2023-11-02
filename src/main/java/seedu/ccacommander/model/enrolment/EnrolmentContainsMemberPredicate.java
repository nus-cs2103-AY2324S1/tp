package seedu.ccacommander.model.enrolment;

import java.util.function.Predicate;

import seedu.ccacommander.commons.util.ToStringBuilder;
import seedu.ccacommander.model.shared.Name;


/**
 * Tests that an {@code Enrolment} contains a {@code Member}'s {@code Name}
 */
public class EnrolmentContainsMemberPredicate implements Predicate<Enrolment> {
    private final Name memberName;

    public EnrolmentContainsMemberPredicate(Name memberName) {
        this.memberName = memberName;
    }

    @Override
    public boolean test(Enrolment enrolment) {
        return enrolment.getMemberName().equals(memberName);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EnrolmentContainsMemberPredicate)) {
            return false;
        }

        EnrolmentContainsMemberPredicate otherEnrolmentContainsMemberPredicate =
                (EnrolmentContainsMemberPredicate) other;
        return memberName.equals(otherEnrolmentContainsMemberPredicate.memberName);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("Member Name: ", memberName).toString();
    }
}
