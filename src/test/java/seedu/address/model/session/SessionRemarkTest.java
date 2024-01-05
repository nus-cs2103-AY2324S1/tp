package seedu.address.model.session;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class SessionRemarkTest {

    @Test
    public void isValidSessionRemark_validSessionRemark_returnTrue() {
        // Valid session remarks
        assertTrue(SessionRemark.isValidSessionRemark("This is a valid remark"));
        assertTrue(SessionRemark.isValidSessionRemark("123456"));
        assertTrue(SessionRemark.isValidSessionRemark("Alphanumeric_123"));
    }

    @Test
    public void isValidSessionRemark_invalidSessionRemark_returnFalse() {
        // Invalid session remarks
        assertFalse(SessionRemark.isValidSessionRemark("")); // Empty string
        assertFalse(SessionRemark.isValidSessionRemark("   ")); // Blank string
        assertFalse(SessionRemark.isValidSessionRemark("!@#$")); // Special characters
    }

    @Test
    public void equals() {
        SessionRemark sessionRemark1 = new SessionRemark("1");
        SessionRemark sessionRemark2 = new SessionRemark("2");
        SessionRemark sessionRemark1Copy = new SessionRemark("1");

        // Testing equality with itself
        assertTrue(sessionRemark1.equals(sessionRemark1));

        // Testing equality with an equivalent object
        assertTrue(sessionRemark1.equals(sessionRemark1Copy));

        // Testing equality with a different object
        assertFalse(sessionRemark1.equals(sessionRemark2));

        // Testing equality with null
        assertFalse(sessionRemark1.equals(null));
    }
}
