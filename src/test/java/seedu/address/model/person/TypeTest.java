package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class TypeTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Type(null));
    }

    @Test
    public void constructor_null_throwsIllegalArgumentException() {
        String invalidType = "";
        assertThrows(IllegalArgumentException.class, () -> new Type(invalidType));
    }

    @Test
    public void isValidType() {
        // null type
        assertThrows(NullPointerException.class, () -> Type.isValidType(null));

        // blank type
        assertFalse(Type.isValidType("")); // empty string
        assertFalse(Type.isValidType(" ")); // spaces only

        // invalid types
        assertFalse(Type.isValidType("friend"));
        assertFalse(Type.isValidType("classmate"));

        // valid types
        assertTrue(Type.isValidType("client"));
        assertTrue(Type.isValidType("lead"));
    }

    @Test
    public void toStringTest() {
        Type type = new Type("client");
        assertEquals(type.toString(), "client");
    }

    @Test
    public void equals() {
        Type type = new Type("client");

        // same values -> returns true
        assertTrue(type.equals(new Type("client")));

        // same object -> returns true
        assertTrue(type.equals(type));

        // null -> returns false
        assertFalse(type.equals(null));

        // different types -> returns false
        assertFalse(type.equals(5.0f));

        // different values -> returns false
        assertFalse(type.equals(new Type("lead")));
    }
}
