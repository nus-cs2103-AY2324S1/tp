package seedu.address.model.person.predicates;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COMPANY_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_POLICY_EXPIRY_DATE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_POLICY_NO_AMY;

import org.junit.jupiter.api.Test;

import seedu.address.model.policy.PolicyDate;
import seedu.address.testutil.PersonBuilder;

public class PolicyIssueContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        String firstPredicateKeyword = "first";
        String secondPredicateKeyword = "first second";

        PolicyIssueContainsKeywordsPredicate firstPredicate =
                new PolicyIssueContainsKeywordsPredicate(firstPredicateKeyword);
        PolicyIssueContainsKeywordsPredicate secondPredicate =
                new PolicyIssueContainsKeywordsPredicate(secondPredicateKeyword);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        PolicyIssueContainsKeywordsPredicate firstPredicateCopy =
                new PolicyIssueContainsKeywordsPredicate(firstPredicateKeyword);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_policyIssueContainsKeywords_returnsTrue() {
        // One keyword
        PolicyIssueContainsKeywordsPredicate predicate =
                new PolicyIssueContainsKeywordsPredicate("11-11-2023");
        assertTrue(predicate.test(new PersonBuilder().withPolicy(VALID_COMPANY_AMY,
                VALID_POLICY_NO_AMY, "11-11-2023", VALID_POLICY_EXPIRY_DATE_AMY).build()));

        // Trailing and Leading white spaces
        predicate = new PolicyIssueContainsKeywordsPredicate("   11-11-2023    ");
        assertTrue(predicate.test(new PersonBuilder().withPolicy(VALID_COMPANY_AMY,
                VALID_POLICY_NO_AMY, "11-11-2023", VALID_POLICY_EXPIRY_DATE_AMY).build()));

        // Partial keyword
        predicate = new PolicyIssueContainsKeywordsPredicate("11-11");
        assertTrue(predicate.test(new PersonBuilder().withPolicy(VALID_COMPANY_AMY,
                VALID_POLICY_NO_AMY, "11-11-2023", VALID_POLICY_EXPIRY_DATE_AMY).build()));
    }

    @Test
    public void test_policyIssueDoesNotContainKeywords_returnsFalse() {
        // Non-matching keyword
        PolicyIssueContainsKeywordsPredicate predicate = new PolicyIssueContainsKeywordsPredicate("12-12-2022");
        assertFalse(predicate.test(new PersonBuilder().withPolicy(VALID_COMPANY_AMY,
                VALID_POLICY_NO_AMY, "11-11-2023", VALID_POLICY_EXPIRY_DATE_AMY).build()));

        // Keyword only matches word that is separated by white space
        predicate = new PolicyIssueContainsKeywordsPredicate("11 - 11");
        assertFalse(predicate.test(new PersonBuilder().withPolicy(VALID_COMPANY_AMY,
                VALID_POLICY_NO_AMY, "11-11-2023", VALID_POLICY_EXPIRY_DATE_AMY).build()));

        // Keyword do not match exact
        predicate = new PolicyIssueContainsKeywordsPredicate("11-11-2022");
        assertFalse(predicate.test(new PersonBuilder().withPolicy(VALID_COMPANY_AMY,
                VALID_POLICY_NO_AMY, "11-11-2023", VALID_POLICY_EXPIRY_DATE_AMY).build()));
    }

    @Test
    public void test_policyIssueIsDefault_returnsFalse() {
        // Policy issue date is the default parameter
        PolicyIssueContainsKeywordsPredicate predicate =
                new PolicyIssueContainsKeywordsPredicate("11-11-2023");
        assertFalse(predicate.test(new PersonBuilder().withPolicy(VALID_COMPANY_AMY, VALID_POLICY_NO_AMY,
                PolicyDate.DEFAULT_VALUE, VALID_POLICY_EXPIRY_DATE_AMY).build()));
    }

    @Test
    public void toStringMethod() {
        String keyword = "keyword1";
        PolicyIssueContainsKeywordsPredicate predicate = new PolicyIssueContainsKeywordsPredicate(keyword);

        String expected = PolicyIssueContainsKeywordsPredicate.class.getCanonicalName() + "{keywords=["
                + keyword + "]}";
        assertEquals(expected, predicate.toString());
    }
}
