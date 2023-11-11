package seedu.address.model.person.predicates;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class EmailContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        String firstPredicateKeyword = "first";
        String secondPredicateKeyword = "first second";

        EmailContainsKeywordsPredicate firstPredicate = new EmailContainsKeywordsPredicate(firstPredicateKeyword);
        EmailContainsKeywordsPredicate secondPredicate = new EmailContainsKeywordsPredicate(secondPredicateKeyword);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        EmailContainsKeywordsPredicate firstPredicateCopy = new EmailContainsKeywordsPredicate(firstPredicateKeyword);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_emailContainsKeywords_returnsTrue() {
        // One keyword
        EmailContainsKeywordsPredicate predicate = new EmailContainsKeywordsPredicate("amy@example.com");
        assertTrue(predicate.test(new PersonBuilder().withEmail("amy@example.com").build()));

        // Trailing and Leading white spaces
        predicate = new EmailContainsKeywordsPredicate("   amy@example.com    ");
        assertTrue(predicate.test(new PersonBuilder().withEmail("amy@example.com").build()));

        // Mixed-case keywords
        predicate = new EmailContainsKeywordsPredicate("AmY@ExAmPle.coM");
        assertTrue(predicate.test(new PersonBuilder().withEmail("amy@example.com").build()));

        // Partial keyword
        predicate = new EmailContainsKeywordsPredicate("amy");
        assertTrue(predicate.test(new PersonBuilder().withEmail("amy@example.com").build()));
    }

    @Test
    public void test_emailDoesNotContainKeywords_returnsFalse() {
        // Non-matching keyword
        EmailContainsKeywordsPredicate predicate = new EmailContainsKeywordsPredicate("bob");
        assertFalse(predicate.test(new PersonBuilder().withEmail("amy@example.com").build()));

        // Keyword including white spaces
        predicate = new EmailContainsKeywordsPredicate("amy @example. com");
        assertFalse(predicate.test(new PersonBuilder().withEmail("amy@example.com").build()));

        // Keyword do not match exact
        predicate = new EmailContainsKeywordsPredicate("amykim@example.com");
        assertFalse(predicate.test(new PersonBuilder().withEmail("amy@example.com").build()));
    }

    @Test
    public void toStringMethod() {
        String keyword = "keyword1";
        EmailContainsKeywordsPredicate predicate = new EmailContainsKeywordsPredicate(keyword);

        String expected = EmailContainsKeywordsPredicate.class.getCanonicalName() + "{keywords=[" + keyword + "]}";
        assertEquals(expected, predicate.toString());
    }
}
