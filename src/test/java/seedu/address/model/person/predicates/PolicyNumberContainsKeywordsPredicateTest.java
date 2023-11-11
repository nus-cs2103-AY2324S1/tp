package seedu.address.model.person.predicates;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COMPANY_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_POLICY_EXPIRY_DATE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_POLICY_ISSUE_DATE_AMY;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class PolicyNumberContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        String firstPredicateKeyword = "first";
        String secondPredicateKeyword = "first second";

        PolicyNumberContainsKeywordsPredicate firstPredicate =
                new PolicyNumberContainsKeywordsPredicate(firstPredicateKeyword);
        PolicyNumberContainsKeywordsPredicate secondPredicate =
                new PolicyNumberContainsKeywordsPredicate(secondPredicateKeyword);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        PolicyNumberContainsKeywordsPredicate firstPredicateCopy =
                new PolicyNumberContainsKeywordsPredicate(firstPredicateKeyword);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_policyNumberContainsKeywords_returnsTrue() {
        // One keyword
        PolicyNumberContainsKeywordsPredicate predicate =
                new PolicyNumberContainsKeywordsPredicate("FWD1123");
        assertTrue(predicate.test(new PersonBuilder().withPolicy(VALID_COMPANY_AMY,
                "FWD1123", VALID_POLICY_ISSUE_DATE_AMY, VALID_POLICY_EXPIRY_DATE_AMY).build()));

        // Trailing and Leading white spaces
        predicate = new PolicyNumberContainsKeywordsPredicate("   FWD1123    ");
        assertTrue(predicate.test(new PersonBuilder().withPolicy(VALID_COMPANY_AMY,
                "FWD1123", VALID_POLICY_ISSUE_DATE_AMY, VALID_POLICY_EXPIRY_DATE_AMY).build()));

        // Mixed-case keywords
        predicate = new PolicyNumberContainsKeywordsPredicate("fwD1123");
        assertTrue(predicate.test(new PersonBuilder().withPolicy(VALID_COMPANY_AMY,
                "FWD1123", VALID_POLICY_ISSUE_DATE_AMY, VALID_POLICY_EXPIRY_DATE_AMY).build()));

        // Partial keyword
        predicate = new PolicyNumberContainsKeywordsPredicate("FW");
        assertTrue(predicate.test(new PersonBuilder().withPolicy(VALID_COMPANY_AMY,
                "FWD1123", VALID_POLICY_ISSUE_DATE_AMY, VALID_POLICY_EXPIRY_DATE_AMY).build()));
    }

    @Test
    public void test_policyNumberDoesNotContainKeywords_returnsFalse() {
        // Non-matching keyword
        PolicyNumberContainsKeywordsPredicate predicate = new PolicyNumberContainsKeywordsPredicate("ABC999");
        assertFalse(predicate.test(new PersonBuilder().withPolicy(VALID_COMPANY_AMY,
                "FWD1123", VALID_POLICY_ISSUE_DATE_AMY, VALID_POLICY_EXPIRY_DATE_AMY).build()));

        // Keyword only matches word that is separated by white space
        predicate = new PolicyNumberContainsKeywordsPredicate("FWD 11 23");
        assertFalse(predicate.test(new PersonBuilder().withPolicy(VALID_COMPANY_AMY,
                "FWD1123", VALID_POLICY_ISSUE_DATE_AMY, VALID_POLICY_EXPIRY_DATE_AMY).build()));

        // Keyword do not match exact
        predicate = new PolicyNumberContainsKeywordsPredicate("FWD11234");
        assertFalse(predicate.test(new PersonBuilder().withPolicy(VALID_COMPANY_AMY,
                "FWD1123", VALID_POLICY_ISSUE_DATE_AMY, VALID_POLICY_EXPIRY_DATE_AMY).build()));
    }

    @Test
    public void toStringMethod() {
        String keyword = "keyword1";
        PolicyNumberContainsKeywordsPredicate predicate = new PolicyNumberContainsKeywordsPredicate(keyword);

        String expected = PolicyNumberContainsKeywordsPredicate.class.getCanonicalName() + "{keywords=["
                + keyword + "]}";
        assertEquals(expected, predicate.toString());
    }
}
