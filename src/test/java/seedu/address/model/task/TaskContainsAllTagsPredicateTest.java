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

public class TaskContainsAllTagsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("firstTag");
        List<String> secondPredicateKeywordList = Arrays.asList("firstTag", "secondTag");

        TaskContainsAllTagsPredicate firstPredicate =
                new TaskContainsAllTagsPredicate(firstPredicateKeywordList);
        TaskContainsAllTagsPredicate secondPredicate =
                new TaskContainsAllTagsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertEquals(firstPredicate, firstPredicate);

        // same values -> returns true
        TaskContainsAllTagsPredicate firstPredicateCopy =
                new TaskContainsAllTagsPredicate(firstPredicateKeywordList);
        assertEquals(firstPredicate, firstPredicateCopy);

        // different types -> returns false
        assertNotEquals(firstPredicate, 1);

        // null -> returns false
        assertNotEquals(firstPredicate, null);

        // different values -> returns false
        assertNotEquals(firstPredicate, secondPredicate);
    }

    @Test
    public void test_taskContainsTags_returnsTrue() {
        Task testTask = new TaskBuilder().withTags("firstTag", "secondTag").build();

        // One keyword (tag)
        TaskContainsAllTagsPredicate predicate =
                new TaskContainsAllTagsPredicate(Collections.singletonList("firstTag"));
        assertTrue(predicate.test(testTask));

        // Multiple keywords (tags)
        predicate = new TaskContainsAllTagsPredicate(Arrays.asList("firstTag", "secondTag"));
        assertTrue(predicate.test(testTask));

        // Mixed-case keywords (tags)
        predicate = new TaskContainsAllTagsPredicate(Arrays.asList("FIRSTtag", "SECONDTAG"));
        assertTrue(predicate.test(testTask));
    }

    @Test
    public void test_taskDoesNotContainTags_returnsFalse() {
        // Zero tags
        TaskContainsAllTagsPredicate predicate = new TaskContainsAllTagsPredicate(Collections.singletonList("Valid"));
        assertFalse(predicate.test(new TaskBuilder().withTags().build()));

        // Non-matching tag
        predicate = new TaskContainsAllTagsPredicate(Collections.singletonList("Invalid"));
        assertFalse(predicate.test(new TaskBuilder().withTags("firstTag").build()));
    }

    @Test
    public void toStringMethod() {
        List<String> keywords = List.of("tag1", "tag2");
        TaskContainsAllTagsPredicate predicate = new TaskContainsAllTagsPredicate(keywords);

        String expected = TaskContainsAllTagsPredicate.class.getCanonicalName() + "{keywords=" + keywords + "}";
        assertEquals(expected, predicate.toString());
    }
}
