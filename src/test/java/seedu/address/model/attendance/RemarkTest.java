package seedu.address.model.attendance;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

class RemarkTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Remark(null));
    }

    @Test
    public void constructor_invalidRemark_throwsIllegalArgumentException() {
        String invalidRemark = " ";
        assertThrows(IllegalArgumentException.class, () -> new Remark(invalidRemark));
    }

    @Test
    void isValidRemark() {
        // null remark
        assertThrows(NullPointerException.class, () -> Remark.isValidRemark(null));

        // invalid remark
        assertFalse(Remark.isValidRemark("")); // empty string
        assertFalse(Remark.isValidRemark(" ")); // spaces only
        assertFalse(Remark.isValidRemark(" Remark")); // wrong format
        assertFalse(Remark.isValidRemark("\tTab")); // wrong format
        assertFalse(Remark.isValidRemark("\nNewLine")); // wrong format

        // valid remark
        assertTrue(Remark.isValidRemark("Remark")); // Alphabets
        assertTrue(Remark.isValidRemark("New remark")); // Alphabets with space
        assertTrue(Remark.isValidRemark("123")); // Numerals
        assertTrue(Remark.isValidRemark("123 456")); // Numerals with space
        assertTrue(Remark.isValidRemark("Hello123")); // Alphanumeric
        assertTrue(Remark.isValidRemark("Hello 123")); // Alphanumeric with space
        assertTrue(Remark.isValidRemark("Hello123\\n")); // Alphanumeric with newline
        assertTrue(Remark.isValidRemark("!@#$%^&*()[];',.?")); // Special characters
    }

    @Test
    public void equals() {
        Remark remark = new Remark("Test remark");

        // same values -> returns true
        assertTrue(remark.equals(new Remark("Test remark")));

        // same object -> returns true
        assertTrue(remark.equals(remark));

        // null -> returns false
        assertFalse(remark.equals(null));

        // different types -> returns false
        assertFalse(remark.equals(5.0f));

        // different values -> returns false
        assertFalse(remark.equals(new Remark("Different remark")));
    }

}
