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

public class TitleContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        TitleContainsKeywordsPredicate firstPredicate =
                new TitleContainsKeywordsPredicate(firstPredicateKeywordList);
        TitleContainsKeywordsPredicate secondPredicate =
                new TitleContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertEquals(firstPredicate, firstPredicate);

        // same values -> returns true
        TitleContainsKeywordsPredicate firstPredicateCopy =
                new TitleContainsKeywordsPredicate(firstPredicateKeywordList);
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
    public void test_titleContainsKeywords_returnsTrue() {
        Task testTask = new TaskBuilder()
                .withTitle("Alice Charlie Bob Echo Derrick")
                .withNote("Fiona Harry George Irene")
                .build();

        // One keyword in Title
        TitleContainsKeywordsPredicate predicate =
                new TitleContainsKeywordsPredicate(Collections.singletonList("Bob"));
        assertTrue(predicate.test(testTask));

        // Multiple keywords in Title
        predicate = new TitleContainsKeywordsPredicate(Arrays.asList("Alice", "Bob"));
        assertTrue(predicate.test(testTask));

        // Only one matching keyword in Title
        predicate = new TitleContainsKeywordsPredicate(Arrays.asList("Alice", "Invalid"));
        assertTrue(predicate.test(testTask));

        // Mixed-case keywords in Title
        predicate = new TitleContainsKeywordsPredicate(Arrays.asList("aLIce", "echO"));
        assertTrue(predicate.test(testTask));
    }

    @Test
    public void test_titleDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        TitleContainsKeywordsPredicate predicate = new TitleContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new TaskBuilder().withTitle("Alice").withNote("Bob").build()));

        // Non-matching keyword
        predicate = new TitleContainsKeywordsPredicate(Collections.singletonList("Carol"));
        assertFalse(predicate.test(new TaskBuilder().withTitle("Alice").withNote("Bob").build()));

        // Keywords match note but not title
        predicate = new TitleContainsKeywordsPredicate(Arrays.asList("Carol", "Derrick"));
        assertFalse(predicate.test(new TaskBuilder().withTitle("Alice Bob").withNote("Carol Derrick").build()));
    }

    @Test
    public void toStringMethod() {
        List<String> keywords = List.of("keyword1", "keyword2");
        TitleContainsKeywordsPredicate predicate = new TitleContainsKeywordsPredicate(keywords);

        String expected = TitleContainsKeywordsPredicate.class.getCanonicalName() + "{keywords=" + keywords + "}";
        assertEquals(expected, predicate.toString());
    }
}
