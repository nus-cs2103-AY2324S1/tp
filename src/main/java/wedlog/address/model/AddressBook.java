package wedlog.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Objects;

import javafx.collections.ObservableList;
import wedlog.address.commons.util.ToStringBuilder;
import wedlog.address.model.person.Guest;
import wedlog.address.model.person.UniqueGuestList;
import wedlog.address.model.person.UniqueVendorList;
import wedlog.address.model.person.Vendor;


/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class AddressBook implements ReadOnlyAddressBook {

    private final UniqueGuestList guests;
    private final UniqueVendorList vendors;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        guests = new UniqueGuestList();
        vendors = new UniqueVendorList();
    }

    public AddressBook() {}

    /**
     * Creates an AddressBook using the Persons in the {@code toBeCopied}
     */
    public AddressBook(ReadOnlyAddressBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the guest list with {@code guests}.
     * {@code guests} must not contain duplicate guests.
     */
    public void setGuests(List<Guest> guests) {
        this.guests.setGuests(guests);
    }

    /**
     * Replaces the contents of the vendor list with {@code vendors}.
     * {@code vendors} must not contain duplicate vendors.
     */
    public void setVendors(List<Vendor> vendors) {
        this.vendors.setVendors(vendors);
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyAddressBook newData) {
        requireNonNull(newData);

        setGuests(newData.getGuestList());
        setVendors(newData.getVendorList());
    }


    //// guest-level operations
    /**
     * Returns true if a guest with the same identity as {@code guest} exists in the address book.
     */
    public boolean hasGuest(Guest guest) {
        requireNonNull(guest);
        return guests.contains(guest);
    }

    /**
     * Adds a guest to the address book.
     * The guest must not already exist in the address book.
     */
    public void addGuest(Guest g) {
        guests.add(g);
    }

    /**
     * Replaces the given guest {@code target} in the list with {@code editedGuest}.
     * {@code target} must exist in the address book.
     * The guest identity of {@code editedGuest} must not be the same as another existing guest in the address book.
     */
    public void setGuest(Guest target, Guest editedGuest) {
        requireNonNull(editedGuest);

        guests.setGuest(target, editedGuest);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removeGuest(Guest key) {
        guests.remove(key);
    }

    /**
     * Returns a {@code RsvpStatistics} with information on the RSVP statuses of guests
     * @return {@code RsvpStatistics}
     */
    public RsvpStatistics getRsvpStatistics() {
        return new RsvpStatistics(guests.getNumGuestsRsvpYes(), guests.getNumGuestsRsvpNo(),
                guests.getNumGuestsRsvpUnknown());
    }

    /**
     * Returns a {@code DietaryRequirementStatistics} with information on the dietary requirements of guests
     * @return {@code DietaryRequirementStatistics}
     */
    public DietaryRequirementStatistics getDietaryRequirementStatistics() {
        return guests.getDietaryRequirementStatistics();
    }

    //// vendor-level operations

    /**
     * Returns true if a vendor with the same identity as {@code vendor} exists in the address book.
     */
    public boolean hasVendor(Vendor vendor) {
        requireNonNull(vendor);
        return vendors.contains(vendor);
    }

    /**
     * Adds a vendor to the address book.
     * The vendor must not already exist in the address book.
     */
    public void addVendor(Vendor v) {
        vendors.add(v);
    }

    /**
     * Replaces the given vendor {@code target} in the list with {@code editedVendor}.
     * {@code target} must exist in the address book.
     * The vendor identity of {@code editedVendor} must not be the same as another existing vendor in the address book.
     */
    public void setVendor(Vendor target, Vendor editedVendor) {
        requireNonNull(editedVendor);

        vendors.setVendor(target, editedVendor);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removeVendor(Vendor key) {
        vendors.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("guests", guests)
                .add("vendors", vendors)
                .toString();
    }

    @Override
    public ObservableList<Guest> getGuestList() {
        return guests.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Vendor> getVendorList() {
        return vendors.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddressBook)) {
            return false;
        }

        AddressBook otherAddressBook = (AddressBook) other;
        return guests.equals(otherAddressBook.guests) && vendors.equals(otherAddressBook.vendors);
    }

    @Override
    public int hashCode() {
        return Objects.hash(guests, vendors);
    }
}
