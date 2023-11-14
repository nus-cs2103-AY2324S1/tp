package seedu.address.model.internship.task;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.internship.role.InternshipRole;


/**
 * Tests that a {@code InternshipTask}'s {@code InternshipRole} is the given internshipRole.
 */
public class InternshipTaskContainsInternshipRolesPredicate implements Predicate<InternshipTask> {
    private final List<InternshipRole> internshipRoles;

    public InternshipTaskContainsInternshipRolesPredicate(List<InternshipRole> internshipRoles) {
        this.internshipRoles = internshipRoles;
    }

    @Override
    public boolean test(InternshipTask internshipTask) {
        return internshipRoles.contains(internshipTask.getInternshipRole());
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof InternshipTaskContainsInternshipRolesPredicate)) {
            return false;
        }

        InternshipTaskContainsInternshipRolesPredicate otherInternshipTaskNameContainsKeywordsPredicate =
                (InternshipTaskContainsInternshipRolesPredicate) other;
        return internshipRoles.equals(otherInternshipTaskNameContainsKeywordsPredicate.internshipRoles);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("internshipRoles", internshipRoles).toString();
    }
}
