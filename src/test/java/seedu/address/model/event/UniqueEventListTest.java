package seedu.address.model.event;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LOCATION_BOXING;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalEvents.AURORA_BOREALIS;
import static seedu.address.testutil.TypicalEvents.BOXING_DAY;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.event.exceptions.DuplicateEventException;
import seedu.address.model.event.exceptions.EventNotFoundException;
import seedu.address.testutil.EventBuilder;

public class UniqueEventListTest {

    private final UniqueEventList uniqueEventList = new UniqueEventList();

    @Test
    public void contains_nullEvent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueEventList.contains(null));
    }

    @Test
    public void contains_eventNotInList_returnsFalse() {
        assertFalse(uniqueEventList.contains(AURORA_BOREALIS));
    }

    @Test
    public void contains_eventInList_returnsTrue() {
        uniqueEventList.createEvent(AURORA_BOREALIS);
        assertTrue(uniqueEventList.contains(AURORA_BOREALIS));
    }

    @Test
    public void contains_eventWithSameIdentityFieldsInList_returnsTrue() {
        uniqueEventList.createEvent(AURORA_BOREALIS);
        Event editedAurora = new EventBuilder(AURORA_BOREALIS).withLocation(VALID_LOCATION_BOXING)
                .build();
        assertFalse(uniqueEventList.contains(editedAurora));
    }

    @Test
    public void add_nullEvent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueEventList.createEvent(null));
    }

    @Test
    public void add_duplicateEvent_throwsDuplicateEventException() {
        uniqueEventList.createEvent(AURORA_BOREALIS);
        assertThrows(DuplicateEventException.class, () -> uniqueEventList.createEvent(AURORA_BOREALIS));
    }

    @Test
    public void setEvent_nullTargetEvent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueEventList.setEvent(null, AURORA_BOREALIS));
    }

    @Test
    public void setEvent_nullEditedEvent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueEventList.setEvent(AURORA_BOREALIS, null));
    }

    @Test
    public void setEvent_targetEventNotInList_throwsEventNotFoundException() {
        assertThrows(EventNotFoundException.class, () -> uniqueEventList.setEvent(AURORA_BOREALIS, AURORA_BOREALIS));
    }

    @Test
    public void setEvent_editedEventIsSameEvent_success() {
        uniqueEventList.createEvent(AURORA_BOREALIS);
        uniqueEventList.setEvent(AURORA_BOREALIS, AURORA_BOREALIS);
        UniqueEventList expectedUniqueEventList = new UniqueEventList();
        expectedUniqueEventList.createEvent(AURORA_BOREALIS);
        assertEquals(expectedUniqueEventList, uniqueEventList);
    }

    @Test
    public void setEvent_editedEventHasSameIdentity_success() {
        uniqueEventList.createEvent(AURORA_BOREALIS);
        Event editedAurora = new EventBuilder(AURORA_BOREALIS).withLocation(VALID_LOCATION_BOXING)
                .build();
        uniqueEventList.setEvent(AURORA_BOREALIS, editedAurora);
        UniqueEventList expectedUniqueEventList = new UniqueEventList();
        expectedUniqueEventList.createEvent(editedAurora);
        assertEquals(expectedUniqueEventList, uniqueEventList);
    }

    @Test
    public void setEvent_editedEventHasDifferentIdentity_success() {
        uniqueEventList.createEvent(AURORA_BOREALIS);
        uniqueEventList.setEvent(AURORA_BOREALIS, BOXING_DAY);
        UniqueEventList expectedUniqueEventList = new UniqueEventList();
        expectedUniqueEventList.createEvent(BOXING_DAY);
        assertEquals(expectedUniqueEventList, uniqueEventList);
    }

    @Test
    public void setEvent_editedEventHasNonUniqueIdentity_throwsDuplicateEventException() {
        uniqueEventList.createEvent(AURORA_BOREALIS);
        uniqueEventList.createEvent(BOXING_DAY);
        assertThrows(DuplicateEventException.class, () -> uniqueEventList.setEvent(AURORA_BOREALIS, BOXING_DAY));
    }

    @Test
    public void remove_nullEvent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueEventList.remove(null));
    }

    @Test
    public void remove_eventDoesNotExist_throwsEventNotFoundException() {
        assertThrows(EventNotFoundException.class, () -> uniqueEventList.remove(AURORA_BOREALIS));
    }

    @Test
    public void remove_existingEvent_removesEvent() {
        uniqueEventList.createEvent(AURORA_BOREALIS);
        uniqueEventList.remove(AURORA_BOREALIS);
        UniqueEventList expectedUniqueEventList = new UniqueEventList();
        assertEquals(expectedUniqueEventList, uniqueEventList);
    }

    @Test
    public void setEvents_nullUniqueEventList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueEventList.setEvents((UniqueEventList) null));
    }

    @Test
    public void setEvents_uniqueEventList_replacesOwnListWithProvidedUniqueEventList() {
        uniqueEventList.createEvent(AURORA_BOREALIS);
        UniqueEventList expectedUniqueEventList = new UniqueEventList();
        expectedUniqueEventList.createEvent(BOXING_DAY);
        uniqueEventList.setEvents(expectedUniqueEventList);
        assertEquals(expectedUniqueEventList, uniqueEventList);
    }

    @Test
    public void setEvents_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueEventList.setEvents((List<Event>) null));
    }

    @Test
    public void setEvents_list_replacesOwnListWithProvidedList() {
        uniqueEventList.createEvent(AURORA_BOREALIS);
        List<Event> eventList = Collections.singletonList(BOXING_DAY);
        uniqueEventList.setEvents(eventList);
        UniqueEventList expectedUniqueEventList = new UniqueEventList();
        expectedUniqueEventList.createEvent(BOXING_DAY);
        assertEquals(expectedUniqueEventList, uniqueEventList);
    }

    @Test
    public void setEvents_listWithDuplicateEvents_throwsDuplicateEventException() {
        List<Event> listWithDuplicateEvents = Arrays.asList(AURORA_BOREALIS, AURORA_BOREALIS);
        assertThrows(DuplicateEventException.class, () -> uniqueEventList.setEvents(listWithDuplicateEvents));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniqueEventList.asUnmodifiableObservableList().remove(0));
    }

    @Test
    public void toStringMethod() {
        assertEquals(uniqueEventList.asUnmodifiableObservableList().toString(), uniqueEventList.toString());
    }
}
