package seedu.address.testutil;

import seedu.address.model.BookingsBook;
import seedu.address.model.booking.Booking;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code AddressBook ab = new AddressBookBuilder().withPerson("John", "Doe").build();}
 */
public class AddressBookBuilder {

    private BookingsBook addressBook;

    public AddressBookBuilder() {
        addressBook = new BookingsBook();
    }

    public AddressBookBuilder(BookingsBook addressBook) {
        this.addressBook = addressBook;
    }

    /**
     * Adds a new {@code Person} to the {@code AddressBook} that we are building.
     */
    public AddressBookBuilder withPerson(Booking booking) {
        addressBook.addBooking(booking);
        return this;
    }

    public BookingsBook build() {
        return addressBook;
    }
}
