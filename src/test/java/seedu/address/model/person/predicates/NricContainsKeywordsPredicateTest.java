package seedu.address.model.person.predicates;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class NricContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        String firstPredicateKeyword = "first";
        String secondPredicateKeyword = "first second";

        NricContainsKeywordsPredicate firstPredicate = new NricContainsKeywordsPredicate(firstPredicateKeyword);
        NricContainsKeywordsPredicate secondPredicate = new NricContainsKeywordsPredicate(secondPredicateKeyword);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        NricContainsKeywordsPredicate firstPredicateCopy = new NricContainsKeywordsPredicate(firstPredicateKeyword);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_nricContainsKeywords_returnsTrue() {
        // One keyword
        NricContainsKeywordsPredicate predicate = new NricContainsKeywordsPredicate("987A");
        assertTrue(predicate.test(new PersonBuilder().withNric("987A").build()));

        // Trailing and Leading white spaces
        predicate = new NricContainsKeywordsPredicate("   987A    ");
        assertTrue(predicate.test(new PersonBuilder().withNric("987A").build()));

        // Mixed-case keywords
        predicate = new NricContainsKeywordsPredicate("987a");
        assertTrue(predicate.test(new PersonBuilder().withNric("987A").build()));

        // Partial keyword
        predicate = new NricContainsKeywordsPredicate("98");
        assertTrue(predicate.test(new PersonBuilder().withNric("987A").build()));
    }

    @Test
    public void test_nricDoesNotContainKeywords_returnsFalse() {
        // Non-matching keyword
        NricContainsKeywordsPredicate predicate = new NricContainsKeywordsPredicate("123");
        assertFalse(predicate.test(new PersonBuilder().withNric("987A").build()));

        // Keyword including white spaces
        predicate = new NricContainsKeywordsPredicate("98 7A");
        assertFalse(predicate.test(new PersonBuilder().withNric("987A").build()));

        // Keyword do not match exact
        predicate = new NricContainsKeywordsPredicate("987AB");
        assertFalse(predicate.test(new PersonBuilder().withNric("987A").build()));
    }

    @Test
    public void toStringMethod() {
        String keyword = "keyword1";
        NricContainsKeywordsPredicate predicate = new NricContainsKeywordsPredicate(keyword);

        String expected = NricContainsKeywordsPredicate.class.getCanonicalName() + "{keywords=[" + keyword + "]}";
        assertEquals(expected, predicate.toString());
    }
}
