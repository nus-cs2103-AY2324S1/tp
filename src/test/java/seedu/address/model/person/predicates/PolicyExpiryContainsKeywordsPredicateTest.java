package seedu.address.model.person.predicates;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COMPANY_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_POLICY_ISSUE_DATE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_POLICY_NO_AMY;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class PolicyExpiryContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        String firstPredicateKeyword = "first";
        String secondPredicateKeyword = "first second";

        PolicyExpiryContainsKeywordsPredicate firstPredicate =
                new PolicyExpiryContainsKeywordsPredicate(firstPredicateKeyword);
        PolicyExpiryContainsKeywordsPredicate secondPredicate =
                new PolicyExpiryContainsKeywordsPredicate(secondPredicateKeyword);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        PolicyExpiryContainsKeywordsPredicate firstPredicateCopy =
                new PolicyExpiryContainsKeywordsPredicate(firstPredicateKeyword);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_policyExpiryContainsKeywords_returnsTrue() {
        // One keyword
        PolicyExpiryContainsKeywordsPredicate predicate =
                new PolicyExpiryContainsKeywordsPredicate("11-11-2023");
        assertTrue(predicate.test(new PersonBuilder().withPolicy(VALID_COMPANY_AMY,
                VALID_POLICY_NO_AMY, VALID_POLICY_ISSUE_DATE_AMY, "11-11-2023").build()));

        // Trailing and Leading white spaces
        predicate = new PolicyExpiryContainsKeywordsPredicate("   11-11-2023    ");
        assertTrue(predicate.test(new PersonBuilder().withPolicy(VALID_COMPANY_AMY,
                VALID_POLICY_NO_AMY, VALID_POLICY_ISSUE_DATE_AMY, "11-11-2023").build()));

        // Partial keyword
        predicate = new PolicyExpiryContainsKeywordsPredicate("11-11");
        assertTrue(predicate.test(new PersonBuilder().withPolicy(VALID_COMPANY_AMY,
                VALID_POLICY_NO_AMY, VALID_POLICY_ISSUE_DATE_AMY, "11-11-2023").build()));
    }

    @Test
    public void test_policyExpiryDoesNotContainKeywords_returnsFalse() {
        // Non-matching keyword
        PolicyExpiryContainsKeywordsPredicate predicate = new PolicyExpiryContainsKeywordsPredicate("12-12-2022");
        assertFalse(predicate.test(new PersonBuilder().withPolicy(VALID_COMPANY_AMY,
                VALID_POLICY_NO_AMY, VALID_POLICY_ISSUE_DATE_AMY, "11-11-2023").build()));

        // Keyword only matches word that is separated by white space
        predicate = new PolicyExpiryContainsKeywordsPredicate("11 - 11");
        assertFalse(predicate.test(new PersonBuilder().withPolicy(VALID_COMPANY_AMY,
                VALID_POLICY_NO_AMY, VALID_POLICY_ISSUE_DATE_AMY, "11-11-2023").build()));

        // Keyword do not match exact
        predicate = new PolicyExpiryContainsKeywordsPredicate("11-11-2022");
        assertFalse(predicate.test(new PersonBuilder().withPolicy(VALID_COMPANY_AMY,
                VALID_POLICY_NO_AMY, VALID_POLICY_ISSUE_DATE_AMY, "11-11-2023").build()));
    }

    @Test
    public void toStringMethod() {
        String keyword = "keyword1";
        PolicyExpiryContainsKeywordsPredicate predicate = new PolicyExpiryContainsKeywordsPredicate(keyword);

        String expected = PolicyExpiryContainsKeywordsPredicate.class.getCanonicalName() + "{keywords=["
                + keyword + "]}";
        assertEquals(expected, predicate.toString());
    }
}
