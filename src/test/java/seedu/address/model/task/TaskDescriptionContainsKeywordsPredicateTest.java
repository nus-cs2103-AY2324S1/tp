package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.TaskBuilder;

public class TaskDescriptionContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        TaskDescriptionContainsKeywordsPredicate firstPredicate =
                new TaskDescriptionContainsKeywordsPredicate(firstPredicateKeywordList);
        TaskDescriptionContainsKeywordsPredicate secondPredicate =
                new TaskDescriptionContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        TaskDescriptionContainsKeywordsPredicate firstPredicateCopy =
                new TaskDescriptionContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different task -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_taskDescriptionContainsKeywords_returnsTrue() {
        // One keyword
        TaskDescriptionContainsKeywordsPredicate predicate =
                new TaskDescriptionContainsKeywordsPredicate(Collections.singletonList("cs2103t"));
        assertTrue(predicate.test(new TaskBuilder().withDescription("do cs2103t do cs2100").build()));

        // Multiple keywords
        predicate = new TaskDescriptionContainsKeywordsPredicate(Arrays.asList("cs2103t", "cs2100"));
        assertTrue(predicate.test(new TaskBuilder().withDescription("do cs2103t do cs2100").build()));

        // Only one matching keyword
        predicate = new TaskDescriptionContainsKeywordsPredicate(Arrays.asList("cs2100", "cs2101"));
        assertTrue(predicate.test(new TaskBuilder().withDescription("do cs2103t do cs2101").build()));

        // Mixed-case keywords
        predicate = new TaskDescriptionContainsKeywordsPredicate(Arrays.asList("cS2103T", "Cs2100"));
        assertTrue(predicate.test(new TaskBuilder().withDescription("do cs2103t do cs2100").build()));
    }

    @Test
    public void test_taskDescriptionDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        TaskDescriptionContainsKeywordsPredicate predicate =
                new TaskDescriptionContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new TaskBuilder().withDescription("do cs2103t").build()));

        // Non-matching keyword
        predicate = new TaskDescriptionContainsKeywordsPredicate(Arrays.asList("cs2101"));
        assertFalse(predicate.test(new TaskBuilder().withDescription("do cs2103t do cs2100").build()));

    }

    @Test
    public void toStringMethod() {
        List<String> keywords = List.of("keyword1", "keyword2");
        TaskDescriptionContainsKeywordsPredicate predicate = new TaskDescriptionContainsKeywordsPredicate(keywords);

        String expected = TaskDescriptionContainsKeywordsPredicate.class.getCanonicalName()
                + "{task desc keywords=" + keywords + "}";
        assertEquals(expected, predicate.toString());
    }
}
