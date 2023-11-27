package seedu.address.model.booking;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class BookingPeriodTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(IllegalArgumentException.class, () -> new BookingPeriod(null));
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
        assertFalse(BookingPeriod.isValidBookingPeriod("2023-01-02 to 2023-01-01"));

        // valid
        assertTrue(BookingPeriod.isValidBookingPeriod("2023-01-01 08:00 to 2023-01-02 12:00"));

        // catching exception
        assertFalse(BookingPeriod.isValidBookingPeriod(" to "));
        assertFalse(BookingPeriod.isValidBookingPeriod("01/01/23 to 02/01/23 12:00"));
    }

    @Test
    public void overlaps() {
        BookingPeriod bookingPeriod = new BookingPeriod("2023-01-01 08:00 to 2023-01-02 12:00");

        //invalid input
        assertFalse(bookingPeriod.overlaps(null));

        //overlaps -> returns true
        assertTrue(bookingPeriod.overlaps(new BookingPeriod("2023-01-01 08:00 to 2023-01-02 12:00")));
        assertTrue(bookingPeriod.overlaps(new BookingPeriod("2023-01-02 08:00 to 2023-01-03 12:00")));
        assertTrue(bookingPeriod.overlaps(new BookingPeriod("2022-12-31 08:00 to 2023-01-01 12:00")));

        //does not overlap -> return false
        assertFalse(bookingPeriod.overlaps(new BookingPeriod("2023-01-03 08:00 to 2023-01-04 12:00")));
        assertFalse(bookingPeriod.overlaps(new BookingPeriod("2023-01-02 13:00 to 2023-01-04 12:00")));
        assertFalse(bookingPeriod.overlaps(new BookingPeriod("2022-12-30 08:00 to 2022-12-31 12:00")));
        assertFalse(bookingPeriod.overlaps(new BookingPeriod("2022-12-30 08:00 to 2023-01-01 07:00")));
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

    @Test
    public void hashcode() {
        BookingPeriod bookingPeriodOne = new BookingPeriod("2023-01-01 08:00 to 2023-01-02 12:00");
        BookingPeriod bookingPeriodTwo = new BookingPeriod("2023-01-01 08:00 to 2023-01-02 12:00");
        BookingPeriod bookingPeriodDiff = new BookingPeriod("2023-01-03 08:00 to 2023-01-04 12:00");

        assertTrue(bookingPeriodOne.equals(bookingPeriodTwo) && bookingPeriodTwo.equals(bookingPeriodOne));
        assertTrue(bookingPeriodOne.hashCode() == bookingPeriodTwo.hashCode());

        assertFalse(bookingPeriodOne.equals(bookingPeriodDiff) || bookingPeriodDiff.equals(bookingPeriodOne));
        assertFalse(bookingPeriodOne.hashCode() == bookingPeriodDiff.hashCode());
    }

    @Test
    public void testInvalidDateFormat() {
        assertFalse(BookingPeriod.datePartIsValid("2022-01")); // Missing day part
        assertFalse(BookingPeriod.datePartIsValid("2022-01-01-01")); // Extra parts
        assertFalse(BookingPeriod.datePartIsValid("2022/01/01")); // Invalid delimiter
        assertFalse(BookingPeriod.datePartIsValid("2022-January-01")); // Incorrect format
    }

    @Test
    public void testInvalidDateValues() {
        assertFalse(BookingPeriod.datePartIsValid("2022-00-01")); // Invalid month
        assertFalse(BookingPeriod.datePartIsValid("2022-13-01")); // Invalid month
        assertFalse(BookingPeriod.datePartIsValid("2022-01-00")); // Invalid day
        assertFalse(BookingPeriod.datePartIsValid("2022-01-32")); // Invalid day
    }

    @Test
    public void testFebruaryDays() {
        assertFalse(BookingPeriod.datePartIsValid("2021-02-29")); // Not a leap year
        assertTrue(BookingPeriod.datePartIsValid("2020-02-29")); // Leap year
        assertTrue(BookingPeriod.datePartIsValid("2024-02-29")); // Leap year
    }

    @Test
    public void test30DaysMonths() {
        assertTrue(BookingPeriod.datePartIsValid("2022-04-30"));
        assertFalse(BookingPeriod.datePartIsValid("2022-04-31"));
    }

    @Test
    public void test31DaysMonths() {
        assertTrue(BookingPeriod.datePartIsValid("2022-01-31"));
        assertFalse(BookingPeriod.datePartIsValid("2022-06-31"));
    }

    @Test
    public void testSetPeriod() {
        assertThrows(IllegalArgumentException.class, () -> new BookingPeriod("2019-10-18 12:00 to 2020-10-18 12:00")
                .setPeriod("apple to 2022-11-10 12:00"));

        assertThrows(IllegalArgumentException.class, () -> new BookingPeriod("2019-10-18 12:00 to 2020-10-18 12:00")
                .setPeriod("2022-11-10 12:00 to 2019-10-18 12:00"));
    }
}
