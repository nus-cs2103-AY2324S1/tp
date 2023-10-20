package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

/**
 * Test class for the ID model.
 */
public class IdTest {

    /**
     * Tests the construction of ID with null input.
     */
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ID(null));
    }

    /**
     * Tests the construction of ID with invalid input.
     */
    @Test
    public void constructor_invalidID_throwsIllegalArgumentException() {
        String invalidID = "12345678";
        assertThrows(IllegalArgumentException.class, () -> new ID(invalidID));
    }

    /**
     * Tests the validity of various ID formats.
     */
    @Test
    public void isValidID() {
        // null ID
        assertThrows(NullPointerException.class, () -> ID.isValidID(null));

        // invalid IDs
        assertFalse(ID.isValidID("")); // empty string
        assertFalse(ID.isValidID(" ")); // spaces only
        assertFalse(ID.isValidID("12345678")); // 8 numbers
        assertFalse(ID.isValidID("A12345678Z")); // 2 letters
        assertFalse(ID.isValidID("A1234567")); // missing last letter
        assertFalse(ID.isValidID("A1234567B A0252")); // only first id is valid
        assertFalse(ID.isValidID("A1234567 A1234567E")); // only second id is valid

        // valid IDs
        assertTrue(ID.isValidID("A1234567E"));
        assertTrue(ID.isValidID("A9876543Z"));
        assertTrue(ID.isValidID("A1234567F A1234567E")); // only second id is valid
    }

    /**
     * Tests the hash code generation for an ID object.
     */
    @Test
    public void hashCode_validID_correctHashCode() {
        ID id1 = new ID("A1234567E");
        ID id2 = new ID("A1234567E");
        assertEquals(id1.hashCode(), id2.hashCode());
    }
}
