package seedu.address.model.person.predicates;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COMPANY_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_POLICY_ISSUE_DATE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_POLICY_NO_AMY;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import seedu.address.model.policy.PolicyDate;
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

    @Test
    public void test_policyExpiryDateIsWithinUserInputDays_returnsTrue() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(PolicyDate.VALIDATION_DATE_FORMAT);

        // Policy expiry date is 30 days away
        RemindPredicate predicate = new RemindPredicate(30);
        assertTrue(predicate.test(new PersonBuilder().withPolicy(VALID_COMPANY_AMY, VALID_POLICY_NO_AMY,
                VALID_POLICY_ISSUE_DATE_AMY, LocalDate.now().plusDays(30).format(formatter)).build()));

        // Policy expiry date is 60 days away
        predicate = new RemindPredicate(60);
        assertTrue(predicate.test(new PersonBuilder().withPolicy(VALID_COMPANY_AMY, VALID_POLICY_NO_AMY,
                VALID_POLICY_ISSUE_DATE_AMY, LocalDate.now().plusDays(60).format(formatter)).build()));

        // Policy expiry date is within 60 days away
        assertTrue(predicate.test(new PersonBuilder().withPolicy(VALID_COMPANY_AMY, VALID_POLICY_NO_AMY,
                VALID_POLICY_ISSUE_DATE_AMY, LocalDate.now().plusDays(15).format(formatter)).build()));
    }

    @Test
    public void test_policyExpiryDateIsNotInNext30Days_returnsFalse() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(PolicyDate.VALIDATION_DATE_FORMAT);

        // Policy expiry date is more than 30 days away
        RemindPredicate predicate = new RemindPredicate(30);
        assertFalse(predicate.test(new PersonBuilder().withPolicy(VALID_COMPANY_AMY, VALID_POLICY_NO_AMY,
                VALID_POLICY_ISSUE_DATE_AMY, LocalDate.now().plusDays(50).format(formatter)).build()));
    }

    @Test
    public void test_policyExpiryDatePassed_returnsFalse() {
        // Policy expiry date is in the past
        RemindPredicate predicate = new RemindPredicate(30);
        assertFalse(predicate.test(new PersonBuilder().withPolicy(VALID_COMPANY_AMY, VALID_POLICY_NO_AMY,
                VALID_POLICY_ISSUE_DATE_AMY, "10-10-2020").build()));
    }

    @Test
    public void test_policyExpiryDateIsDefault_returnsFalse() {
        // Policy expiry date is the default parameter
        RemindPredicate predicate = new RemindPredicate(30);
        assertFalse(predicate.test(new PersonBuilder().withPolicy(VALID_COMPANY_AMY, VALID_POLICY_NO_AMY,
                VALID_POLICY_ISSUE_DATE_AMY, PolicyDate.DEFAULT_VALUE).build()));
    }

    @Test
    public void toStringMethod() {
        int days = 30;

        RemindPredicate predicate = new RemindPredicate(days);

        String expected = RemindPredicate.class.getCanonicalName() + "{days=" + days + "}";
        assertEquals(expected, predicate.toString());
    }
}
