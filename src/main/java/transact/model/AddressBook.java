package transact.model;

import static java.util.Objects.requireNonNull;

import java.util.Map;

import javafx.collections.FXCollections;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import transact.commons.util.ToStringBuilder;
import transact.model.person.Person;
import transact.model.person.PersonId;

/**
 * Wraps person data at the address-book level
 * Duplicates are not allowed (by .isSameEntry comparison)
 */
public class AddressBook implements ReadOnlyAddressBook {

    private final UniqueEntryHashmap<PersonId, Person> persons;
    private ObservableList<Person> personList;
    private ObservableList<Person> internalUnmodifiablePersonList;

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
        persons = new UniqueEntryHashmap<>();
        personList = FXCollections.observableArrayList();
        internalUnmodifiablePersonList = FXCollections.unmodifiableObservableList(personList);

        getPersonMap()
                .addListener((MapChangeListener.Change<? extends PersonId, ? extends Person> change) -> {
                    boolean removed = change.wasRemoved();
                    if (removed != change.wasAdded()) {
                        if (removed) {
                            // no put for existing key
                            // remove pair completely
                            personList.remove(change.getValueRemoved());
                        } else {
                            // add new entry
                            personList.add(change.getValueAdded());
                        }
                    } else {
                        // replace existing entry
                        Person entry = change.getValueAdded();

                        for (int i = 0; i < personList.size(); i++) {
                            if (personList.get(i).getPersonId() == entry.getPersonId()) {
                                personList.set(i, entry);
                            }
                        }
                    }
                });
    }

    public AddressBook() {
    }

    /**
     * Creates an AddressBook using the Persons in the {@code toBeCopied}
     */
    public AddressBook(ReadOnlyAddressBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the person list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setPersons(Map<PersonId, Person> persons) {
        this.persons.setEntries(persons);
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyAddressBook newData) {
        requireNonNull(newData);

        setPersons(newData.getPersonMap());
    }

    //// person-level operations

    /**
     * Returns true if a person with the same identity as {@code person} exists in
     * the address book.
     */
    public boolean hasPerson(PersonId personId) {
        requireNonNull(personId);
        return persons.contains(personId);
    }

    /**
     * Adds a person to the address book.
     * The person must not already exist in the address book.
     */
    public void addPerson(Person p) {
        persons.add(p.getPersonId(), p);
    }

    /**
     * Replaces the given person {@code target} in the list with
     * {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another
     * existing person in the address book.
     */
    public void setPerson(PersonId targetId, Person editedPerson) {
        requireNonNull(editedPerson);

        persons.setEntry(targetId, editedPerson);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public Person removePerson(PersonId key) {
        return persons.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("persons", persons)
                .toString();
    }

    @Override
    public ObservableMap<PersonId, Person> getPersonMap() {
        return persons.asUnmodifiableObservableMap();
    }

    @Override
    public ObservableList<Person> getPersonList() {
        return internalUnmodifiablePersonList;
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
