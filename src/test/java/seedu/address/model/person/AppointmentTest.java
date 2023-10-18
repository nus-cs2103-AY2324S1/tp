package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class AppointmentTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Appointment(null));
    }

    @Test
    public void constructor_invalidAppointment_throwsIllegalArgumentException() {
        String invalidAppointment = "";
        assertThrows(IllegalArgumentException.class, () -> new Appointment(invalidAppointment));
    }

    @Test
    public void constructor_validAppointment_success() {
        String validAppointment = "2021-01-01 10:00 11:30";

        Appointment appointment = new Appointment(validAppointment);

        assertEquals("2021-01-01 10:00 11:30", appointment.toString());
    }


    @Test
    public void isValidAppointment() {
        // null appoinment
        assertThrows(NullPointerException.class, () -> Appointment.isValidAppointment(null));

        // invalid appointment
        assertFalse(Appointment.isValidAppointment("")); // empty string
        assertFalse(Appointment.isValidAppointment(" ")); // spaces only

        // valid appointment
        assertTrue(Appointment.isValidAppointment("2023-01-01 11:00 12:00"));
        assertTrue(Appointment.isValidAppointment("2005-01-03 10:00 12:00"));
    }

    @Test
    public void equals() {
        Appointment appointment = new Appointment("2023-01-01 11:00 12:00");

        // same values -> returns true
        assertTrue(appointment.equals(new Appointment("2023-01-01 11:00 12:00")));

        // same object -> returns true
        assertTrue(appointment.equals(appointment));

        // null -> returns false
        assertFalse(appointment.equals(null));

        // different types -> returns false
        assertFalse(appointment.equals(5.0f));

        // different values -> returns false
        assertFalse(appointment.equals(new Appointment("2019-01-01 11:00 12:00")));
    }

    @Test
    public int hashCode() {
        String appointmentValue = "Valid Appointment";
        Appointment appointment = new Appointment(appointmentValue);

        // same value
        assertTrue(appointment.hashCode() == appointmentValue.hashCode());

        // different value
        assertFalse(appointment.hashCode() != appointmentValue.hashCode());

        return 0;
    }
}
