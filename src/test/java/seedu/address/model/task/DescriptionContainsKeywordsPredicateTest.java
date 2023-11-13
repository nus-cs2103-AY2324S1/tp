package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.TaskBuilder;

public class DescriptionContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        DescriptionContainsKeywordsPredicate firstPredicate =
                new DescriptionContainsKeywordsPredicate(firstPredicateKeywordList);
        DescriptionContainsKeywordsPredicate secondPredicate =
                new DescriptionContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        DescriptionContainsKeywordsPredicate firstPredicateCopy =
                new DescriptionContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different task -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_nameContainsKeywords_returnsTrue() {
        // One keyword
        DescriptionContainsKeywordsPredicate predicate =
                new DescriptionContainsKeywordsPredicate(Collections.singletonList("Guide"));
        assertTrue(predicate.test(new TaskBuilder().withDescription("User Guide").build()));

        // Multiple keywords
        predicate = new DescriptionContainsKeywordsPredicate(Arrays.asList("User", "Guide"));
        assertTrue(predicate.test(new TaskBuilder().withDescription("User Guide").build()));

        // Only one matching keyword
        predicate = new DescriptionContainsKeywordsPredicate(Arrays.asList("Developer", "Guide"));
        assertTrue(predicate.test(new TaskBuilder().withDescription("User Guide").build()));

        // Mixed-case keywords
        predicate = new DescriptionContainsKeywordsPredicate(Arrays.asList("uSEr", "gUIde"));
        assertTrue(predicate.test(new TaskBuilder().withDescription("User Guide").build()));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        DescriptionContainsKeywordsPredicate predicate =
                new DescriptionContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new TaskBuilder().withDescription("User").build()));

        // Non-matching keyword
        predicate = new DescriptionContainsKeywordsPredicate(Arrays.asList("Developer"));
        assertFalse(predicate.test(new TaskBuilder().withDescription("User Guide").build()));

        // Keywords match phone, email and address, but does not match name
        predicate = new DescriptionContainsKeywordsPredicate(Arrays
                .asList("12345", "alice@email.com", "Main", "Street"));
        assertFalse(predicate.test(new TaskBuilder().withDescription("User").build()));
    }

    @Test
    public void toStringMethod() {
        List<String> keywords = List.of("keyword1", "keyword2");
        DescriptionContainsKeywordsPredicate predicate = new DescriptionContainsKeywordsPredicate(keywords);

        String expected = DescriptionContainsKeywordsPredicate.class.getCanonicalName() + "{keywords=" + keywords + "}";
        assertEquals(expected, predicate.toString());
    }
}
