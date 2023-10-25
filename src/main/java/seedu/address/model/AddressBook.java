package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javafx.collections.ObservableList;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.event.Event;
import seedu.address.model.event.EventList;
import seedu.address.model.group.Group;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.UniquePersonList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed for persons (by .isSamePerson comparison)
 */
public class AddressBook implements ReadOnlyAddressBook {

    private final UniquePersonList persons;

    private final EventList events;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        persons = new UniquePersonList();
        events = new EventList();
    }

    public AddressBook() {}

    /**
     * Creates an AddressBook using the Persons in the {@code toBeCopied}
     */
    public AddressBook(ReadOnlyAddressBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //=========== List overwrite operations ===========================================================

    /**
     * Replaces the contents of the person list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setPersons(List<Person> persons) {
        this.persons.setPersons(persons);
    }

    /**
     * Replaces the contents of the event list with {@code events}.
     * @param newEvents the list of events to be replaced with.
     */
    public void setEvents(List<Event> newEvents) {
        this.events.setEvents(newEvents);
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyAddressBook newData) {
        requireNonNull(newData);

        setPersons(newData.getPersonList());
        setEvents(newData.getEventList());
    }

    //=========== person-level operations ===========================================================

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return persons.contains(person);
    }

    public Set<Group> getEmptyGroups(Person person) {
        requireNonNull(person);
        return persons.isLastPersonGroup(person);
    }

    /**
     * Adds a person to the address book.
     * The person must not already exist in the address book.
     */
    public void addPerson(Person p) {
        persons.add(p);
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

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removePerson(Person key) {
        persons.remove(key);
    }

    //=========== event-level operations ===========================================================

    /**
     * Replaces the given event {@code target} in the list with {@code editedEvent}.
     * @param target event to be edited. {@code target} must exist in the address book.
     * @param editedEvent event with the edited details.
     */
    public void setEvent(Event target, Event editedEvent) {
        requireNonNull(editedEvent);
        Event targetEvent = removePersonsInGroups(editedEvent);
        this.events.setEvent(target, targetEvent);
    }

    /**
     * Adds an event to the address book.
     * @param event Event to be added.
     */
    public void addEvent(Event event) {
        // Get persons per group
        Event targetEvent = removePersonsInGroups(event);
        this.events.addEvent(targetEvent);
    }

    public void deleteEvent(Event event) {
        this.events.remove(event);
    }

    /**
     * remove persons from the event if it is already in the group
     * @param event event to operate on
     * @return return the event after finishing
     */
    public Event removePersonsInGroups(Event event) {
        for (Group group: event.getGroups()) {
            Set<Name> personNames = new HashSet<>();
            for (Person person: this.persons) {
                if (person.getGroups().contains(group)) {
                    personNames.add(person.getName());
                }
            }
            event.getNames().removeAll(personNames);
        }
        return event;
    }

    // ========== Group operations ===========================================================

    /**
     * Retrieve a list of Persons in the group
     * @param groupName Group to search by
     * @return List of Persons in the group
     */
    public ObservableList<Person> getPersonsByGroup(Group groupName) {
        return this.persons.getPersonsByGroup(groupName);
    }

    //=========== util methods ===========================================================

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
    public ObservableList<Event> getEventList() {
        return events.asUnmodifiableObservableList();
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
