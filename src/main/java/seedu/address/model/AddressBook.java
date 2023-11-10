package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.group.Group;
import seedu.address.model.group.GroupList;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.UniquePersonList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class AddressBook implements ReadOnlyAddressBook {

    private final UniquePersonList persons;
    private final GroupList groups;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     * among constructors.
     */
    {
        persons = new UniquePersonList();
        groups = new GroupList();
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
    public void setPersons(List<Person> persons) {
        this.persons.setPersons(persons);
    }

    /**
     * Replaces the contents of the group list with {@code groups}.
     * {@code groups} must not contain duplicate groups.
     */
    public void setGroups(List<Group> groups) {
        this.groups.setGroups(groups);
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyAddressBook newData) {
        requireNonNull(newData);
        setGroups(newData.getGroupList());
        setPersons(newData.getPersonList());
    }

    //// person-level operations

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return persons.contains(person);
    }

    /**
     * Returns true if a person with the same name exists in the address book.
     *
     * @param personName Name of the person.
     * @return Returns true if a person with the same name exists in the address book.
     */
    public boolean hasPerson(Name personName) {
        requireNonNull(personName);
        for (Person person : persons) {
            if (person.getName().equals(personName)) {
                return true;
            }
        }
        return false;
    }


    /**
     * Adds a person to the address book.
     * The person must not already exist in the address book.
     */
    public void addPerson(Person p) {
        persons.add(p);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removePerson(Person key) {
        persons.remove(key);
    }

    /**
     * Adds a person to a group
     * @param person person to add
     * @param group group to add person to
     */
    public void addPersonToGroup(Person person, Group group) {
        requireNonNull(person);
        requireNonNull(group);
        GroupList groups = person.getGroups();
    }

    public Person getPerson(String personName) throws CommandException {
        // person list get that person object with same name
        return persons.getPerson(personName);
    }

    /**
     * Replaces the given person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    public void setPerson(Person target, Person editedPerson) {
        requireNonNull(editedPerson);

        persons.setPerson(target, editedPerson);
    }

    //// group-level operations

    /**
     * Adds a group to the address book.
     * The group must not already exist in the address book.
     */
    public void addGroup(Group g) {
        groups.add(g);
    }

    /**
     * Removes {@code group} from this {@code AddressBook}.
     * {@code group} must exist in the address book.
     */
    public void removeGroup(Group g) {
        groups.remove(g);
    }

    /**
     * Returns a group with {@code groupName} from this {@code AddressBook}.
     * @param groupName Name of group to search
     * @return The group
     * @throws CommandException If group is not in address book
     */
    public Group getGroup(String groupName) throws CommandException {
        // group list get that group object with same name
        return groups.getGroup(groupName);
    }
    /**
     * Returns a {@code group} from this {@code AddressBook}.
     * @param group Group to search for
     * @return The group
     * @throws CommandException If group is not in address book
     */
    public Group getGroup(Group group) throws CommandException {
        return groups.getGroup(group);
    }

    /**
     * Returns true if a group with the same identity as {@code group} exists in the address book.
     */
    public boolean hasGroup(Group group) {
        requireNonNull(group);
        return groups.contains(group);
    }

    //// util methods

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .add("persons", persons)
            .toString();
    }


    @Override
    public ObservableList<Person> getPersonList() {
        return persons.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Group> getGroupList() {
        return groups.asUnmodifiableObservableList();
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

    public boolean hasEmail(Person toAdd) {
        return persons.containsEmail(toAdd);
    }

    public boolean hasPhone(Person toAdd) {
        return persons.containsPhoneNumber(toAdd);
    }
}
