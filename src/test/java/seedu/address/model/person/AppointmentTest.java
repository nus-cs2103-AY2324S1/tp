package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.enums.InputSource;
import seedu.address.model.person.exceptions.BadAppointmentFormatException;

// TODO: Update AppointmentTest to handle new implementation of Appointment
public class AppointmentTest {
    private static final String VALID_APPOINTMENT_USER_INPUT_1 = "1-1-2021, 9:00 11:30";
    private static final String VALID_APPOINTMENT_USER_INPUT_2 = "01-Jan-2021 09:00 11:30";
    private static final String VALID_APPOINTMENT_USER_INPUT_3 = "01-Jan-2021,9:00,11:30";
    private static final String VALID_APPOINTMENT_USER_INPUT_4 = "01-01-2021,9:00, 11:30";
    private static final String VALID_APPOINTMENT_USER_INPUT_5 = "02-01-2021,9:00, 11:30";
    private static final String VALID_APPOINTMENT_USER_INPUT_6 = "01-01-2021,13:00, 13:30";
    private static final String VALID_APPOINTMENT_STORAGE = "01-Jan-2021, 09:00 to 11:30";

    private static final String INVALID_MISSING_FIELD = "1-Jan-2023 12:00";
    private static final String INVALID_BAD_YEAR = "1-Jan-23 12:00 15:00";
    private static final String INVALID_BAD_MONTH = "1-20-2023 12:00 15:00";
    private static final String INVALID_BAD_DAY = "132-Jan-2023 12:00 15:00";
    private static final String INVALID_BAD_MIN = "1-Jan-2023 12:60 15:00";
    private static final String INVALID_BAD_HOUR = "1-Jan-2023 25:00 15:00";
    private static final String INVALID_BAD_START = "1-Jan-2023 16:00 15:00";
    @Test
    public void factory_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> Appointment.of(null, InputSource.STORAGE));
        assertThrows(NullPointerException.class, () -> Appointment.of(null, InputSource.USER_INPUT));
    }

    @Test
    public void factory_invalidAppointment_throwsIllegalArgumentException() {
        String invalidAppointment = " ";
        assertThrows(IllegalArgumentException.class, () -> Appointment.of(invalidAppointment, InputSource.STORAGE));
        assertThrows(IllegalArgumentException.class, () -> Appointment.of(invalidAppointment, InputSource.USER_INPUT));
        assertThrows(
                IllegalArgumentException.class, () -> Appointment.of(INVALID_MISSING_FIELD, InputSource.USER_INPUT));
    }

    @Test
    public void factory_validAppointment_success() throws Exception {
        Appointment userInput1 = Appointment.of(VALID_APPOINTMENT_USER_INPUT_1, InputSource.USER_INPUT);
        Appointment userInput2 = Appointment.of(VALID_APPOINTMENT_USER_INPUT_2, InputSource.USER_INPUT);
        Appointment userInput3 = Appointment.of(VALID_APPOINTMENT_USER_INPUT_3, InputSource.USER_INPUT);
        Appointment userInput4 = Appointment.of(VALID_APPOINTMENT_USER_INPUT_4, InputSource.USER_INPUT);
        Appointment storageInput = Appointment.of(VALID_APPOINTMENT_STORAGE, InputSource.STORAGE);

        assertEquals(userInput1, userInput2);
        assertEquals(userInput2, userInput3);
        assertEquals(userInput3, userInput4);
        assertEquals(userInput4, storageInput);

        assertEquals("01-Jan-2021, 09:00 to 11:30", userInput1.toString());
    }

    @Test
    public void factory_badAppointment_throwsBadAppointmentException() throws Exception {
        assertThrows(
                BadAppointmentFormatException.class, () -> Appointment.of(INVALID_BAD_YEAR, InputSource.USER_INPUT));
        assertThrows(
                BadAppointmentFormatException.class, () -> Appointment.of(INVALID_BAD_MONTH, InputSource.USER_INPUT));
        assertThrows(
                BadAppointmentFormatException.class, () -> Appointment.of(INVALID_BAD_DAY, InputSource.USER_INPUT));
        assertThrows(
                BadAppointmentFormatException.class, () -> Appointment.of(INVALID_BAD_MIN, InputSource.USER_INPUT));
        assertThrows(
                BadAppointmentFormatException.class, () -> Appointment.of(INVALID_BAD_HOUR, InputSource.USER_INPUT));
        assertThrows(
                BadAppointmentFormatException.class, () -> Appointment.of(INVALID_BAD_START, InputSource.USER_INPUT));
    }

    @Test
    public void isValidAppointmentDelimit() {
        // null appointment
        assertThrows(NullPointerException.class, () ->
                Appointment.isValidAppointmentDelimit(null, InputSource.USER_INPUT));
        assertThrows(NullPointerException.class, () ->
                Appointment.isValidAppointmentDelimit(null, InputSource.STORAGE));

        // invalid delimits for appointment
        assertFalse(Appointment.isValidAppointmentDelimit("", InputSource.USER_INPUT)); // empty string
        assertFalse(Appointment.isValidAppointmentDelimit("", InputSource.STORAGE));
        assertFalse(Appointment.isValidAppointmentDelimit(" ", InputSource.USER_INPUT)); // spaces only
        assertFalse(Appointment.isValidAppointmentDelimit(" ", InputSource.STORAGE));
        assertFalse(Appointment.isValidAppointmentDelimit("a, b, c, d", InputSource.USER_INPUT)); // too many
        assertFalse(Appointment.isValidAppointmentDelimit("a, b to c to d", InputSource.STORAGE));
    }

    @Test
    public void parseAppointmentIfExists_emptyOptional_returnsNull() throws ParseException {
        Optional<String> emptyAppointment = Optional.empty();
        assertNull(ParserUtil.parseAppointmentIfExists(emptyAppointment));
    }

    @Test
    public void equals() throws Exception {
        Appointment appointment = new Appointment(VALID_APPOINTMENT_USER_INPUT_1);

        // same values -> returns true
        assertTrue(appointment.equals(new Appointment(VALID_APPOINTMENT_USER_INPUT_1)));

        // same object -> returns true
        assertTrue(appointment.equals(appointment));

        // null -> returns false
        assertFalse(appointment.equals(null));

        // different types -> returns false
        assertFalse(appointment.equals(5.0f));

        // different values -> returns false
        assertFalse(appointment.equals(new Appointment(VALID_APPOINTMENT_USER_INPUT_5)));
        assertFalse(appointment.equals(new Appointment(VALID_APPOINTMENT_USER_INPUT_6)));
    }

    @Test
    public void hashCodeTest() throws Exception {
        LocalDate date = LocalDate.of(2021, 1, 1);
        LocalTime start = LocalTime.of(9, 0);
        LocalTime end = LocalTime.of(11, 30);
        Appointment appointment = Appointment.of(VALID_APPOINTMENT_STORAGE, InputSource.STORAGE);

        // same value
        assertTrue(appointment.hashCode() == Objects.hash(date, start, end));

        // different value
        assertFalse(appointment.hashCode() != Objects.hash(date, start, end));
    }
}
