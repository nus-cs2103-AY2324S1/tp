package seedu.address.model.internship.task;

import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.internship.role.InternshipRole;


/**
 * Tests that a {@code InternshipTask} has the same {@InternshipRole} given.
 */

public class InternshipTaskHasSameInternshipRolePredicate implements Predicate<InternshipTask> {
    private final InternshipRole role;

    public InternshipTaskHasSameInternshipRolePredicate(InternshipRole role) {
        this.role = role;
    }

    @Override
    public boolean test(InternshipTask task) {
        return task.getInternshipRole().equals(role);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof InternshipTaskHasSameInternshipRolePredicate)) {
            return false;
        }

        InternshipTaskHasSameInternshipRolePredicate otherInternshipTaskHasSameInternshipRolePredicate =
                (InternshipTaskHasSameInternshipRolePredicate) other;
        return role.equals(otherInternshipTaskHasSameInternshipRolePredicate.role);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("role", role)
                .toString();
    }
}
