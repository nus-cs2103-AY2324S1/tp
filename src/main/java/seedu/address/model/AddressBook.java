package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.musician.Musician;
import seedu.address.model.musician.UniquePersonList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class AddressBook implements ReadOnlyAddressBook {

    private final UniquePersonList persons;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        persons = new UniquePersonList();
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
     * Replaces the contents of the musician list with {@code musicians}.
     * {@code musicians} must not contain duplicate musicians.
     */
    public void setPersons(List<Musician> musicians) {
        this.persons.setPersons(musicians);
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyAddressBook newData) {
        requireNonNull(newData);

        setPersons(newData.getPersonList());
    }

    //// musician-level operations

    /**
     * Returns true if a musician with the same identity as {@code musician} exists in the address book.
     */
    public boolean hasPerson(Musician musician) {
        requireNonNull(musician);
        return persons.contains(musician);
    }

    /**
     * Adds a musician to the address book.
     * The musician must not already exist in the address book.
     */
    public void addPerson(Musician p) {
        persons.add(p);
    }

    /**
     * Replaces the given musician {@code target} in the list with {@code editedMusician}.
     * {@code target} must exist in the address book.
     * The musician identity of {@code editedMusician} must not be the same as another existing musician in the address book.
     */
    public void setPerson(Musician target, Musician editedMusician) {
        requireNonNull(editedMusician);

        persons.setPerson(target, editedMusician);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removePerson(Musician key) {
        persons.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("persons", persons)
                .toString();
    }

    @Override
    public ObservableList<Musician> getPersonList() {
        return persons.asUnmodifiableObservableList();
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
        return persons.equals(otherAddressBook.persons);
    }

    @Override
    public int hashCode() {
        return persons.hashCode();
    }
}
