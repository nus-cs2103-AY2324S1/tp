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
        assertTrue(BookingPeriod.isValidBookingPeriod("2023-01-01 to 2023-01-02"));
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
}
