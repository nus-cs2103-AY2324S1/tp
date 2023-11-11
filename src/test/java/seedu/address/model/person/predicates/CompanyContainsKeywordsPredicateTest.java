package seedu.address.model.person.predicates;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_POLICY_EXPIRY_DATE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_POLICY_ISSUE_DATE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_POLICY_NO_AMY;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class CompanyContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        String firstPredicateKeyword = "first";
        String secondPredicateKeyword = "first second";

        CompanyContainsKeywordsPredicate firstPredicate = new CompanyContainsKeywordsPredicate(firstPredicateKeyword);
        CompanyContainsKeywordsPredicate secondPredicate = new CompanyContainsKeywordsPredicate(secondPredicateKeyword);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        CompanyContainsKeywordsPredicate firstPredicateCopy =
                new CompanyContainsKeywordsPredicate(firstPredicateKeyword);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_companyContainsKeywords_returnsTrue() {
        // One keyword
        CompanyContainsKeywordsPredicate predicate =
                new CompanyContainsKeywordsPredicate("FWD Insurance");
        assertTrue(predicate.test(new PersonBuilder().withPolicy("FWD Insurance",
                VALID_POLICY_NO_AMY, VALID_POLICY_ISSUE_DATE_AMY, VALID_POLICY_EXPIRY_DATE_AMY).build()));

        // Trailing and Leading white spaces
        predicate = new CompanyContainsKeywordsPredicate("   FWD Insurance    ");
        assertTrue(predicate.test(new PersonBuilder().withPolicy("FWD Insurance",
                VALID_POLICY_NO_AMY, VALID_POLICY_ISSUE_DATE_AMY, VALID_POLICY_EXPIRY_DATE_AMY).build()));

        // Mixed-case keywords
        predicate = new CompanyContainsKeywordsPredicate("fWd InsURanCe");
        assertTrue(predicate.test(new PersonBuilder().withPolicy("FWD Insurance",
                VALID_POLICY_NO_AMY, VALID_POLICY_ISSUE_DATE_AMY, VALID_POLICY_EXPIRY_DATE_AMY).build()));

        // Partial keyword
        predicate = new CompanyContainsKeywordsPredicate("FWD");
        assertTrue(predicate.test(new PersonBuilder().withPolicy("FWD Insurance",
                VALID_POLICY_NO_AMY, VALID_POLICY_ISSUE_DATE_AMY, VALID_POLICY_EXPIRY_DATE_AMY).build()));
    }

    @Test
    public void test_companyDoesNotContainKeywords_returnsFalse() {
        // Non-matching keyword
        CompanyContainsKeywordsPredicate predicate = new CompanyContainsKeywordsPredicate("ABC");
        assertFalse(predicate.test(new PersonBuilder().withPolicy("FWD Insurance",
                VALID_POLICY_NO_AMY, VALID_POLICY_ISSUE_DATE_AMY, VALID_POLICY_EXPIRY_DATE_AMY).build()));

        // Keyword only matches word that is separated by white space
        predicate = new CompanyContainsKeywordsPredicate("ABC Insurance");
        assertFalse(predicate.test(new PersonBuilder().withPolicy("ABCInsurance",
                VALID_POLICY_NO_AMY, VALID_POLICY_ISSUE_DATE_AMY, VALID_POLICY_EXPIRY_DATE_AMY).build()));

        // Keyword do not match exact
        predicate = new CompanyContainsKeywordsPredicate("FWD Insurance 123");
        assertFalse(predicate.test(new PersonBuilder().withPolicy("FWD Insurance",
                VALID_POLICY_NO_AMY, VALID_POLICY_ISSUE_DATE_AMY, VALID_POLICY_EXPIRY_DATE_AMY).build()));
    }

    @Test
    public void toStringMethod() {
        String keyword = "keyword1";
        CompanyContainsKeywordsPredicate predicate = new CompanyContainsKeywordsPredicate(keyword);

        String expected = CompanyContainsKeywordsPredicate.class.getCanonicalName() + "{keywords=[" + keyword + "]}";
        assertEquals(expected, predicate.toString());
    }
}
