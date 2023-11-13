package seedu.address.model.meeting;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.MeetingBuilder;

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
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        TitleContainsKeywordsPredicate firstPredicateCopy =
                new TitleContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_titleContainsKeywords_returnsTrue() {
        // One keyword
        TitleContainsKeywordsPredicate predicate =
                new TitleContainsKeywordsPredicate(Collections.singletonList("CS2103T"));
        assertTrue(predicate.test(new MeetingBuilder().withTitle("CS2103T").build()));

        // Multiple keywords
        predicate = new TitleContainsKeywordsPredicate(Arrays.asList("CS2103T", "meeting"));
        assertTrue(predicate.test(new MeetingBuilder().withTitle("CS2103T meeting").build()));

        // Only one matching keyword
        predicate = new TitleContainsKeywordsPredicate(Arrays.asList("CS2013T", "meeting"));
        assertTrue(predicate.test(new MeetingBuilder().withTitle("ABCDE meeting").build()));

        // Mixed-case keywords
        predicate = new TitleContainsKeywordsPredicate(Arrays.asList("CS2103T", "meeting"));
        assertTrue(predicate.test(new MeetingBuilder().withTitle("cs2103t MEETING").build()));
    }

    @Test
    public void test_titleDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        TitleContainsKeywordsPredicate predicate = new TitleContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new MeetingBuilder().withTitle("Alice").build()));

        // Non-matching keyword
        predicate = new TitleContainsKeywordsPredicate(Arrays.asList("Carol"));
        assertFalse(predicate.test(new MeetingBuilder().withTitle("Alice Bob").build()));

        // Keywords match others
        predicate = new TitleContainsKeywordsPredicate(Arrays.asList("12345", "alice@email.com", "Main", "Street"));
        assertFalse(predicate.test(new MeetingBuilder().withTitle("Alice").withLocation("12345")
                .build()));
    }

    @Test
    public void toStringMethod() {
        List<String> keywords = List.of("keyword1", "keyword2");
        TitleContainsKeywordsPredicate predicate = new TitleContainsKeywordsPredicate(keywords);

        String expected = TitleContainsKeywordsPredicate.class.getCanonicalName() + "{keywords=" + keywords + "}";
        assertEquals(expected, predicate.toString());
    }
}
