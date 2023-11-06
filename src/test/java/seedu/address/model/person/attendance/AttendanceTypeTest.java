package seedu.address.model.person.attendance;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AttendanceTypeTest {
    @Test
    public void isValidAttendanceType_nullName() {
        assertThrows(
                NullPointerException.class,
                () -> AttendanceType.isValidAttendanceType(null));
    }

    @Test
    public void isValidAttendanceType_validNamePresent_mixedCasingName() {
        assertTrue(AttendanceType.isValidAttendanceType("PreSeNt"));
    }

    @Test
    public void isValidAttendanceType_invalidName() {
        assertFalse(AttendanceType.isValidAttendanceType("latte"));
    }

}