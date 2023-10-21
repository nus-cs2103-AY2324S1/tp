package seedu.address.model.appointment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.Name;

class AppointmentTest {

    public static final LocalDateTime VALID_DATE_TIME = LocalDateTime.of(2023, 1, 1, 20, 0);
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Appointment(null,
                LocalDateTime.of(2023, 1, 1, 1, 1)));
    }

    @Test
    public void isValidDesc() {
        // null name
        assertThrows(NullPointerException.class, () -> Appointment.isValidDesc(null));

        // invalid Appointment name
        assertFalse(Name.isValidName("")); // empty string
        assertFalse(Name.isValidName(" ")); // spaces only
        assertFalse(Name.isValidName("^")); // only non-alphanumeric characters
        assertFalse(Name.isValidName("peter*")); // contains non-alphanumeric characters

        // valid Appointment name
        assertTrue(Name.isValidName("insurance")); // alphabets only
        assertTrue(Name.isValidName("12345")); // numbers only
        assertTrue(Name.isValidName("review Insurance 1")); // alphanumeric characters
        assertTrue(Name.isValidName("Review Insurance")); // with capital letters
        assertTrue(Name.isValidName("Review Insurance policy 1")); // long names
    }

    @Test
    public void isValidDateFormat() {
        // null date
        assertThrows(NullPointerException.class, () -> Appointment.isValidDateFormat(null));

        // valid date
        assertTrue(Appointment.isValidDateFormat("01-01-2023 20:00"));
    }

    @Test
    public void isValidAppointment() {
        // null appointment string representation
        assertThrows(NullPointerException.class, () -> Appointment.isValidAppointment(null));

        // invalid appointment string format
        assertFalse(Appointment.isValidAppointment("Review Insurance 01-01-2023 20:00"));
        assertFalse(Appointment.isValidAppointment("Review Insurance, ")); // missing date
        assertFalse(Appointment.isValidAppointment("01-01-2023 20:00")); // missing name
        assertFalse(Appointment.isValidAppointment("Review Insurance 01-01-2023 20:00")); // wrong format
        assertFalse(Appointment.isValidAppointment("Review Insurance 1 May 2023 20:00")); // wrong date
        assertFalse(Appointment.isValidAppointment("Review Insurance 01-01-2023 8pm")); // wrong time
        // valid appointment string
        assertTrue(Appointment.isValidAppointment("Review Insurance, 01-01-2023 20:00"));
    }

    @Test
    public void testToStringReturnsExpectedString() {
        Appointment appointment = new Appointment("Review Insurance",
                VALID_DATE_TIME);

        // correct string
        assertEquals(appointment.toString(), "Review Insurance, 01-01-2023 20:00");
    }

    @Test
    public void equals() {
        Appointment appointment = new Appointment("Review Insurance",
                LocalDateTime.of(2023, 1, 1, 20, 0));

        // same values
        assertTrue(appointment.equals(new Appointment("Review Insurance",
                VALID_DATE_TIME)));

        // same object
        assertTrue(appointment.equals(appointment));

        // null -> returns false
        assertFalse(appointment.equals(null));

        // different types -> returns false
        assertFalse(appointment.equals(5.0f));

        // different name values -> returns false
        assertFalse(appointment.equals(new Appointment("Insurance nomination",
                LocalDateTime.of(2023, 1, 1, 20, 0))));

        // different date obj -> returns false
        assertFalse(appointment.equals(new Appointment("Review insurance",
                LocalDateTime.of(2023, 5, 2, 18, 0))));
    }
    @Test
    public void parseAppointmentDate() {
        LocalDateTime expectedDateTime = VALID_DATE_TIME;

        assertEquals(expectedDateTime, Appointment.parseAppointmentDate("01-01-2023 20:00"));

        // invalid day
        assertThrows(DateTimeParseException.class, () -> Appointment.parseAppointmentDate("32-01-2023 20:00"));
        // invalid month
        assertThrows(DateTimeParseException.class, () -> Appointment.parseAppointmentDate("01-13-2023 20:00"));
        // invalid year
        assertThrows(DateTimeParseException.class, () -> Appointment.parseAppointmentDate("01-13-0100 20:00"));
        // invalid hours
        assertThrows(DateTimeParseException.class, () -> Appointment.parseAppointmentDate("01-01-2023 25:00"));
        // invalid minutes
        assertThrows(DateTimeParseException.class, () -> Appointment.parseAppointmentDate("01-01-2023 20:61"));
    }
    @Test
    public void parseAppointmentDescription() {
        Appointment expectedAppointment = new Appointment("Review Insurance",
                VALID_DATE_TIME);

        Appointment testAppointment = Appointment.parseAppointmentDescription("Review Insurance, 01-01-2023 20:00");

        assertEquals(expectedAppointment, testAppointment);
    }
}
