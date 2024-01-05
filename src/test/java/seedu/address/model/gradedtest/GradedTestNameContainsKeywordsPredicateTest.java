package seedu.address.model.gradedtest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.GradedTestBuilder;

public class GradedTestNameContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("1");
        List<String> secondPredicateKeywordList = Arrays.asList("0", "3");

        GradedTestNameContainsKeywordsPredicate firstPredicate =
                new GradedTestNameContainsKeywordsPredicate(firstPredicateKeywordList);
        GradedTestNameContainsKeywordsPredicate secondPredicate =
                new GradedTestNameContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        GradedTestNameContainsKeywordsPredicate firstPredicateCopy =
                new GradedTestNameContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different task -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void equals_sameInstance_returnsTrue() {
        GradedTestNameContainsKeywordsPredicate predicate =
                new GradedTestNameContainsKeywordsPredicate(List.of("keyword"));
        assertTrue(predicate.equals(predicate));
    }

    @Test
    public void equals_mainCase_returnsTrue() {
        GradedTestNameContainsKeywordsPredicate predicate1 =
                new GradedTestNameContainsKeywordsPredicate(List.of("keyword"));
        GradedTestNameContainsKeywordsPredicate predicate2 =
                new GradedTestNameContainsKeywordsPredicate(List.of("keyword"));
        assertTrue(predicate1.equals(predicate2));
    }

    @Test
    public void equals_mainCase_returnsFalse() {
        GradedTestNameContainsKeywordsPredicate predicate1 =
                new GradedTestNameContainsKeywordsPredicate(List.of("keyword"));
        GradedTestNameContainsKeywordsPredicate predicate2 =
                new GradedTestNameContainsKeywordsPredicate(List.of("differentKeyword"));
        assertFalse(predicate1.equals(predicate2));
    }

    @Test
    public void equals_null_returnsFalse() {
        GradedTestNameContainsKeywordsPredicate predicate =
                new GradedTestNameContainsKeywordsPredicate(List.of("keyword"));
        assertFalse(predicate.equals(null));
    }

    @Test
    public void equals_differentType_returnsFalse() {
        GradedTestNameContainsKeywordsPredicate predicate =
                new GradedTestNameContainsKeywordsPredicate(List.of("keyword"));
        String notAPredicate = "notAPredicate";
        assertFalse(predicate.equals(notAPredicate));
    }

    @Test
    public void test_gradedTestContainsKeywords_returnsTrue() {
        // One keyword
        GradedTestNameContainsKeywordsPredicate predicate =
                new GradedTestNameContainsKeywordsPredicate(Collections.singletonList("1"));
        assertTrue(predicate.test(new GradedTestBuilder().withFinals("1").withPe("1").build()));

        // Multiple keywords
        predicate = new GradedTestNameContainsKeywordsPredicate(Arrays.asList("0", "3"));
        assertTrue(predicate.test(new GradedTestBuilder().withMidTerms("30").withPe("3").withFinals("0").build()));

        // Only one matching keyword
        predicate = new GradedTestNameContainsKeywordsPredicate(Arrays.asList("0", "3"));
        assertTrue(predicate.test(new GradedTestBuilder().withMidTerms("35").withFinals("0").withPe("3").build()));
    }

    @Test
    public void test_gradedTestDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        GradedTestNameContainsKeywordsPredicate predicate =
                new GradedTestNameContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new GradedTestBuilder().withFinals("1").build()));

        // Non-matching keyword
        predicate = new GradedTestNameContainsKeywordsPredicate(Arrays.asList("0"));
        assertFalse(predicate.test(new GradedTestBuilder().withFinals("2").build()));

    }

    @Test
    public void toStringMethod() {
        List<String> keywords = List.of("keyword1", "keyword2");
        GradedTestNameContainsKeywordsPredicate predicate = new GradedTestNameContainsKeywordsPredicate(keywords);

        String expected = GradedTestNameContainsKeywordsPredicate.class.getCanonicalName()
                + "{gradedTest keywords=" + keywords + "}";
        assertEquals(expected, predicate.toString());
    }
}
