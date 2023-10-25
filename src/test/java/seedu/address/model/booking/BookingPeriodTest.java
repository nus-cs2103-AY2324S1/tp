package seedu.address.model.booking;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import java.time.format.DateTimeParseException;

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
        assertFalse(BookingPeriod.isValidBookingPeriod("2023-01-02 to 2023-01-01"));

        // valid
        assertTrue(BookingPeriod.isValidBookingPeriod("2023-01-01 to 2023-01-02"));

        // catching exception
        assertFalse(BookingPeriod.isValidBookingPeriod(" to "));
        assertFalse(BookingPeriod.isValidBookingPeriod("01/01/23 to 02/01/23"));
    }

    @Test
    public void overlaps() {
        BookingPeriod bookingPeriod = new BookingPeriod("2023-01-01 to 2023-01-02");

        //invalid input
        assertFalse(bookingPeriod.overlaps(null));

        //overlaps -> returns true
        assertTrue(bookingPeriod.overlaps(new BookingPeriod("2023-01-01 to 2023-01-02")));
        assertTrue(bookingPeriod.overlaps(new BookingPeriod("2023-01-02 to 2023-01-03")));
        assertTrue(bookingPeriod.overlaps(new BookingPeriod("2022-12-31 to 2023-01-01")));

        //does not overlap -> return false
        assertFalse(bookingPeriod.overlaps(new BookingPeriod("2023-01-03 to 2023-01-04")));
        assertFalse(bookingPeriod.overlaps(new BookingPeriod("2022-12-30 to 2022-12-31")));
    }

    @Test
    public void equals() {
        BookingPeriod bookingPeriod = new BookingPeriod("2023-01-01 to 2023-01-02");

        // same values -> returns true
        assertTrue(bookingPeriod.equals(new BookingPeriod("2023-01-01 to 2023-01-02")));

        // same object -> returns true
        assertTrue(bookingPeriod.equals(bookingPeriod));

        // null -> returns false
        assertFalse(bookingPeriod.equals(null));

        // different types -> returns false
        assertFalse(bookingPeriod.equals(5.0f));

        // different values -> returns false
        assertFalse(bookingPeriod.equals(new BookingPeriod("2023-01-03 to 2023-01-04")));
    }

    @Test
    public void hashcode() {
        BookingPeriod bookingPeriodOne = new BookingPeriod("2023-01-01 to 2023-01-02");
        BookingPeriod bookingPeriodTwo = new BookingPeriod("2023-01-01 to 2023-01-02");
        BookingPeriod bookingPeriodDiff = new BookingPeriod("2023-01-03 to 2023-01-04");

        assertTrue(bookingPeriodOne.equals(bookingPeriodTwo) && bookingPeriodTwo.equals(bookingPeriodOne));
        assertTrue(bookingPeriodOne.hashCode() == bookingPeriodTwo.hashCode());

        assertFalse(bookingPeriodOne.equals(bookingPeriodDiff) || bookingPeriodDiff.equals(bookingPeriodOne));
        assertFalse(bookingPeriodOne.hashCode() == bookingPeriodDiff.hashCode());
    }
}
