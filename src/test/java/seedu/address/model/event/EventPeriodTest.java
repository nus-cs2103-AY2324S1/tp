package seedu.address.model.event;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DATE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_END_DATE_EARLIER;
import static seedu.address.logic.commands.CommandTestUtil.VALID_END_DATE_LATER;
import static seedu.address.logic.commands.CommandTestUtil.VALID_START_DATE_EARLIER;
import static seedu.address.logic.commands.CommandTestUtil.VALID_START_DATE_LATER;
import static seedu.address.model.event.EventPeriod.isValidPeriod;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.format.DateTimeParseException;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.EventPeriodBuilder;

public class EventPeriodTest {
    @Test
    public void constructorTest() {
        assertThrows(DateTimeParseException.class, () ->
                new EventPeriod(INVALID_DATE, VALID_END_DATE_EARLIER));

        assertThrows(DateTimeParseException.class, () ->
                new EventPeriod(VALID_START_DATE_EARLIER, INVALID_DATE));

        assertThrows(DateTimeParseException.class, () ->
                new EventPeriod(INVALID_DATE, INVALID_DATE));

        assertThrows(NullPointerException.class, () ->
                new EventPeriod(null, VALID_END_DATE_EARLIER));

        assertThrows(NullPointerException.class, () ->
                new EventPeriod(VALID_START_DATE_EARLIER, null));

        assertThrows(NullPointerException.class, () ->
                new EventPeriod(null, null));
    }

    @Test
    public void createNonConflictingPeriodTest() {
        EventPeriod nonConflictingPeriod = EventPeriod.createNonConflictingPeriod();
        EventPeriod validPeriod = new EventPeriodBuilder().build();

        assertFalse(validPeriod.isOverlapping(nonConflictingPeriod));
    }

    @Test
    public void isValidPeriodTest() {
        assertTrue(isValidPeriod(VALID_START_DATE_EARLIER, VALID_END_DATE_EARLIER));

        assertFalse(isValidPeriod(VALID_END_DATE_EARLIER, VALID_START_DATE_EARLIER));

        assertFalse(isValidPeriod(VALID_START_DATE_EARLIER, VALID_START_DATE_EARLIER));

        assertFalse(isValidPeriod(INVALID_DATE, VALID_END_DATE_EARLIER));

        assertFalse(isValidPeriod(VALID_START_DATE_EARLIER, INVALID_DATE));

        assertFalse(isValidPeriod(INVALID_DATE, INVALID_DATE));
    }

    @Test
    public void isOverlappingTest() {
        EventPeriod earlierNoOverlapEventPeriod = new EventPeriodBuilder()
                .changeStartAndEnd(VALID_START_DATE_EARLIER, VALID_END_DATE_EARLIER).build();
        EventPeriod laterNoOverlapEventPeriod = new EventPeriodBuilder()
                .changeStartAndEnd(VALID_START_DATE_LATER, VALID_END_DATE_LATER).build();
        EventPeriod earlierOverlappingEventPeriod = new EventPeriodBuilder()
                .changeStartAndEnd(VALID_START_DATE_EARLIER, VALID_END_DATE_LATER).build();
        EventPeriod laterOverlappingEventPeriod = new EventPeriodBuilder()
                .changeStartAndEnd(VALID_START_DATE_LATER, VALID_END_DATE_LATER).build();

        assertThrows(NullPointerException.class, () -> earlierNoOverlapEventPeriod.isOverlapping(null));

        assertTrue(earlierOverlappingEventPeriod.isOverlapping(laterOverlappingEventPeriod));

        assertTrue(laterOverlappingEventPeriod.isOverlapping(earlierOverlappingEventPeriod));

        assertFalse(earlierNoOverlapEventPeriod.isOverlapping(laterNoOverlapEventPeriod));

        assertFalse(laterNoOverlapEventPeriod.isOverlapping(earlierNoOverlapEventPeriod));
    }

    @Test
    public void compareToTest() {
        EventPeriod earlierEventPeriod = new EventPeriodBuilder()
                .changeStartAndEnd(VALID_START_DATE_EARLIER, VALID_END_DATE_EARLIER).build();
        EventPeriod laterEventPeriod = new EventPeriodBuilder()
                .changeStartAndEnd(VALID_START_DATE_LATER, VALID_END_DATE_LATER).build();

        assertThrows(NullPointerException.class, () -> earlierEventPeriod.compareTo(null));

        assertEquals(-1, earlierEventPeriod.compareTo(laterEventPeriod));

        assertEquals(0, earlierEventPeriod.compareTo(earlierEventPeriod));

        assertEquals(1, laterEventPeriod.compareTo(earlierEventPeriod));
    }

    @Test
    public void getDatesTest() {
        EventPeriod validEventPeriod = new EventPeriodBuilder().build();

        assertFalse(validEventPeriod.getDates().isEmpty());
    }

    @Test
    public void equalsTest() {
        EventPeriod validEventPeriod = new EventPeriodBuilder()
                .changeStartAndEnd(VALID_START_DATE_EARLIER, VALID_END_DATE_LATER).build();
        EventPeriod equivalentEventPeriod = new EventPeriodBuilder()
                .changeStartAndEnd(VALID_START_DATE_EARLIER, VALID_END_DATE_LATER).build();
        EventPeriod laterStartEventPeriod = new EventPeriodBuilder()
                .changeStartAndEnd(VALID_START_DATE_LATER, VALID_END_DATE_LATER).build();
        EventPeriod earlierEndEventPeriod = new EventPeriodBuilder()
                .changeStartAndEnd(VALID_START_DATE_EARLIER, VALID_END_DATE_EARLIER).build();
        Object nonEventPeriodObject = new Object();

        assertThrows(NullPointerException.class, () -> validEventPeriod.equals(null));

        assertTrue(validEventPeriod.equals(validEventPeriod));

        assertTrue(validEventPeriod.equals(equivalentEventPeriod));

        assertFalse(validEventPeriod.equals(nonEventPeriodObject));

        assertFalse(validEventPeriod.equals(laterStartEventPeriod));

        assertFalse(validEventPeriod.equals(earlierEndEventPeriod));
    }

    @Test
    public void toStringTest() {
        EventPeriod validEventPeriod = new EventPeriodBuilder()
                .changeStartAndEnd(VALID_START_DATE_EARLIER, VALID_END_DATE_EARLIER).build();
        String expectedString = "start: "
                + VALID_START_DATE_EARLIER
                + "; end: "
                + VALID_END_DATE_EARLIER;

        assertEquals(validEventPeriod.toString(), expectedString);
    }
}
