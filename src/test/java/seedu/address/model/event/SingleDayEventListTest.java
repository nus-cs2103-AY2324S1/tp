package seedu.address.model.event;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION;
import static seedu.address.logic.commands.CommandTestUtil.VALID_END_DATE_EARLIER;
import static seedu.address.logic.commands.CommandTestUtil.VALID_END_DATE_LATER;
import static seedu.address.logic.commands.CommandTestUtil.VALID_START_DATE_EARLIER;
import static seedu.address.logic.commands.CommandTestUtil.VALID_START_DATE_LATER;
import static seedu.address.logic.commands.CommandTestUtil.VALID_UNUSED_DESCRIPTION;
import static seedu.address.model.event.EventPeriod.DATE_TIME_STRING_FORMATTER;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.EventBuilder.DEFAULT_START_TIME_STRING;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import seedu.address.model.event.exceptions.ConflictingEventException;
import seedu.address.model.event.exceptions.EventNotFoundException;
import seedu.address.testutil.EventBuilder;
import seedu.address.testutil.SingleDayEventListBuilder;



public class SingleDayEventListTest {
    @Test
    public void constructorTest() {
        assertThrows(NullPointerException.class, () -> new SingleDayEventList(null));
    }

    @Test
    public void isEventAddValidTest() {
        Event toBeAdded = new EventBuilder()
                .withStartEndDate(VALID_END_DATE_EARLIER, VALID_START_DATE_LATER).build();
        Event startAfterEndSame = new EventBuilder()
                .withStartEndDate(VALID_START_DATE_EARLIER, VALID_START_DATE_LATER).build();
        Event startSameEndBefore = new EventBuilder()
                .withStartEndDate(VALID_END_DATE_EARLIER, VALID_END_DATE_LATER).build();
        Event startImmediatelyAfter = new EventBuilder()
                .withStartEndDate(VALID_START_DATE_LATER, VALID_END_DATE_LATER).build();
        Event endImmediatelyBefore = new EventBuilder()
                .withStartEndDate(VALID_START_DATE_EARLIER, VALID_END_DATE_EARLIER).build();
        SingleDayEventList dayEventList = new SingleDayEventListBuilder().build();

        assertThrows(NullPointerException.class, () -> dayEventList.isEventAddValid(null));

        assertTrue(dayEventList.isEventAddValid(toBeAdded));

        dayEventList.addEvent(toBeAdded);

        assertFalse(dayEventList.isEventAddValid(toBeAdded));

        assertFalse(dayEventList.isEventAddValid(startAfterEndSame));

        assertFalse(dayEventList.isEventAddValid(startSameEndBefore));

        assertTrue(dayEventList.isEventAddValid(startImmediatelyAfter));

        assertTrue(dayEventList.isEventAddValid(endImmediatelyBefore));
    }

    @Test
    public void addEventTest() {
        Event toBeAdded = new EventBuilder().build();
        Event duplicate = new EventBuilder().build();
        SingleDayEventList dayEventList = new SingleDayEventListBuilder().build();
        dayEventList.addEvent(toBeAdded);

        assertThrows(ConflictingEventException.class, () -> dayEventList.addEvent(toBeAdded));

        assertThrows(ConflictingEventException.class, () -> dayEventList.addEvent(duplicate));
    }

    @Test
    public void containsEventTest() {
        Event toBeAdded = new EventBuilder().build();
        Event duplicateEvent = new EventBuilder().build();
        Event differentDescriptionEvent = new EventBuilder()
                .withDescription(VALID_UNUSED_DESCRIPTION).build();
        Event differentPeriodEvent = new EventBuilder()
                .withStartEndDate(VALID_START_DATE_LATER, VALID_END_DATE_LATER).build();
        SingleDayEventList dayEventList = new SingleDayEventListBuilder().build();

        assertThrows(NullPointerException.class, () -> dayEventList.containsEvent(null));

        assertFalse(dayEventList.containsEvent(toBeAdded));

        dayEventList.addEvent(toBeAdded);

        assertTrue(dayEventList.containsEvent(toBeAdded));

        assertTrue(dayEventList.containsEvent(duplicateEvent));

        assertFalse(dayEventList.containsEvent(differentDescriptionEvent));

        assertFalse(dayEventList.containsEvent(differentPeriodEvent));
    }

    @Test
    public void removeTest() {
        SingleDayEventList dayEventList = new SingleDayEventListBuilder().build();
        Event toBeRemoved = new EventBuilder().build();
        Event duplicateEvent = new EventBuilder().build();
        Event differentDescriptionEvent = new EventBuilder()
                .withDescription(VALID_UNUSED_DESCRIPTION).build();
        Event differentPeriodEvent = new EventBuilder()
                .withStartEndDate(VALID_START_DATE_LATER, VALID_END_DATE_LATER).build();

        assertThrows(NullPointerException.class, () -> dayEventList.remove(null));

        assertThrows(EventNotFoundException.class, () -> dayEventList.remove(toBeRemoved));

        dayEventList.addEvent(toBeRemoved);

        assertThrows(EventNotFoundException.class, () -> dayEventList.remove(differentDescriptionEvent));

        assertThrows(EventNotFoundException.class, () -> dayEventList.remove(differentPeriodEvent));

        assertFalse(dayEventList.getDayEventList().isEmpty());

        dayEventList.remove(toBeRemoved);

        assertTrue(dayEventList.getDayEventList().isEmpty());

        dayEventList.addEvent(toBeRemoved);

        assertFalse(dayEventList.getDayEventList().isEmpty());

        dayEventList.remove(duplicateEvent);

        assertTrue(dayEventList.getDayEventList().isEmpty());
    }

    @Test
    public void getDayEventListTest() {
        SingleDayEventList dayEventList = new SingleDayEventListBuilder().build();
        Event toBeAdded = new EventBuilder().build();

        assertTrue(dayEventList.getDayEventList().isEmpty());

        dayEventList.addEvent(toBeAdded);

        assertFalse(dayEventList.getDayEventList().isEmpty());
        assertTrue(dayEventList.getDayEventList().contains(toBeAdded));
    }

    @Test
    public void eventAtTimeTest() {
        SingleDayEventList dayEventList = new SingleDayEventListBuilder().build();
        Event toBeAdded = new EventBuilder().build();
        dayEventList.addEvent(toBeAdded);
        LocalDateTime expectedDateTime = LocalDateTime.parse(DEFAULT_START_TIME_STRING, DATE_TIME_STRING_FORMATTER);
        assertEquals(dayEventList.eventAtTime(expectedDateTime).get(), toBeAdded);
    }

    @Test
    public void equalsTest() {
        SingleDayEventList dayEventList = new SingleDayEventListBuilder().build();
        SingleDayEventList comparisonList = new SingleDayEventListBuilder().build();
        Event validEvent = new EventBuilder().withDescription(VALID_DESCRIPTION)
                .withStartEndDate(VALID_START_DATE_EARLIER, VALID_END_DATE_EARLIER).build();
        Event nonConflictingEvent = new EventBuilder().withDescription(VALID_UNUSED_DESCRIPTION)
                .withStartEndDate(VALID_START_DATE_LATER, VALID_END_DATE_LATER).build();
        Object notSingleDayEventList = new Object();

        assertTrue(dayEventList.equals(dayEventList));

        assertTrue(dayEventList.equals(comparisonList));

        assertFalse(dayEventList.equals(notSingleDayEventList));

        dayEventList.addEvent(validEvent);

        assertFalse(dayEventList.equals(comparisonList));

        comparisonList.addEvent(nonConflictingEvent);

        assertFalse(dayEventList.equals(comparisonList));

        dayEventList.addEvent(nonConflictingEvent);
        comparisonList.addEvent(validEvent);

        assertTrue(dayEventList.equals(comparisonList));
    }
}
