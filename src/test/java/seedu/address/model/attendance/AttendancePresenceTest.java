package seedu.address.model.attendance;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class AttendancePresenceTest {

    @Test
    public void testEnumValues_success() {
        // Test that all enum values are correctly defined
        AttendancePresence present = AttendancePresence.PRESENT;
        AttendancePresence absent = AttendancePresence.ABSENT;

        assertEquals("PRESENT", present.name());
        assertEquals("ABSENT", absent.name());
    }

    @Test
    public void testMessageConstraints_success() {
        assertEquals(
                "Presence field should only contain either 'present' or 'absent', and it should not be blank",
                AttendancePresence.MESSAGE_CONSTRAINTS);
    }
}
