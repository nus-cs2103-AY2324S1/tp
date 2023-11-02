package seedu.address.model.event;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.testutil.MeetingBuilder;

public class EventNameContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        EventNameOrGroupContainsKeywordsPredicate firstPredicate =
                new EventNameOrGroupContainsKeywordsPredicate(firstPredicateKeywordList);
        EventNameOrGroupContainsKeywordsPredicate secondPredicate =
                new EventNameOrGroupContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        EventNameOrGroupContainsKeywordsPredicate firstPredicateCopy =
                new EventNameOrGroupContainsKeywordsPredicate(firstPredicateKeywordList);
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
        Event testEvent;

        try {
            testEvent = new MeetingBuilder().withEventName("Birthday Party")
                    .withEventDate(LocalDate.now().plusDays(1).toString())
                    .build();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        // One keyword
        EventNameOrGroupContainsKeywordsPredicate predicate =
                new EventNameOrGroupContainsKeywordsPredicate(Collections.singletonList("Birthday"));
        predicate.setPersonList(FXCollections.observableArrayList());
        assertTrue(predicate.test(testEvent));

        // Multiple keywords
        predicate = new EventNameOrGroupContainsKeywordsPredicate(Arrays.asList("Birthday", "Party"));
        predicate.setPersonList(FXCollections.observableArrayList());
        assertTrue(predicate.test(testEvent));

        // Only one matching keyword
        predicate = new EventNameOrGroupContainsKeywordsPredicate(Arrays.asList("Party", "Event"));
        predicate.setPersonList(FXCollections.observableArrayList());
        assertTrue(predicate.test(testEvent));

        // Mixed-case keywords
        predicate = new EventNameOrGroupContainsKeywordsPredicate(Arrays.asList("bIRthDaY", "pArTY"));
        predicate.setPersonList(FXCollections.observableArrayList());
        assertTrue(predicate.test(testEvent));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        Event testEvent;

        try {
            testEvent = new MeetingBuilder().withEventName("Project Presentation")
                    .withEventDate(LocalDate.now().plusDays(1).toString())
                    .withEventStartTime("1300")
                    .withEventEndTime("1400")
                    .build();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        // Zero keywords
        EventNameOrGroupContainsKeywordsPredicate predicate =
                new EventNameOrGroupContainsKeywordsPredicate(Collections.emptyList());
        predicate.setPersonList(FXCollections.observableArrayList());
        assertFalse(predicate.test(testEvent));

        // Non-matching keyword
        predicate = new EventNameOrGroupContainsKeywordsPredicate(Arrays.asList("Carol"));
        predicate.setPersonList(FXCollections.observableArrayList());
        assertFalse(predicate.test(testEvent));

        // Keywords match date, start time and end time, but does not match name
        predicate = new EventNameOrGroupContainsKeywordsPredicate(
                Arrays.asList(LocalDate.now().plusDays(1).toString(), "1300", "1400"));
        predicate.setPersonList(FXCollections.observableArrayList());
        assertFalse(predicate.test(testEvent));
    }

    @Test
    public void toStringMethod() {
        List<String> keywords = List.of("keyword1", "keyword2");
        EventNameOrGroupContainsKeywordsPredicate predicate = new EventNameOrGroupContainsKeywordsPredicate(keywords);

        String expected = EventNameOrGroupContainsKeywordsPredicate.class.getCanonicalName() + "{keywords=" + keywords
                + "}";
        assertEquals(expected, predicate.toString());
    }
}
