package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.TaskBuilder;

public class TaskPriorityContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("low");
        List<String> secondPredicateKeywordList = Arrays.asList("low", "medium");

        TaskPriorityContainsKeywordsPredicate firstPredicate =
                new TaskPriorityContainsKeywordsPredicate(firstPredicateKeywordList);
        TaskPriorityContainsKeywordsPredicate secondPredicate =
                new TaskPriorityContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        TaskPriorityContainsKeywordsPredicate firstPredicateCopy =
                new TaskPriorityContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different task -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_taskPriorityContainsKeywords_returnsTrue() {
        // One keyword
        TaskPriorityContainsKeywordsPredicate predicate =
                new TaskPriorityContainsKeywordsPredicate(Collections.singletonList("low"));
        assertTrue(predicate.test(new TaskBuilder().withPriority("low").build()));

        // Multiple keywords
        predicate = new TaskPriorityContainsKeywordsPredicate(Arrays.asList("low", "high"));
        assertTrue(predicate.test(new TaskBuilder().withPriority("high").build()));

        // Mixed-case keywords
        predicate = new TaskPriorityContainsKeywordsPredicate(Collections.singletonList("LOW"));
        assertTrue(predicate.test(new TaskBuilder().withPriority("low").build()));
    }

    @Test
    public void test_taskPriorityDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        TaskPriorityContainsKeywordsPredicate predicate =
                new TaskPriorityContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new TaskBuilder().withPriority("low").build()));

        // Non-matching keyword
        predicate = new TaskPriorityContainsKeywordsPredicate(Arrays.asList("medium"));
        assertFalse(predicate.test(new TaskBuilder().withPriority("low").build()));

    }

    @Test
    public void toStringMethod() {
        List<String> keywords = List.of("keyword1", "keyword2");
        TaskPriorityContainsKeywordsPredicate predicate = new TaskPriorityContainsKeywordsPredicate(keywords);

        String expected = TaskPriorityContainsKeywordsPredicate.class.getCanonicalName()
                + "{priority keywords=" + keywords + "}";
        assertEquals(expected, predicate.toString());
    }
}
