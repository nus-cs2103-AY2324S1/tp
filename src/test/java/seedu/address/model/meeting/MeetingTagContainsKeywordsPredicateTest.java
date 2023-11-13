package seedu.address.model.meeting;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.MeetingBuilder;

public class MeetingTagContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        MeetingTagContainsKeywordsPredicate firstPredicate =
                new MeetingTagContainsKeywordsPredicate(firstPredicateKeywordList);
        MeetingTagContainsKeywordsPredicate secondPredicate =
                new MeetingTagContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        MeetingTagContainsKeywordsPredicate firstPredicateCopy =
                new MeetingTagContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_locationContainsKeywords_returnsTrue() {
        // One keyword
        MeetingTagContainsKeywordsPredicate predicate =
                new MeetingTagContainsKeywordsPredicate(Collections.singletonList("CS2103T"));
        assertTrue(predicate.test(new MeetingBuilder().withTags("CS2103T").build()));

        // Multiple keywords
        predicate = new MeetingTagContainsKeywordsPredicate(Arrays.asList("CS2103T", "meeting"));
        assertTrue(predicate.test(new MeetingBuilder().withTags("CS2103T", "meeting").build()));

        // Only one matching keyword
        predicate = new MeetingTagContainsKeywordsPredicate(Arrays.asList("CS2013T", "meeting"));
        assertTrue(predicate.test(new MeetingBuilder().withTags("ABCDE", "meeting").build()));

        // Mixed-case keywords
        predicate = new MeetingTagContainsKeywordsPredicate(Arrays.asList("CS2103T", "meeting"));
        assertTrue(predicate.test(new MeetingBuilder().withTags("cs2103t", "MEETING").build()));
    }

    @Test
    public void test_locationDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        MeetingTagContainsKeywordsPredicate predicate =
                new MeetingTagContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new MeetingBuilder().withTags("Alice").build()));

        // Non-matching keyword
        predicate = new MeetingTagContainsKeywordsPredicate(Arrays.asList("Carol"));
        assertFalse(predicate.test(new MeetingBuilder().withTags("Alice", "Bob").build()));

        // Keywords match others
        predicate =
                new MeetingTagContainsKeywordsPredicate(Arrays.asList("12345", "alice@email.com", "Main", "Street"));
        assertFalse(predicate.test(new MeetingBuilder().withTags("Alice")
                .build()));
    }

    @Test
    public void toStringMethod() {
        List<String> keywords = List.of("keyword1", "keyword2");
        MeetingTagContainsKeywordsPredicate predicate = new MeetingTagContainsKeywordsPredicate(keywords);

        String expected = MeetingTagContainsKeywordsPredicate.class.getCanonicalName() + "{keywords=" + keywords + "}";
        assertEquals(expected, predicate.toString());
    }
}
