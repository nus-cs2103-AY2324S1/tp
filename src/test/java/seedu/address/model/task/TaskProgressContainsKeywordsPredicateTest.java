package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.TaskBuilder;

public class TaskProgressContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("not_started");
        List<String> secondPredicateKeywordList = Arrays.asList("not_started", "pending");

        TaskProgressContainsKeywordsPredicate firstPredicate =
                new TaskProgressContainsKeywordsPredicate(firstPredicateKeywordList);
        TaskProgressContainsKeywordsPredicate secondPredicate =
                new TaskProgressContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        TaskProgressContainsKeywordsPredicate firstPredicateCopy =
                new TaskProgressContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different task -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_taskProgressContainsKeywords_returnsTrue() {
        // One keyword
        TaskProgressContainsKeywordsPredicate predicate =
                new TaskProgressContainsKeywordsPredicate(Collections.singletonList("pending"));
        assertTrue(predicate.test(new TaskBuilder().withProgress("pending").build()));

        // Multiple keywords
        predicate = new TaskProgressContainsKeywordsPredicate(Arrays.asList("pending", "done"));
        assertTrue(predicate.test(new TaskBuilder().withProgress("done").build()));

        // Mixed-case keywords
        predicate = new TaskProgressContainsKeywordsPredicate(Collections.singletonList("PENDING"));
        assertTrue(predicate.test(new TaskBuilder().withProgress("pending").build()));
    }

    @Test
    public void test_taskProgressDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        TaskProgressContainsKeywordsPredicate predicate =
                new TaskProgressContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new TaskBuilder().withProgress("pending").build()));

        // Non-matching keyword
        predicate = new TaskProgressContainsKeywordsPredicate(Arrays.asList("not_started"));
        assertFalse(predicate.test(new TaskBuilder().withProgress("pending").build()));

    }

    @Test
    public void toStringMethod() {
        List<String> keywords = List.of("keyword1", "keyword2");
        TaskProgressContainsKeywordsPredicate predicate = new TaskProgressContainsKeywordsPredicate(keywords);

        String expected = TaskProgressContainsKeywordsPredicate.class.getCanonicalName()
                + "{progress keywords=" + keywords + "}";
        assertEquals(expected, predicate.toString());
    }
}
