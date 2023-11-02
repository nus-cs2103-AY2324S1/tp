package seedu.ccacommander.model.enrolment;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.ccacommander.testutil.TypicalEnrolments.ALICE_AURORA;
import static seedu.ccacommander.testutil.TypicalEnrolments.BENSON_BOXING;
import static seedu.ccacommander.testutil.TypicalMembers.ALICE;
import static seedu.ccacommander.testutil.TypicalMembers.BENSON;

import org.junit.jupiter.api.Test;

public class EnrolmentContainsMemberPredicateTest {
    @Test
    public void test_enrolmentContainsMember_returnsTrue() {
        EnrolmentContainsMemberPredicate predicate = new EnrolmentContainsMemberPredicate(ALICE.getName());

        assertTrue(predicate.test(ALICE_AURORA));
    }
    @Test
    public void test_enrolmentContainsMember_returnsFalse() {
        EnrolmentContainsMemberPredicate predicate = new EnrolmentContainsMemberPredicate(ALICE.getName());

        assertFalse(predicate.test(BENSON_BOXING));
    }
    @Test
    public void equals() {
        EnrolmentContainsMemberPredicate firstPredicate = new EnrolmentContainsMemberPredicate(ALICE.getName());
        EnrolmentContainsMemberPredicate secondPredicate = new EnrolmentContainsMemberPredicate(BENSON.getName());


        // same values -> returns true
        EnrolmentContainsMemberPredicate firsPredicateCopy = new EnrolmentContainsMemberPredicate(ALICE.getName());
        assertTrue(firstPredicate.equals(firsPredicateCopy));

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different type -> returns false
        assertFalse(firstPredicate.equals(5));

        // different enrolment -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

}
