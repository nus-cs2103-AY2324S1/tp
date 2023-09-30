package seedu.address.model;

import seedu.address.model.booking.Booking;
import seedu.address.model.booking.UniqueBookingList;

/**
 * Booking class holding unique booking list.
 */
public class BookingBook {
    private final UniqueBookingList bookings;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        bookings = new UniqueBookingList();
    }

    public BookingBook() {
    }

    /**
     * Adds a booking to the booking book.
     * The booking must not already exist in the booking book.
     */
    public void addBooking(Booking b) {
        bookings.add(b);
    }
}
