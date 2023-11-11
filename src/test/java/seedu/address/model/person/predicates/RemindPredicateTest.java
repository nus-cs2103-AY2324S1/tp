package seedu.address.model.person.predicates;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COMPANY_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_POLICY_ISSUE_DATE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_POLICY_NO_AMY;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class RemindPredicateTest {

    @Test
    public void equals() {
        int firstPredicateDay = 0;
        int secondPredicateDay = 30;

        RemindPredicate firstPredicate = new RemindPredicate(firstPredicateDay);
        RemindPredicate secondPredicate = new RemindPredicate(secondPredicateDay);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        RemindPredicate firstPredicateCopy = new RemindPredicate(firstPredicateDay);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    // All tests on the test method are done on the date 12/11/2023, and will be using 30 days as the benchmark.
    // If the tests are going to be done after 12/12/2023, do adjust the expiry dates for testing purposes.
    @Test
    public void test_policyExpiryDateIsWithinUserInputDays_returnsTrue() {
        // Policy expiry date is 30 days from 12/11/2023
        RemindPredicate predicate = new RemindPredicate(30);
        assertTrue(predicate.test(new PersonBuilder().withPolicy(VALID_COMPANY_AMY, VALID_POLICY_NO_AMY,
                VALID_POLICY_ISSUE_DATE_AMY, "11-12-2023").build()));

        // Policy expiry date is 60 days away from 12/11/2023
        predicate = new RemindPredicate(60);
        assertTrue(predicate.test(new PersonBuilder().withPolicy(VALID_COMPANY_AMY, VALID_POLICY_NO_AMY,
                VALID_POLICY_ISSUE_DATE_AMY, "10-01-2024").build()));

        // Policy expiry date is within 60 days away from 12/11/2023
        assertTrue(predicate.test(new PersonBuilder().withPolicy(VALID_COMPANY_AMY, VALID_POLICY_NO_AMY,
                VALID_POLICY_ISSUE_DATE_AMY, "25-12-2023").build()));
    }

    @Test
    public void test_policyExpiryDateIsNotInNext30Days_returnsFalse() {
        // Policy expiry date is over 30 days away from 12/11/2023
        RemindPredicate predicate = new RemindPredicate(30);
        assertFalse(predicate.test(new PersonBuilder().withPolicy(VALID_COMPANY_AMY, VALID_POLICY_NO_AMY,
                VALID_POLICY_ISSUE_DATE_AMY, "12-12-2024").build()));
    }

    @Test
    public void toStringMethod() {
        int days = 30;
        RemindPredicate predicate = new RemindPredicate(days);

        String expected = RemindPredicate.class.getCanonicalName() + "{days=" + days + "}";
        assertEquals(expected, predicate.toString());
    }
}
