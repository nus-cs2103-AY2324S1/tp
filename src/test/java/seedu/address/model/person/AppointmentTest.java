package seedu.address.model.person;

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
    public void constructorTwo_invalidAppointment_throwsIllegalArgumentException() {
        String invalidAppointment = "";
        assertThrows(IllegalArgumentException.class, () -> new Appointment(invalidAppointment, invalidAppointment));
    }

    @Test
    public void isValidAppointment() {
        // null appoinment
        assertThrows(NullPointerException.class, () -> Appointment.isValidAppointment(null));

        // invalid appointment
        assertFalse(Appointment.isValidAppointment("")); // empty string
        assertFalse(Appointment.isValidAppointment(" ")); // spaces only

        // valid appointment
        assertTrue(Appointment.isValidAppointment("12 AUG 2023 10AM - 11AM"));
        assertTrue(Appointment.isValidAppointment("-")); // one character
        assertTrue(Appointment.isValidAppointment("11-05-2023 1500-1630")); // long address
    }

    @Test
    public void equals() {
        Appointment appointment = new Appointment("Valid Appointment");

        // same values -> returns true
        assertTrue(appointment.equals(new Appointment("Valid Appointment")));

        // same object -> returns true
        assertTrue(appointment.equals(appointment));

        // null -> returns false
        assertFalse(appointment.equals(null));

        // different types -> returns false
        assertFalse(appointment.equals(5.0f));

        // different values -> returns false
        assertFalse(appointment.equals(new Appointment("Other Valid Appointment")));
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
