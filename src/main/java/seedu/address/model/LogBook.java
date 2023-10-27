package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.person.Person;
import seedu.address.model.person.UniquePersonList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class LogBook implements ReadOnlyAddressBook {

    private final UniquePersonList loggedPersons;

    /*
     * The 'unusual' code block below is a non-static initialization block,
     * sometimes used to avoid duplication
     * between constructors. See
     * https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other
     * ways to avoid duplication
     * among constructors.
     */
    {
        loggedPersons = new UniquePersonList();
        // placeholder data (commented for the sake of test case), constructor should start with an empty list
        // loggedPersons.add(
        //         new Person(new Name("Alex Yeoh"), new Nric("T0123456F"), new Phone("87438807"),
        //                 new Email("alexyeoh@example.com"),
        //                 new Address("Blk 30 Geylang Street 29, #06-40"),
        //                 new Appointment("2023-08-08 10:00 12:00"),
        //                 SampleDataUtil.getMedicalHistorySet(),
        //                 SampleDataUtil.getTagSet("friends")));
        // loggedPersons.add(
        //         new Person(new Name("Bernice Yu"), new Nric("S0123452F"), new Phone("99272758"),
        //                 new Email("berniceyu@example.com"),
        //                 new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
        //                 new Appointment("2023-08-08 12:00 14:00"),
        //                 SampleDataUtil.getMedicalHistorySet("Diabetes"),
        //                 SampleDataUtil.getTagSet("colleagues", "friends")));
        // loggedPersons.add(
        //         new Person(new Name("Charlotte Oliveiro"), new Nric("T0123456Y"), new Phone("93210283"),
        //                 new Email("charlotte@example.com"),
        //                 new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
        //                 null,
        //                 SampleDataUtil.getMedicalHistorySet("AB+ Blood"),
        //                 SampleDataUtil.getTagSet("neighbours")));
    }

    public LogBook() {
    }

    /**
     * Creates an AddressBook using the Persons in the {@code toBeCopied}
     */
    public LogBook(ReadOnlyAddressBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the person list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setPersons(List<Person> persons) {
        this.loggedPersons.setPersons(persons);
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyAddressBook newData) {
        requireNonNull(newData);

        setPersons(newData.getPersonList());
    }

    //// person-level operations

    /**
     * Returns true if a person with the same identity as {@code person} exists in
     * the address book.
     */
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return loggedPersons.contains(person);
    }

    /**
     * Adds a person to the address book.
     * The person must not already exist in the address book.
     */
    public void addPerson(Person p) {
        loggedPersons.add(p);
    }

    /**
     * Replaces the given person {@code target} in the list with
     * {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another
     * existing person in the address book.
     */
    public void setPerson(Person target, Person editedPerson) {
        requireNonNull(editedPerson);

        loggedPersons.setPerson(target, editedPerson);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removePerson(Person key) {
        loggedPersons.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("persons", loggedPersons)
                .toString();
    }

    @Override
    public ObservableList<Person> getPersonList() {
        return loggedPersons.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof LogBook)) {
            return false;
        }

        LogBook otherLogBook = (LogBook) other;
        return loggedPersons.equals(otherLogBook.loggedPersons);
    }

    @Override
    public int hashCode() {
        return loggedPersons.hashCode();
    }
}
