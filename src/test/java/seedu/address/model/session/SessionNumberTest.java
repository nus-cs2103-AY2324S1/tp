package seedu.address.model.session;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class SessionNumberTest {
    @Test
    public void isValidSessionNumber_validSessionNumber_returnsTrue() {
        // Valid session numbers containing only digits
        assertTrue(SessionNumber.isValidSessionNumber("1"));
        assertTrue(SessionNumber.isValidSessionNumber("12345"));
        assertTrue(SessionNumber.isValidSessionNumber("9876543210"));
    }

    @Test
    public void isValidSessionNumber_invalidSessionNumber_returnsFalse() {
        // Invalid session numbers containing non-digits
        assertFalse(SessionNumber.isValidSessionNumber("A1"));
        assertFalse(SessionNumber.isValidSessionNumber("1A"));
        assertFalse(SessionNumber.isValidSessionNumber("12 34"));
        assertFalse(SessionNumber.isValidSessionNumber("1_23"));
        assertFalse(SessionNumber.isValidSessionNumber("12@34"));
        assertFalse(SessionNumber.isValidSessionNumber(" 1234"));
        assertFalse(SessionNumber.isValidSessionNumber("1234 "));
        assertFalse(SessionNumber.isValidSessionNumber(" 1234 "));

        // Invalid session numbers that are blank or empty
        assertFalse(SessionNumber.isValidSessionNumber(""));
        assertFalse(SessionNumber.isValidSessionNumber(" "));
        assertFalse(SessionNumber.isValidSessionNumber("\t"));
        assertFalse(SessionNumber.isValidSessionNumber("\n"));
    }

    @Test
    public void equals() {
        SessionNumber sessionNumber1 = new SessionNumber("1");
        SessionNumber sessionNumber2 = new SessionNumber("2");
        SessionNumber sessionNumber1Copy = new SessionNumber("1");

        // Testing equality with itself
        assertTrue(sessionNumber1.equals(sessionNumber1));

        // Testing equality with an equivalent object
        assertTrue(sessionNumber1.equals(sessionNumber1Copy));

        // Testing equality with a different object
        assertFalse(sessionNumber1.equals(sessionNumber2));

        // Testing equality with null
        assertFalse(sessionNumber1.equals(null));
    }
}
