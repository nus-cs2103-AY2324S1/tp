package seedu.address.model.event;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DESCRIPTION;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION;
import static seedu.address.logic.commands.CommandTestUtil.VALID_UNUSED_DESCRIPTION;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.model.event.exceptions.InvalidDescriptionException;
import seedu.address.testutil.EventDescriptionBuilder;

public class EventDescriptionTest {
    @Test
    public void constructorTest() {
        assertThrows(NullPointerException.class, () -> new EventDescription(null));

        assertThrows(InvalidDescriptionException.class, () -> new EventDescription(INVALID_DESCRIPTION));
    }

    @Test
    public void isValidTest() {
        assertThrows(NullPointerException.class, () -> EventDescription.isValid(null));

        assertFalse(EventDescription.isValid(INVALID_DESCRIPTION));

        assertTrue(EventDescription.isValid(VALID_DESCRIPTION));

        assertTrue(EventDescription.isValid(VALID_UNUSED_DESCRIPTION));
    }

    @Test
    public void createUnusedDescriptionTest() {
        assertEquals(EventDescription.createUnusedDescription().getDescription(), VALID_UNUSED_DESCRIPTION);

        assertNotEquals(new EventDescriptionBuilder()
                        .changeDescription(VALID_UNUSED_DESCRIPTION).build().getDescription(),
                new EventDescriptionBuilder().build().getDescription());
    }

    @Test
    public void getDescriptionTest() {
        assertEquals(VALID_DESCRIPTION, new EventDescriptionBuilder().changeDescription(
                VALID_DESCRIPTION).build().getDescription());
    }

    @Test
    public void equalsTest() {
        EventDescription validEventDescription = new EventDescriptionBuilder().build();
        EventDescription equivalentValidEventDescription = new EventDescriptionBuilder().build();
        EventDescription nonEquivalentValidEventDescription = new EventDescriptionBuilder()
                .changeDescription(VALID_UNUSED_DESCRIPTION).build();
        Object nonEventDescriptionObject = new Object();

        assertTrue(validEventDescription.equals(validEventDescription));

        assertTrue(validEventDescription.equals(equivalentValidEventDescription));

        assertFalse(validEventDescription.equals(nonEquivalentValidEventDescription));

        assertFalse(validEventDescription.equals(nonEventDescriptionObject));
    }

    @Test
    public void toStringTest() {
        assertEquals(new EventDescriptionBuilder().build().toString(),
                 VALID_DESCRIPTION);

        assertNotEquals(new EventDescriptionBuilder().build().toString(),
                 VALID_UNUSED_DESCRIPTION);
    }
}
