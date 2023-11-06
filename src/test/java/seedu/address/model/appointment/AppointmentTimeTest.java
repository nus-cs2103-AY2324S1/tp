package seedu.address.model.appointment;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

public class AppointmentTimeTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AppointmentTime(null, null));
    }

    @Test
    public void constructor_invalidTime_throwsIllegalArgumentException() {
        LocalDateTime invalidStart = LocalDateTime.parse("2023-01-01T10:00");
        LocalDateTime invalidEnd = LocalDateTime.parse("2023-01-01T09:00");
        assertThrows(IllegalArgumentException.class, () -> new AppointmentTime(invalidStart, invalidEnd));
    }

    @Test
    public void isValidAppointmentTime() {
        // null name
        assertThrows(NullPointerException.class, () -> AppointmentTime.isValidAppointmentTime(null, null));

        LocalDateTime validStart = LocalDateTime.parse("2023-01-01T10:00");
        LocalDateTime validEnd = LocalDateTime.parse("2023-01-01T11:00");

        // invalid time
        assertFalse(AppointmentTime.isValidAppointmentTime(validStart, validStart)); // same start and end
        assertFalse(AppointmentTime.isValidAppointmentTime(validEnd, validStart)); // start is later than end

        // valid name
        assertTrue(AppointmentTime.isValidAppointmentTime(validStart, validEnd));
    }

    @Test
    public void equals() {
        LocalDateTime validStart = LocalDateTime.parse("2023-01-01T10:00");
        LocalDateTime validEnd = LocalDateTime.parse("2023-01-01T11:00");
        AppointmentTime time = new AppointmentTime(validStart, validEnd);

        // same values -> returns true
        assertTrue(time.equals(new AppointmentTime(validStart, validEnd)));

        // same object -> returns true
        assertTrue(time.equals(time));

        // null -> returns false
        assertFalse(time.equals(null));

        // different types -> returns false
        assertFalse(time.equals(5.0f));

        // different values -> returns false
        assertFalse(time.equals(new AppointmentTime(
                LocalDateTime.parse("2023-01-01T12:00"), LocalDateTime.parse("2023-01-01T14:00"))));
    }
}
