package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.TaskBuilder;

public class DateContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("22/10/2023");
        List<String> secondPredicateKeywordList = Arrays.asList("10/10/2023", "22/10/2023");

        DateContainsKeywordsPredicate firstPredicate =
                new DateContainsKeywordsPredicate(firstPredicateKeywordList);
        DateContainsKeywordsPredicate secondPredicate =
                new DateContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        DateContainsKeywordsPredicate firstPredicateCopy =
                new DateContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different task -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_dateContainsKeywords_returnsTrue() {
        // One keyword
        DateContainsKeywordsPredicate predicate =
                new DateContainsKeywordsPredicate(Collections.singletonList("22/10/2023"));
        assertTrue(predicate.test(new TaskBuilder().withDate("22/10/2023").build()));

        // Multiple keywords
        predicate = new DateContainsKeywordsPredicate(Arrays.asList("22/10/2023", "10/10/2023"));
        assertTrue(predicate.test(new TaskBuilder().withDate("22/10/2023").build()));

    }

    @Test
    public void test_dateDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        DateContainsKeywordsPredicate predicate =
                new DateContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new TaskBuilder().withDate("22/10/2023").build()));

        // Non-matching keyword
        predicate = new DateContainsKeywordsPredicate(Arrays.asList("21/10/2023"));
        assertFalse(predicate.test(new TaskBuilder().withDate("22/10/2023").build()));

    }

    @Test
    public void toStringMethod() {
        List<String> keywords = List.of("22/10/2023", "10/10/2023");
        DateContainsKeywordsPredicate predicate = new DateContainsKeywordsPredicate(keywords);

        String expected = DateContainsKeywordsPredicate.class.getCanonicalName()
                + "{date keywords=" + keywords + "}";
        assertEquals(expected, predicate.toString());
    }
}
