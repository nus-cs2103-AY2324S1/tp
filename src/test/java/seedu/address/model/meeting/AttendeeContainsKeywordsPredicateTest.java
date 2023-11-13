package seedu.address.model.meeting;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.MeetingBuilder;

public class AttendeeContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        AttendeeContainsKeywordsPredicate firstPredicate =
                new AttendeeContainsKeywordsPredicate(firstPredicateKeywordList);
        AttendeeContainsKeywordsPredicate secondPredicate =
                new AttendeeContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        AttendeeContainsKeywordsPredicate firstPredicateCopy =
                new AttendeeContainsKeywordsPredicate(firstPredicateKeywordList);
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
        AttendeeContainsKeywordsPredicate predicate =
                new AttendeeContainsKeywordsPredicate(Collections.singletonList("CS2103T"));
        assertTrue(predicate.test(new MeetingBuilder().withAttendees("CS2103T").build()));

        // Multiple keywords
        predicate = new AttendeeContainsKeywordsPredicate(Arrays.asList("CS2103T", "meeting"));
        assertTrue(predicate.test(new MeetingBuilder().withAttendees("CS2103T meeting").build()));

        // Only one matching keyword
        predicate = new AttendeeContainsKeywordsPredicate(Arrays.asList("CS2013T", "meeting"));
        assertTrue(predicate.test(new MeetingBuilder().withAttendees("ABCDE meeting").build()));

        // Mixed-case keywords
        predicate = new AttendeeContainsKeywordsPredicate(Arrays.asList("CS2103T", "meeting"));
        assertTrue(predicate.test(new MeetingBuilder().withAttendees("cs2103t MEETING").build()));
    }

    @Test
    public void test_locationDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        AttendeeContainsKeywordsPredicate predicate = new AttendeeContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new MeetingBuilder().withAttendees("Alice").build()));

        // Non-matching keyword
        predicate = new AttendeeContainsKeywordsPredicate(Arrays.asList("Carol"));
        assertFalse(predicate.test(new MeetingBuilder().withAttendees("Alice Bob").build()));

        // Keywords match others
        predicate = new AttendeeContainsKeywordsPredicate(Arrays.asList("12345", "alice@email.com", "Main", "Street"));
        assertFalse(predicate.test(new MeetingBuilder().withAttendees("Alice")
                .build()));
    }

    @Test
    public void toStringMethod() {
        List<String> keywords = List.of("keyword1", "keyword2");
        AttendeeContainsKeywordsPredicate predicate = new AttendeeContainsKeywordsPredicate(keywords);

        String expected = AttendeeContainsKeywordsPredicate.class.getCanonicalName() + "{keywords=" + keywords + "}";
        assertEquals(expected, predicate.toString());
    }
}
