package seedu.address.model.booking;

import static java.util.Objects.requireNonNull;

import java.util.Iterator;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.booking.exceptions.BookingNotFoundException;
import seedu.address.model.person.exceptions.DuplicatePersonException;

/**
 * A list of bookings in the hotel.
 */
public class UniqueBookingList implements Iterable<Booking> {
    private final ObservableList<Booking> internalList = FXCollections.observableArrayList();
    private final ObservableList<Booking> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    private boolean contains(Booking toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameBooking);
    }

    /**
     * Adds a booking to the list.
     * The booking must not already exist in the list.
     */
    public void add(Booking toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicatePersonException();
        }
        internalList.add(toAdd);
    }

    /**
     * Removes the equivalent booking from the list.
     * The booking must exist in the list.
     */
    public void remove(Booking toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new BookingNotFoundException();
        }
    }

    @Override
    public Iterator<Booking> iterator() {
        return internalList.iterator();
    }
}
