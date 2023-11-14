package seedu.address.model.internshiptask;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.model.internship.role.InternshipRole;
import seedu.address.model.internship.task.InternshipTaskHasSameInternshipRolePredicate;
import seedu.address.testutil.InternshipTaskBuilder;
import seedu.address.testutil.TypicalInternshipRoles;

public class InternshipTaskHasSameInternshipRolePredicateTest {

    @Test
    public void equals() {
        InternshipRole role1 = TypicalInternshipRoles.INTERNSHIP_ROLE_1;
        InternshipRole role2 = TypicalInternshipRoles.INTERNSHIP_ROLE_2;

        InternshipTaskHasSameInternshipRolePredicate firstPredicate =
                new InternshipTaskHasSameInternshipRolePredicate(role1);
        InternshipTaskHasSameInternshipRolePredicate secondPredicate =
                new InternshipTaskHasSameInternshipRolePredicate(role2);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        InternshipTaskHasSameInternshipRolePredicate firstPredicateCopy =
                new InternshipTaskHasSameInternshipRolePredicate(role1);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_taskBelongsToSameRole_returnsTrue() {
        InternshipRole role1 = TypicalInternshipRoles.INTERNSHIP_ROLE_1;

        InternshipTaskHasSameInternshipRolePredicate predicate =
                new InternshipTaskHasSameInternshipRolePredicate(role1);
        assertTrue(predicate.test(new InternshipTaskBuilder().withInternshipRole(role1).build()));
    }

    @Test
    public void test_taskDoesNotBelongToSameRole_returnsFalse() {
        InternshipRole role1 = TypicalInternshipRoles.INTERNSHIP_ROLE_1;
        InternshipRole role2 = TypicalInternshipRoles.INTERNSHIP_ROLE_2;

        InternshipTaskHasSameInternshipRolePredicate predicate =
                new InternshipTaskHasSameInternshipRolePredicate(role1);
        assertFalse(predicate.test(new InternshipTaskBuilder().withInternshipRole(role2).build()));
    }

    @Test
    public void toStringMethod() {
        InternshipRole role1 = TypicalInternshipRoles.INTERNSHIP_ROLE_1;
        InternshipTaskHasSameInternshipRolePredicate predicate =
                new InternshipTaskHasSameInternshipRolePredicate(role1);

        String expected = InternshipTaskHasSameInternshipRolePredicate.class.getCanonicalName()
                + "{role=" + role1 + "}";
        assertEquals(expected, predicate.toString());
    }
}
