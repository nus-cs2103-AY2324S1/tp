package seedu.address.model.event;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalEvents.EVENT1;
import static seedu.address.testutil.TypicalEvents.EVENT2;
import static seedu.address.testutil.TypicalEvents.EVENT3;
import static seedu.address.testutil.TypicalEvents.EVENT5;

import org.junit.jupiter.api.Test;

class UniqueEventListTest {
    private final UniqueEventList uniqueEventList = new UniqueEventList();

    @Test
    public void contains_nullEvent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueEventList.contains(null));
    }

    @Test
    public void contains_eventNotInList_returnsFalse() {
        assertFalse(uniqueEventList.contains(EVENT5));
    }

    @Test
    public void contains_eventInList_returnsTrue() {
        uniqueEventList.add(EVENT5);
        assertTrue(uniqueEventList.contains(EVENT5));
    }

    @Test
    public void contains_eventNotInListWithMultipleEvents_returnsFalse() {
        uniqueEventList.add(EVENT1);
        uniqueEventList.add(EVENT2);
        uniqueEventList.add(EVENT3);

        assertFalse(uniqueEventList.contains(EVENT5));
    }

    @Test
    public void contains_eventInListWithMultipleEvents_returnsFalse() {
        uniqueEventList.add(EVENT1);
        uniqueEventList.add(EVENT5);
        uniqueEventList.add(EVENT3);

        assertTrue(uniqueEventList.contains(EVENT5));
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
