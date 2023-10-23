package seedu.ccacommander.model.event;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.ccacommander.logic.commands.CommandTestUtil.VALID_DATE_BOXING;
import static seedu.ccacommander.logic.commands.CommandTestUtil.VALID_LOCATION_BOXING;
import static seedu.ccacommander.logic.commands.CommandTestUtil.VALID_NAME_BOXING;
import static seedu.ccacommander.testutil.TypicalEvents.AURORA_BOREALIS;
import static seedu.ccacommander.testutil.TypicalEvents.BOXING_DAY;

import org.junit.jupiter.api.Test;

import seedu.ccacommander.testutil.EventBuilder;

public class EventTest {

    @Test
    public void isSameEvent() {
        // same object -> returns true
        assertTrue(AURORA_BOREALIS.isSameEvent(AURORA_BOREALIS));

        // null -> returns false
        assertFalse(AURORA_BOREALIS.isSameEvent(null));

        // same name, all other attributes different -> returns false
        Event editedAurora = new EventBuilder(AURORA_BOREALIS).withDate(VALID_DATE_BOXING)
                .withLocation(VALID_LOCATION_BOXING).build();
        assertFalse(AURORA_BOREALIS.isSameEvent(editedAurora));

        // different name, all other attributes same -> returns false
        editedAurora = new EventBuilder(AURORA_BOREALIS).withName(VALID_NAME_BOXING).build();
        assertFalse(AURORA_BOREALIS.isSameEvent(editedAurora));

        // name differs in case, all other attributes same -> returns true
        // as name will be converted to PascalCase with space in between
        Event editedBoxing = new EventBuilder(BOXING_DAY).withName(VALID_NAME_BOXING.toLowerCase()).build();
        assertTrue(BOXING_DAY.isSameEvent(editedBoxing));

        // name has trailing spaces, all other attributes same -> returns true
        // as name will trim trailing space
        String nameWithTrailingSpaces = VALID_NAME_BOXING + " ";
        editedBoxing = new EventBuilder(BOXING_DAY).withName(nameWithTrailingSpaces).build();
        assertTrue(BOXING_DAY.isSameEvent(editedBoxing));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Event auroraCopy = new EventBuilder(AURORA_BOREALIS).build();
        assertTrue(AURORA_BOREALIS.equals(auroraCopy));

        // same object -> returns true
        assertTrue(AURORA_BOREALIS.equals(AURORA_BOREALIS));

        // null -> returns false
        assertFalse(AURORA_BOREALIS.equals(null));

        // different type -> returns false
        assertFalse(AURORA_BOREALIS.equals(5));

        // different event -> returns false
        assertFalse(AURORA_BOREALIS.equals(BOXING_DAY));

        // different name -> returns false
        Event editedAurora = new EventBuilder(AURORA_BOREALIS).withName(VALID_NAME_BOXING).build();
        assertFalse(AURORA_BOREALIS.equals(editedAurora));

        // different date -> returns false
        editedAurora = new EventBuilder(AURORA_BOREALIS).withDate(VALID_DATE_BOXING).build();
        assertFalse(AURORA_BOREALIS.equals(editedAurora));

        // different location -> returns false
        editedAurora = new EventBuilder(AURORA_BOREALIS).withLocation(VALID_LOCATION_BOXING).build();
        assertFalse(AURORA_BOREALIS.equals(editedAurora));

    }
    @Test
    public void test_equalObjectsHaveSameHashCode() {
        Event auroraCopy1 = new EventBuilder(AURORA_BOREALIS).build();
        Event auroraCopy2 = new EventBuilder(AURORA_BOREALIS).build();
        assertEquals(auroraCopy1.hashCode(), auroraCopy1.hashCode());
        assertEquals(auroraCopy1.hashCode(), auroraCopy2.hashCode());
    }
    @Test
    public void toStringMethod() {
        String expected = Event.class.getCanonicalName() + "{name=" + AURORA_BOREALIS.getName()
                + ", date=" + AURORA_BOREALIS.getDate() + ", location=" + AURORA_BOREALIS.getLocation() + "}";
        assertEquals(expected, AURORA_BOREALIS.toString());
    }
}
