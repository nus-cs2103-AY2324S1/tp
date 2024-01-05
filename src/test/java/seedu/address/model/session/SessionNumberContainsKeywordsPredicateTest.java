package seedu.address.model.session;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.SessionBuilder;

public class SessionNumberContainsKeywordsPredicateTest {
    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("1");
        List<String> secondPredicateKeywordList = Arrays.asList("0", "3");

        SessionNumberContainsKeywordsPredicate firstPredicate =
                new SessionNumberContainsKeywordsPredicate(firstPredicateKeywordList);
        SessionNumberContainsKeywordsPredicate secondPredicate =
                new SessionNumberContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        SessionNumberContainsKeywordsPredicate firstPredicateCopy =
                new SessionNumberContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different task -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_sessionNumberContainsKeywords_returnsTrue() {
        // One keyword
        SessionNumberContainsKeywordsPredicate predicate =
                new SessionNumberContainsKeywordsPredicate(Collections.singletonList("1"));
        assertTrue(predicate.test(new SessionBuilder().withSessionNumber("12").build()));

        // Multiple keywords
        predicate = new SessionNumberContainsKeywordsPredicate(Arrays.asList("0", "3"));
        assertTrue(predicate.test(new SessionBuilder().withSessionNumber("30").build()));

        // Only one matching keyword
        predicate = new SessionNumberContainsKeywordsPredicate(Arrays.asList("0", "3"));
        assertTrue(predicate.test(new SessionBuilder().withSessionNumber("35").build()));
    }

    @Test
    public void test_sessionNumberDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        SessionNumberContainsKeywordsPredicate predicate =
                new SessionNumberContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new SessionBuilder().withSessionNumber("1").build()));

        // Non-matching keyword
        predicate = new SessionNumberContainsKeywordsPredicate(Arrays.asList("0"));
        assertFalse(predicate.test(new SessionBuilder().withSessionNumber("2").build()));

    }

    @Test
    public void toStringMethod() {
        List<String> keywords = List.of("keyword1", "keyword2");
        SessionNumberContainsKeywordsPredicate predicate = new SessionNumberContainsKeywordsPredicate(keywords);

        String expected = SessionNumberContainsKeywordsPredicate.class.getCanonicalName()
                + "{session keywords=" + keywords + "}";
        assertEquals(expected, predicate.toString());
    }
}
