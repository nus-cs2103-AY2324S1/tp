package seedu.address.model.booking;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.model.booking.exceptions.RemarkNotFoundException;

public class RemarkTest {
    @Test
    public void testValidRemark() {
        // Test with a valid remark
        Remark remark = new Remark("This is a valid remark");
        assertEquals("This is a valid remark", remark.value);
    }

    @Test
    public void testNullRemark() {
        // Test with a null remark
        assertThrows(RemarkNotFoundException.class, () -> new Remark(null));
    }

    @Test
    public void testExceedingLength() {
        // Test with a remark exceeding 50 characters
        String longRemark = "This is a long remark exceeding the fifty characters limit, it should throw an exception";
        assertThrows(IllegalArgumentException.class, () -> new Remark(longRemark));
    }

    @Test
    public void testEmptyRemark() {
        // Test with an empty remark
        Remark remark = new Remark(""); // An empty remark is valid
        assertEquals("", remark.value);
    }

    @Test
    public void testMaxLengthRemark() {
        // Test with a remark of exactly 50 characters
        String maxValidRemark = "This is a remark exactly 50 characters long.";
        Remark remark = new Remark(maxValidRemark);
        assertEquals(maxValidRemark, remark.value);
    }

    @Test
    public void testIsValidRemark() {
        // Test for a valid and invalid remark
        assertTrue(Remark.isValidRemark("Valid remark"));
        assertFalse(Remark.isValidRemark("This remark is way too long and should be invalid. Right???"));
    }

    @Test
    public void testEqualsAndHashCode() {
        // Test equals() and hashCode() methods
        Remark remark1 = new Remark("Remark 1");
        Remark remark2 = new Remark("Remark 1");
        Remark remark3 = new Remark("Remark 3");

        assertEquals(remark1, remark2);
        assertEquals(remark1.hashCode(), remark2.hashCode());
        assertNotEquals(remark1, remark3);
        assertNotEquals(remark1.hashCode(), remark3.hashCode());
        assertTrue(remark1.equals(remark2));
        assertFalse(remark1.equals(true));
    }
}
