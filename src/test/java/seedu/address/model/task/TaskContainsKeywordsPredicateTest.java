package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.TaskBuilder;

public class TaskContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        TaskContainsKeywordsPredicate firstPredicate =
                new TaskContainsKeywordsPredicate(firstPredicateKeywordList);
        TaskContainsKeywordsPredicate secondPredicate =
                new TaskContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertEquals(firstPredicate, firstPredicate);

        // same values -> returns true
        TaskContainsKeywordsPredicate firstPredicateCopy =
                new TaskContainsKeywordsPredicate(firstPredicateKeywordList);
        assertEquals(firstPredicate, firstPredicateCopy);

        // different types -> returns false
        assertNotEquals(1, firstPredicate);
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertNotEquals(null, firstPredicate);

        // different values -> returns false
        assertNotEquals(firstPredicate, secondPredicate);
    }

    @Test
    public void test_taskContainsKeywords_returnsTrue() {
        Task testTask = new TaskBuilder()
                .withTitle("Alice Charlie Bob Echo Derrick")
                .withNote("Fiona Harry George Irene")
                .build();

        // One keyword in Title
        TaskContainsKeywordsPredicate predicate =
                new TaskContainsKeywordsPredicate(Collections.singletonList("Bob"));
        assertTrue(predicate.test(testTask));

        // One keyword in Note
        predicate = new TaskContainsKeywordsPredicate(Collections.singletonList("Harry"));
        assertTrue(predicate.test(testTask));

        // Multiple keywords in Title
        predicate = new TaskContainsKeywordsPredicate(Arrays.asList("Alice", "Bob"));
        assertTrue(predicate.test(testTask));

        // Multiple keywords in Note
        predicate = new TaskContainsKeywordsPredicate(Arrays.asList("Fiona", "George"));
        assertTrue(predicate.test(testTask));

        // Only one matching keyword in Title
        predicate = new TaskContainsKeywordsPredicate(Arrays.asList("Alice", "Invalid"));
        assertTrue(predicate.test(testTask));

        // Only one matching keyword in Note
        predicate = new TaskContainsKeywordsPredicate(Arrays.asList("Invalid", "Harry"));
        assertTrue(predicate.test(testTask));

        // Mixed-case keywords in Title
        predicate = new TaskContainsKeywordsPredicate(Arrays.asList("aLIce", "echO"));
        assertTrue(predicate.test(testTask));

        // Mixed-case keywords in Note
        predicate = new TaskContainsKeywordsPredicate(Arrays.asList("fIoNA", "iRENE"));
        assertTrue(predicate.test(testTask));
    }

    @Test
    public void test_taskDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        TaskContainsKeywordsPredicate predicate = new TaskContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new TaskBuilder().withTitle("Alice").withNote("Bob").build()));

        // Non-matching keyword
        predicate = new TaskContainsKeywordsPredicate(Collections.singletonList("Carol"));
        assertFalse(predicate.test(new TaskBuilder().withTitle("Alice").withNote("Bob").build()));
    }

    @Test
    public void toStringMethod() {
        List<String> keywords = List.of("keyword1", "keyword2");
        TaskContainsKeywordsPredicate predicate = new TaskContainsKeywordsPredicate(keywords);

        String expected = TaskContainsKeywordsPredicate.class.getCanonicalName() + "{keywords=" + keywords + "}";
        assertEquals(expected, predicate.toString());
    }
}
