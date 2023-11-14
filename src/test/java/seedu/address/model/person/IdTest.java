package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class IdTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Id(null));
    }

    @Test
    public void constructor_invalidId_throwsIllegalArgumentException() {
        String invalidId = "";
        assertThrows(IllegalArgumentException.class, () -> new Id(invalidId));
    }

    @Test
    public void isValidId() {
        // null id
        assertThrows(NullPointerException.class, () -> Id.isValidId(null));

        // invalid id
        assertFalse(Id.isValidId("")); // empty string
        assertFalse(Id.isValidId(" ")); // spaces only

        // valid id
        assertTrue(Id.isValidId("S1234567E"));
        assertTrue(Id.isValidId("-")); // one character
        assertTrue(Id.isValidId("t0987654f")); // long ID
        assertTrue(Id.isValidId("S123")); // custom ID 1
        assertTrue(Id.isValidId("S2-1213")); // custom ID 2
    }

    @Test
    public void equals() {
        Id id = new Id("Valid Id");

        // same values -> returns true
        assertTrue(id.equals(new Id("Valid Id")));

        // same object -> returns true
        assertTrue(id.equals(id));

        // null -> returns false
        assertFalse(id.equals(null));

        // different types -> returns false
        assertFalse(id.equals(5.0f));

        // different values -> returns false
        assertFalse(id.equals(new Id("Other Valid Id")));
    }
}
