package seedu.address.model.predicate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class IdContainsKeywordsPredicateTest {

    @Test
    public void test_idContainsKeywords_returnsTrue() {
        // One keyword
        IdContainsKeywordsPredicate predicate = new IdContainsKeywordsPredicate(Collections.singletonList("A1234567B"));
        assertTrue(predicate.test(new PersonBuilder().withId("A1234567B").build()));

        // Multiple keywords
        predicate = new IdContainsKeywordsPredicate(Arrays.asList("A1234567B", "A1234568C"));
        assertTrue(predicate.test(new PersonBuilder().withId("A1234567B").build()));

        // Only one matching keyword
        predicate = new IdContainsKeywordsPredicate(Arrays.asList("A1234567B", "5678B"));
        assertTrue(predicate.test(new PersonBuilder().withId("A1234567B").build()));

        // Mixed-case keyword
        predicate = new IdContainsKeywordsPredicate(Arrays.asList("a1234567B"));
        assertTrue(predicate.test(new PersonBuilder().withId("A1234567B").build()));
    }

    @Test
    public void test_idDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        IdContainsKeywordsPredicate predicate = new IdContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new PersonBuilder().withId("A1234567B").build()));

        // Non-matching keyword
        predicate = new IdContainsKeywordsPredicate(Arrays.asList("A1234567C"));
        assertFalse(predicate.test(new PersonBuilder().withId("A1234567B").build()));

        // Keywords match phone, email and address, but does not match name
        predicate = new IdContainsKeywordsPredicate(Arrays.asList("12346", "alice@email.com", "Main", "Street"));
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").withPhone("12346")
                .withEmail("alice@email.com").withId("A1234567B").build()));
    }

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        IdContainsKeywordsPredicate firstPredicate = new IdContainsKeywordsPredicate(firstPredicateKeywordList);
        IdContainsKeywordsPredicate secondPredicate = new IdContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        IdContainsKeywordsPredicate firstPredicateCopy = new IdContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void create() {
        List<String> keywords = List.of("keyword1", "keyword2");
        IdContainsKeywordsPredicate predicate = IdContainsKeywordsPredicate.create(keywords);

        IdContainsKeywordsPredicate expected = new IdContainsKeywordsPredicate(keywords);
        assertEquals(expected, predicate);
    }

    @Test
    public void toStringMethod() {
        List<String> keywords = List.of("keyword1", "keyword2");
        IdContainsKeywordsPredicate predicate = new IdContainsKeywordsPredicate(keywords);

        assertEquals("ID Filter: " + keywords, predicate.toString());
    }
}
