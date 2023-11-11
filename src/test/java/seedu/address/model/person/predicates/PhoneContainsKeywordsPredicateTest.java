package seedu.address.model.person.predicates;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class PhoneContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        String firstPredicateKeyword = "first";
        String secondPredicateKeyword = "first second";

        PhoneContainsKeywordsPredicate firstPredicate = new PhoneContainsKeywordsPredicate(firstPredicateKeyword);
        PhoneContainsKeywordsPredicate secondPredicate = new PhoneContainsKeywordsPredicate(secondPredicateKeyword);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        PhoneContainsKeywordsPredicate firstPredicateCopy = new PhoneContainsKeywordsPredicate(firstPredicateKeyword);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_phoneContainsKeywords_returnsTrue() {
        // One keyword
        PhoneContainsKeywordsPredicate predicate = new PhoneContainsKeywordsPredicate("98765432");
        assertTrue(predicate.test(new PersonBuilder().withPhone("98765432").build()));

        // Trailing and Leading white spaces
        predicate = new PhoneContainsKeywordsPredicate("   98765432    ");
        assertTrue(predicate.test(new PersonBuilder().withPhone("98765432").build()));

        // Partial keyword
        predicate = new PhoneContainsKeywordsPredicate("9876");
        assertTrue(predicate.test(new PersonBuilder().withPhone("98765432").build()));
    }

    @Test
    public void test_phoneDoesNotContainKeywords_returnsFalse() {
        // Non-matching keyword
        PhoneContainsKeywordsPredicate predicate = new PhoneContainsKeywordsPredicate("123");
        assertFalse(predicate.test(new PersonBuilder().withPhone("98765432").build()));

        // Keyword including white spaces
        predicate = new PhoneContainsKeywordsPredicate("987 654 32");
        assertFalse(predicate.test(new PersonBuilder().withPhone("98765432").build()));

        // Keyword do not match exact
        predicate = new PhoneContainsKeywordsPredicate("987654321");
        assertFalse(predicate.test(new PersonBuilder().withPhone("98765432").build()));
    }

    @Test
    public void toStringMethod() {
        String keyword = "keyword1";
        PhoneContainsKeywordsPredicate predicate = new PhoneContainsKeywordsPredicate(keyword);

        String expected = PhoneContainsKeywordsPredicate.class.getCanonicalName() + "{keywords=[" + keyword + "]}";
        assertEquals(expected, predicate.toString());
    }
}
