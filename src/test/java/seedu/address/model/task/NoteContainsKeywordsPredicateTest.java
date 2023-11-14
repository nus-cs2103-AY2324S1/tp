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

public class NoteContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        NoteContainsKeywordsPredicate firstPredicate =
                new NoteContainsKeywordsPredicate(firstPredicateKeywordList);
        NoteContainsKeywordsPredicate secondPredicate =
                new NoteContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertEquals(firstPredicate, firstPredicate);

        // same values -> returns true
        NoteContainsKeywordsPredicate firstPredicateCopy =
                new NoteContainsKeywordsPredicate(firstPredicateKeywordList);
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
    public void test_noteContainsKeywords_returnsTrue() {
        Task testTask = new TaskBuilder()
                .withTitle("Alice Charlie Bob Echo Derrick")
                .withNote("Fiona Harry George Irene")
                .build();

        // One keyword in Note
        NoteContainsKeywordsPredicate predicate =
                new NoteContainsKeywordsPredicate(Collections.singletonList("Harry"));
        assertTrue(predicate.test(testTask));

        // Multiple keywords in Note
        predicate = new NoteContainsKeywordsPredicate(Arrays.asList("Fiona", "George"));
        assertTrue(predicate.test(testTask));

        // Only one matching keyword in Note
        predicate = new NoteContainsKeywordsPredicate(Arrays.asList("Invalid", "Harry"));
        assertTrue(predicate.test(testTask));

        // Mixed-case keywords in Note
        predicate = new NoteContainsKeywordsPredicate(Arrays.asList("fIoNA", "iRENE"));
        assertTrue(predicate.test(testTask));
    }

    @Test
    public void test_noteDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        NoteContainsKeywordsPredicate predicate = new NoteContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new TaskBuilder().withTitle("Alice").withNote("Bob").build()));

        // Non-matching keyword
        predicate = new NoteContainsKeywordsPredicate(Collections.singletonList("Carol"));
        assertFalse(predicate.test(new TaskBuilder().withTitle("Alice").withNote("Bob").build()));

        // Keywords match title but not note
        predicate = new NoteContainsKeywordsPredicate(Arrays.asList("Alice", "Bob"));
        assertFalse(predicate.test(new TaskBuilder().withTitle("Alice Bob").withNote("Carol Derrick").build()));
    }

    @Test
    public void toStringMethod() {
        List<String> keywords = List.of("keyword1", "keyword2");
        NoteContainsKeywordsPredicate predicate = new NoteContainsKeywordsPredicate(keywords);

        String expected = NoteContainsKeywordsPredicate.class.getCanonicalName() + "{keywords=" + keywords + "}";
        assertEquals(expected, predicate.toString());
    }
}
