package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.model.appointment.AppointmentTime;

public class AppointmentTimeTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AppointmentTime(null));
    }

    @Test
    public void constructor_invalidAppointmentTime1_throwsIllegalArgumentException() {
        String invalidAppointment1 = "S458Z";
        assertThrows(IllegalArgumentException.class, () -> new Ic(invalidAppointment1));
    }

    @Test
    public void constructor_invalidIc2_throwsIllegalArgumentException() {
        String invalidAppointment2 = "2023/10/15 12:00";
        assertThrows(IllegalArgumentException.class, () -> new Ic(invalidAppointment2));
    }

    @Test
    public void isValidAppointmentTime() {
        // null appointment time
        assertThrows(NullPointerException.class, () -> AppointmentTime.isValidAppointmentTime(null));

        // invalid appointment time
        assertFalse(AppointmentTime.isValidAppointmentTime("")); // empty string
        assertFalse(AppointmentTime.isValidAppointmentTime(" ")); // spaces only
        assertFalse(AppointmentTime.isValidAppointmentTime("2023-10-20")); // only date no time
        assertFalse(AppointmentTime.isValidAppointmentTime("2023/12/12 10:00")); // does not follow the correct format

        // valid appointment time
        assertTrue(AppointmentTime.isValidAppointmentTime("2023-10-20 12:00")); // follows yyyy-mm-dd hh:mm format
    }

    @Test
    public void equals() {
        AppointmentTime appointmentTime = new AppointmentTime("2023-10-20 12:00");

        // same values -> returns true
        assertTrue(appointmentTime.equals(new AppointmentTime("2023-10-20 12:00")));

        // same object -> returns true
        assertTrue(appointmentTime.equals(appointmentTime));

        // null -> returns false
        assertFalse(appointmentTime.equals(null));

        // different types -> returns false
        assertFalse(appointmentTime.equals(5.0f));

        // different values -> returns false
        assertFalse(appointmentTime.equals(new AppointmentTime("2023-10-20 13:00")));
    }

    @Test
    public void compareTo() {
        AppointmentTime appointmentTime1 = new AppointmentTime("2023-10-20 12:00");
        AppointmentTime appointmentTime2 = new AppointmentTime("2023-10-20 13:00");
        AppointmentTime appointmentTime3 = new AppointmentTime("2023-10-20 11:00");

        // compare to itself -> return 0
        assertEquals(0, appointmentTime1.compareTo(appointmentTime1));

        // same value -> return 0
        assertEquals(0, appointmentTime1.compareTo(new AppointmentTime("2023-10-20 12:00")));

        // different values -> compare correctly
        assertTrue(appointmentTime1.compareTo(appointmentTime2) < 0);
        assertTrue(appointmentTime1.compareTo(appointmentTime3) > 0);
    }
}
