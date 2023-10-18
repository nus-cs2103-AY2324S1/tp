package seedu.address.model.meeting;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.ParserUtil.FORMAT;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.MeetingBuilder;

public class MeetingTimeContainsPredicateTest {

    private LocalDateTime start = LocalDateTime.of(LocalDate.of(0001, 01, 01), LocalTime.of(00, 00));
    private LocalDateTime startOn30 = LocalDateTime.of(LocalDate.of(2023, 9, 30), LocalTime.of(10, 00));
    private LocalDateTime endOn30 = LocalDateTime.of(LocalDate.of(2023, 9, 30), LocalTime.of(12, 00));
    private LocalDateTime start2 = LocalDateTime.of(LocalDate.of(0001, 01, 02), LocalTime.of(00, 00));
    private LocalDateTime end = LocalDateTime.of(LocalDate.of(9999, 12, 31), LocalTime.of(23, 59));

    @Test
    public void equals() {
        MeetingTimeContainsPredicate firstPredicate = new MeetingTimeContainsPredicate(start, end);
        MeetingTimeContainsPredicate secondPredicate = new MeetingTimeContainsPredicate(startOn30, endOn30);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        MeetingTimeContainsPredicate firstPredicateCopy = new MeetingTimeContainsPredicate(start, end);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_meetingTimeWithinKeywords_returnsTrue() {
        MeetingTimeContainsPredicate predicate =
                new MeetingTimeContainsPredicate(start, end);
        assertTrue(predicate.test(new MeetingBuilder()
                .withStart(startOn30.format(FORMAT)).withEnd(endOn30.format(FORMAT)).build()));

        predicate = new MeetingTimeContainsPredicate(start, end);
        assertTrue(predicate.test(new MeetingBuilder()
                .withStart(start.format(FORMAT)).withEnd(end.format(FORMAT)).build()));
    }

    @Test
    public void test_meetingTimeOutOfKeywords_returnsFalse() {
        MeetingTimeContainsPredicate predicate =
            new MeetingTimeContainsPredicate(startOn30, endOn30);
        assertFalse(predicate.test(new MeetingBuilder()
                .withStart(start.format(FORMAT)).withEnd(end.format(FORMAT)).build()));
    }

    @Test
    public void toStringMethod() {
        MeetingTimeContainsPredicate predicate = new MeetingTimeContainsPredicate(start, end);

        String expected = MeetingTimeContainsPredicate.class.getCanonicalName()
                + "{start=" + start + ", end=" + end + "}";
        assertEquals(expected, predicate.toString());
    }
}
