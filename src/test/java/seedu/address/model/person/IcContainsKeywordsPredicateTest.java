package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class IcContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        String firstPredicateKeyword = "T1234567Q";
        String secondPredicateKeyword = "S1234567Q";

        IcContainsKeywordsPredicate firstPredicate = new IcContainsKeywordsPredicate(firstPredicateKeyword);
        IcContainsKeywordsPredicate secondPredicate = new IcContainsKeywordsPredicate(secondPredicateKeyword);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        IcContainsKeywordsPredicate firstPredicateCopy = new IcContainsKeywordsPredicate(firstPredicateKeyword);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_icContainsKeywords_returnsTrue() {
        // Test with a person having matching IC
        IcContainsKeywordsPredicate icPredicate = new IcContainsKeywordsPredicate("T1234567G");
        assertTrue(icPredicate.test(new PersonBuilder().withIc("T1234567G").build()));
    }

    @Test
    public void test_icDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        IcContainsKeywordsPredicate predicate = new IcContainsKeywordsPredicate(" ");
        assertFalse(predicate.test(new PersonBuilder().withIc("T1234567Q").build()));

        // Non-matching keyword
        predicate = new IcContainsKeywordsPredicate("S0001004Q");
        assertFalse(predicate.test(new PersonBuilder().withIc("T1123876Q").build()));
    }

    @Test
    public void toStringMethod() {
        String keyword = "keyword1";
        IcContainsKeywordsPredicate predicate = new IcContainsKeywordsPredicate(keyword);

        String expected = IcContainsKeywordsPredicate.class.getCanonicalName() + "{keywords=" + keyword + "}";
        assertEquals(expected, predicate.toString());
    }
}
