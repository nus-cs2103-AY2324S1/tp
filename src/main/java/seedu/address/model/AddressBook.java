package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.person.Date;
import seedu.address.model.person.UniquePersonList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class AddressBook implements ReadOnlyAddressBook {

    private final UniquePersonList dates;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        dates = new UniquePersonList();
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
     * Replaces the contents of the date list with {@code dates}.
     * {@code dates} must not contain duplicate dates.
     */
    public void setPersons(List<Date> dates) {
        this.dates.setPersons(dates);
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyAddressBook newData) {
        requireNonNull(newData);

        setPersons(newData.getPersonList());
    }

    //// date-level operations

    /**
     * Returns true if a date with the same identity as {@code date} exists in the address book.
     */
    public boolean hasPerson(Date date) {
        requireNonNull(date);
        return dates.contains(date);
    }

    /**
     * Adds a date to the address book.
     * The date must not already exist in the address book.
     */
    public void addPerson(Date p) {
        dates.add(p);
    }

    /**
     * Replaces the given date {@code target} in the list with {@code editedDate}.
     * {@code target} must exist in the address book.
     * The date identity of {@code editedDate} must not be the same as another existing date in the address book.
     */
    public void setPerson(Date target, Date editedDate) {
        requireNonNull(editedDate);

        dates.setPerson(target, editedDate);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removePerson(Date key) {
        dates.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("dates", dates)
                .toString();
    }

    @Override
    public ObservableList<Date> getPersonList() {
        return dates.asUnmodifiableObservableList();
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
        return dates.equals(otherAddressBook.dates);
    }

    @Override
    public int hashCode() {
        return dates.hashCode();
    }
}
