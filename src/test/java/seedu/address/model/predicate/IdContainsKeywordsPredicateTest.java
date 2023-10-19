package seedu.address.model.predicate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

public class IdContainsKeywordsPredicateTest {
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
