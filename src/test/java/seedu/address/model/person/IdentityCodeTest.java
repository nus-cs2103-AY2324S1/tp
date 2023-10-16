package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class IdentityCodeTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new IdentityCode(null));
    }

    @Test
    public void constructor_invalidIdentityCode_throwsIllegalArgumentException() {
        String invalidIdentityCode = "";
        assertThrows(IllegalArgumentException.class, () -> new IdentityCode(invalidIdentityCode));
    }

    @Test
    public void isValidCode() {
        // null identity code
        assertThrows(NullPointerException.class, () -> IdentityCode.isValidCode(null));

        // invalid identity code
        assertFalse(IdentityCode.isValidCode("")); // empty string
        assertFalse(IdentityCode.isValidCode(" ")); // spaces only
        assertFalse(IdentityCode.isValidCode("code")); // non-numeric
        assertFalse(IdentityCode.isValidCode("9312p041")); // alphabets within digits
        assertFalse(IdentityCode.isValidCode("9312.041")); // dots within digits
        assertFalse(IdentityCode.isValidCode("9312,041")); // commas within digits
        assertFalse(IdentityCode.isValidCode("9312-041")); // hyphens within digits

        // valid identity code
        assertTrue(IdentityCode.isValidCode("9312")); // exactly 4 numbers
        assertTrue(IdentityCode.isValidCode("931")); // exactly 3 numbers
        assertTrue(IdentityCode.isValidCode("1")); // one number
    }

    @Test
    public void testEquals() {
        IdentityCode code1 = new IdentityCode("1234");
        IdentityCode code2 = new IdentityCode("1234");
        IdentityCode code3 = new IdentityCode("5678");

        assertTrue(code1.equals(code2));
        assertFalse(code1.equals(code3));
        assertFalse(code2.equals(code3));
    }

    @Test
    public void testHashCode() {
        IdentityCode code1 = new IdentityCode("1234");
        IdentityCode code2 = new IdentityCode("1234");

        assertTrue(code1.hashCode() == code2.hashCode());
    }

}
