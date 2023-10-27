package seedu.address.model.schedule;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.ScheduleBuilder;

public class TutorNameContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        TutorNameContainsKeywordsPredicate firstPredicate =
            new TutorNameContainsKeywordsPredicate(firstPredicateKeywordList);
        TutorNameContainsKeywordsPredicate secondPredicate =
            new TutorNameContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        TutorNameContainsKeywordsPredicate firstPredicateCopy =
            new TutorNameContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_nameContainsKeywords_returnsTrue() {
        // One keyword
        TutorNameContainsKeywordsPredicate predicate =
            new TutorNameContainsKeywordsPredicate(Collections.singletonList("Alice"));
        assertTrue(predicate.test(new ScheduleBuilder().build()));

        // Multiple keywords
        predicate = new TutorNameContainsKeywordsPredicate(Arrays.asList("Alice", "Pauline"));
        assertTrue(predicate.test(new ScheduleBuilder().build()));

        // Only one matching keyword
        predicate = new TutorNameContainsKeywordsPredicate(Arrays.asList("Alice", "Carol"));
        assertTrue(predicate.test(new ScheduleBuilder().build()));

        // Mixed-case keywords
        predicate = new TutorNameContainsKeywordsPredicate(Arrays.asList("aLIce", "pAuLiNe"));
        assertTrue(predicate.test(new ScheduleBuilder().build()));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        TutorNameContainsKeywordsPredicate predicate = new TutorNameContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new ScheduleBuilder().build()));

        // Non-matching keyword
        predicate = new TutorNameContainsKeywordsPredicate(Arrays.asList("Carol"));
        assertFalse(predicate.test(new ScheduleBuilder().build()));

    }

    @Test
    public void toStringMethod() {
        List<String> keywords = List.of("keyword1", "keyword2");
        TutorNameContainsKeywordsPredicate predicate = new TutorNameContainsKeywordsPredicate(keywords);

        String expected = TutorNameContainsKeywordsPredicate.class.getCanonicalName() + "{keywords=" + keywords + "}";
        assertEquals(expected, predicate.toString());
    }
}
