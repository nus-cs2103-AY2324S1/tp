package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.person.Person;

import java.util.ArrayList;
import java.util.List;


/**
 * Represents an AddressBook with an undo/redo history
 */
public class VersionedAddressBook extends AddressBook{

    private ArrayList<VersionedAddressBook> addressBookStateList;
    private int currentStatePointer;
    private VersionedAddressBook currentState;

    public VersionedAddressBook() {
        super();
        addressBookStateList = new ArrayList<>();
        VersionedAddressBook initState = new VersionedAddressBook(this);
        addressBookStateList.add(initState);
        VersionedAddressBook ab = addressBookStateList.get(currentStatePointer);
        currentState = new VersionedAddressBook(ab);
        currentStatePointer = 0;
    }

    public VersionedAddressBook(ReadOnlyAddressBook toBeCopied) {
        super(toBeCopied);
    }

    // Commits a copy of current address book so that the saved state is immutable
    public void commit() {
        VersionedAddressBook savedState = new VersionedAddressBook(currentState);
        addressBookStateList.add(savedState);
        currentStatePointer++;
    }

    public void undo() {
        currentStatePointer--;
        currentState = getCurrentAddressBook();
    }

    public void redo() {
        currentStatePointer++;
        currentState = getCurrentAddressBook();
    }

    public boolean canUndo() {
        return currentStatePointer > 0;
    }

    public boolean canRedo() {
        return addressBookStateList.size() > 1;
    }

    // Returns copy of current address book so that the saved state is immutable
    public VersionedAddressBook getCurrentAddressBook() {
        VersionedAddressBook ab = addressBookStateList.get(currentStatePointer);
        return new VersionedAddressBook(ab);
    }

    /**
     * Adds a person to the current address book.
     * The person must not already exist in the address book.
     */
    public void currentAddressBookAddPerson(Person p) {
        currentState.addPerson(p);
    }

    /**
     * Replaces the contents of the person list of the current addressbook with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setCurrentAddressBookPersons(List<Person> persons) {
        currentState.setPersons(persons);
    }

    /**
     * Removes {@code key} from this current address book {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void currentAddressBookRemovePerson(Person key) {
        currentState.removePerson(key);
    }

    /**
     * Resets the existing data of the current addressBook {@code AddressBook} with {@code newData}.
     */
    public void resetCurrentAddressBookData(ReadOnlyAddressBook newData) {
        currentState.resetData(newData);
    }

    /**
     * Returns true if a person in the current addressbook with the
     * same identity as {@code person} exists in the address book.
     */
    public boolean currentAddressBookHasPerson(Person person) {
        return currentState.hasPerson(person);
    }

    /**
     * Returns true if a person in the current addressbook with the
     * same schedule as {@code person} exists in the address book.
     * @param person
     * @return boolean for whether a person has a clashing schedule
     */
    public boolean currentAddressBookHasDate(Person person) {
        return currentState.hasDate(person);
    }

    /**
     * Replaces the given person in the current addressbook {@code target}
     * in the list with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    public void currentAddressBookSetPerson(Person target, Person editedPerson) {
        currentState.setPerson(target, editedPerson);
    }

    //// util methods

    public String currentAddressBookToString() {
        return currentState.toString();
    }

    public ObservableList<Person> currentAddressBookGetPersonList() {
        return currentState.getPersonList();
    }
}
