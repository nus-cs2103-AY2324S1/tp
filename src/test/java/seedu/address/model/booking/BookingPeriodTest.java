package seedu.address.model.booking;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class BookingPeriodTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new BookingPeriod(null));
    }

    @Test
    public void constructor_invalidAddress_throwsIllegalArgumentException() {
        String invalidAddress = "";
        assertThrows(IllegalArgumentException.class, () -> new BookingPeriod(invalidAddress));
    }

    @Test
    public void isBookingPeriod() {
        // null
        assertThrows(NullPointerException.class, () -> BookingPeriod.isValidBookingPeriod(null));

        // invalid
        assertFalse(BookingPeriod.isValidBookingPeriod("")); // empty string
        assertFalse(BookingPeriod.isValidBookingPeriod(" ")); // spaces only

        // valid
        assertTrue(BookingPeriod.isValidBookingPeriod("2023-01-01 08:00 to 2023-01-02 12:00"));
    }

    @Test
    public void equals() {
        BookingPeriod bookingPeriod = new BookingPeriod("2023-01-01 08:00 to 2023-01-02 12:00");

        // same values -> returns true
        assertTrue(bookingPeriod.equals(new BookingPeriod("2023-01-01 08:00 to 2023-01-02 12:00")));

        // same object -> returns true
        assertTrue(bookingPeriod.equals(bookingPeriod));

        // null -> returns false
        assertFalse(bookingPeriod.equals(null));

        // different types -> returns false
        assertFalse(bookingPeriod.equals(5.0f));

        // different values -> returns false
        assertFalse(bookingPeriod.equals(new BookingPeriod("2023-01-03 08:00 to 2023-01-04 12:00")));
    }

    @Test
    public void testIsValidBookingPeriodInvalidFormat() {
        // Create an invalid booking period string with an incorrect format
        String invalidBookingPeriod = "2022-Oct-01 10:00 to 2022-Nov-02 12:00";

        // Check that the method returns false for an invalid format
        assertFalse(BookingPeriod.isValidBookingPeriod(invalidBookingPeriod));

        // Create an invalid booking period string with an impossible date (e.g., February 30)
        String invalidBookingPeriod1 = "2022-02-30 10:00 to 2022-02-30 12:00";

        // Check that the method returns false for an invalid date and time
        assertFalse(BookingPeriod.isValidBookingPeriod(invalidBookingPeriod1));
    }

    @Test
    public void testIsValidDateValidDate() {
        // Valid date and time string
        String validDateTime = "2023-10-24 14:30";
        assertTrue(BookingPeriod.isValidDate(validDateTime));
    }

    @Test
    public void testIsValidDateInvalidDate() {
        // Invalid date and time string with an impossible date (e.g., February 30)
        assertFalse(BookingPeriod.isValidDate("2023-02-30 12:00"));
        assertFalse(BookingPeriod.isValidDate("2023-02-30 24:00"));
        assertFalse(BookingPeriod.isValidDate("2023-02-30 12:00"));
        assertFalse(BookingPeriod.isValidDate("2023-03-32 12:00"));
        assertFalse(BookingPeriod.isValidDate("2023-02-29 12:00"));
        assertFalse(BookingPeriod.isValidDate("2020-02-30 12:00"));
        assertFalse(BookingPeriod.isValidDate("2020-04-32 12:00"));
        assertFalse(BookingPeriod.isValidDate("apple-02-04 12:00"));
        assertFalse(BookingPeriod.isValidDate("2023-02-30"));
    }

    @Test
    public void testIsValidDateInvalidFormat() {
        // Invalid date and time string with incorrect format
        String invalidFormat = "2023-10-24 14:30:00";
        assertFalse(BookingPeriod.isValidDate(invalidFormat));
    }

    @Test
    public void testSetPeriodValidPeriod() {
        // Valid booking period string
        String validBookingPeriod = "2023-10-24 14:30 to 2023-10-24 15:30";
        try {
            BookingPeriod bookingPeriod = new BookingPeriod(validBookingPeriod);
            // No exception should be thrown for a valid period
        } catch (IllegalArgumentException e) {
            // If an exception is thrown, fail the test
            assert false : "Exception thrown for a valid period";
        }
    }

    @Test
    public void testSetPeriodInvalidPeriod() {
        // Invalid booking period string with an impossible date (e.g., February 30)
        String invalidBookingPeriod = "2023-02-30 12:00 to 2023-02-30 13:00";

        try {
            // Creating a BookingPeriod with an invalid period should throw an exception
            BookingPeriod bookingPeriod = new BookingPeriod(invalidBookingPeriod);
            // If no exception is thrown, fail the test
            assert false : "No exception thrown for an invalid period";
        } catch (IllegalArgumentException e) {
            // Exception is expected
        }
    }
}
