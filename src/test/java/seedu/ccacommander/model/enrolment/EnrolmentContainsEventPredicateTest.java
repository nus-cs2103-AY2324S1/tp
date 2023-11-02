package seedu.ccacommander.model.enrolment;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.ccacommander.testutil.TypicalEnrolments.ALICE_AURORA;
import static seedu.ccacommander.testutil.TypicalEnrolments.BENSON_BOXING;
import static seedu.ccacommander.testutil.TypicalEvents.AURORA_BOREALIS;
import static seedu.ccacommander.testutil.TypicalEvents.BOXING_DAY;

import org.junit.jupiter.api.Test;

public class EnrolmentContainsEventPredicateTest {
    @Test
    public void test_enrolmentContainsEvent_returnsTrue() {
        EnrolmentContainsEventPredicate predicate = new EnrolmentContainsEventPredicate(AURORA_BOREALIS.getName());

        assertTrue(predicate.test(ALICE_AURORA));
    }
    @Test
    public void test_enrolmentContainsEvent_returnsFalse() {
        EnrolmentContainsEventPredicate predicate = new EnrolmentContainsEventPredicate(AURORA_BOREALIS.getName());

        assertFalse(predicate.test(BENSON_BOXING));
    }
    @Test
    public void equals() {
        EnrolmentContainsEventPredicate firstPredicate = new EnrolmentContainsEventPredicate(AURORA_BOREALIS.getName());
        EnrolmentContainsEventPredicate secondPredicate = new EnrolmentContainsEventPredicate(BOXING_DAY.getName());


        // same values -> returns true
        EnrolmentContainsEventPredicate firsPredicateCopy = new
                EnrolmentContainsEventPredicate(AURORA_BOREALIS.getName());
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
