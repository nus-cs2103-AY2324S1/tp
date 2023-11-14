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

public class TaskContainsTagsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("firstTag");
        List<String> secondPredicateKeywordList = Arrays.asList("firstTag", "secondTag");

        TaskContainsTagsPredicate firstPredicate =
                new TaskContainsTagsPredicate(firstPredicateKeywordList);
        TaskContainsTagsPredicate secondPredicate =
                new TaskContainsTagsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertEquals(firstPredicate, firstPredicate);

        // same values -> returns true
        TaskContainsTagsPredicate firstPredicateCopy =
                new TaskContainsTagsPredicate(firstPredicateKeywordList);
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
    public void test_taskContainsTags_returnsTrue() {
        Task testTask = new TaskBuilder().withTags("firstTag", "secondTag").build();

        // One keyword (tag)
        TaskContainsTagsPredicate predicate =
                new TaskContainsTagsPredicate(Collections.singletonList("firstTag"));
        assertTrue(predicate.test(testTask));

        // Multiple keywords (tags)
        predicate = new TaskContainsTagsPredicate(Arrays.asList("firstTag", "secondTag"));
        assertTrue(predicate.test(testTask));

        // Mixed-case keywords (tags)
        predicate = new TaskContainsTagsPredicate(Arrays.asList("FIRSTtag", "SECONDTAG"));
        assertTrue(predicate.test(testTask));
    }

    @Test
    public void test_taskDoesNotContainTags_returnsFalse() {
        Task testTask = new TaskBuilder().withTags("firstTag", "secondTag").build();

        // Zero keywords (tags)
        TaskContainsTagsPredicate predicate = new TaskContainsTagsPredicate(Collections.emptyList());
        assertFalse(predicate.test(testTask));

        // Non-matching keyword (tag)
        predicate = new TaskContainsTagsPredicate(Collections.singletonList("thirdTag"));
        assertFalse(predicate.test(testTask));
    }

    @Test
    public void toStringMethod() {
        List<String> keywords = List.of("tag1", "tag2");
        TaskContainsTagsPredicate predicate = new TaskContainsTagsPredicate(keywords);

        String expected = TaskContainsTagsPredicate.class.getCanonicalName() + "{keywords=" + keywords + "}";
        assertEquals(expected, predicate.toString());
    }
}
