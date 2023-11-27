package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.booking.Booking;
import seedu.address.model.booking.UniqueBookingList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class BookingsBook implements ReadOnlyBookingsBook {

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

    public BookingsBook() {}

    /**
     * Creates an AddressBook using the Persons in the {@code toBeCopied}
     */
    public BookingsBook(ReadOnlyBookingsBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the person list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setBookings(List<Booking> bookings) {
        this.bookings.setBookings(bookings);
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyBookingsBook newData) {
        requireNonNull(newData);

        setBookings(newData.getPersonList());
    }

    //// person-level operations

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    public boolean hasBooking(Booking booking) {
        requireNonNull(booking);
        return bookings.contains(booking);
    }

    /**
     * Adds a person to the address book.
     * The person must not already exist in the address book.
     */
    public void addBooking(Booking p) {
        bookings.add(p);
    }

    /**
     * Replaces the given person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    public void setBooking(Booking target, Booking editedBooking) {
        requireNonNull(editedBooking);

        bookings.setBooking(target, editedBooking);
    }
    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removePerson(Booking key) {
        bookings.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("persons", bookings)
                .toString();
    }

    @Override
    public ObservableList<Booking> getPersonList() {
        return bookings.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof BookingsBook)) {
            return false;
        }

        BookingsBook otherAddressBook = (BookingsBook) other;
        return bookings.equals(otherAddressBook.bookings);
    }

    @Override
    public int hashCode() {
        return bookings.hashCode();
    }
}
