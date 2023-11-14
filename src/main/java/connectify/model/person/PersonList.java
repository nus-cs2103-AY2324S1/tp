package connectify.model.person;

import static connectify.commons.util.CollectionUtil.requireAllNonNull;
import static java.util.Objects.requireNonNull;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import connectify.model.person.exceptions.PersonNotFoundException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Represents a list of people.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class PersonList implements Iterable<Person> {
    private final ObservableList<Person> people = FXCollections.observableArrayList();

    /**
     * Constructs a {@code PeopleList}.
     * {@code people} must be present and all people must be not null.
     *
     * @param personList The list of people.
     */
    public PersonList(PersonList personList) {
        requireAllNonNull(personList);
        people.addAll(personList.people);
    }

    /**
     * Constructs an empty {@code PeopleList}.
     */
    public PersonList() {}

    /**
     * Returns true if the list contains an equivalent person as the given argument.
     *
     * @param toCheck The person to check for in the list.
     * @return true if the list contains an equivalent person as the given argument.
     */
    public boolean contains(Person toCheck) {
        requireNonNull(toCheck);
        return people.stream().anyMatch(toCheck::equals);
    }

    /**
     * Adds a person to the list.
     *
     * @param toAdd The person to add to the list.
     * @return The modified person list.
     */
    public PersonList addPerson(Person toAdd) {
        requireNonNull(toAdd);
        PersonList edited = new PersonList(this);
        edited.people.add(toAdd);
        this.people.add(toAdd);
        return edited;
    }

    /**
     * Removes a person from the list.
     *
     * @param toRemove The person to remove from the list.
     * @return The modified person list.
     */
    public PersonList removePerson(Person toRemove) {
        requireNonNull(toRemove);
        if (!contains(toRemove)) {
            throw new PersonNotFoundException();
        }
        PersonList edited = new PersonList(this);
        edited.people.remove(toRemove);
        return edited;
    }

    /**
     * Replaces the person {@code target} in the list with {@code editedPerson}.
     * @param target
     * @param editedPerson
     * @return
     */
    public PersonList setPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);

        int index = people.indexOf(target);
        if (index == -1) {
            throw new PersonNotFoundException();
        }

        PersonList edited = new PersonList(this);
        edited.people.set(index, editedPerson);
        return edited;
    }

    /**
     * Returns an immutable person list, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     *
     * @return An immutable person list.
     */
    public List<Person> asList() {
        people.sort(new PersonNameComparator());
        return Collections.unmodifiableList(people);
    }

    /**
     * Returns a person list with the elements from {@code list}.
     *
     * @param list The list of {@code Person}s to use.
     * @return The new PeopleList.
     */
    public static PersonList fromList(List<Person> list) {
        PersonList personList = new PersonList();
        personList.people.addAll(list);
        return personList;
    }

    /**
     * Returns true only if both people have the same data fields.
     *
     * @param other The other {@code Person} object to check.
     * @return true only if both people have the same data fields.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof PersonList)) {
            return false;
        }

        PersonList otherPeople = (PersonList) other;
        return otherPeople.asList().equals(asList());
    }

    /**
     * Returns the hashcode of the PersonList.
     * @return The hashcode of the PersonList.
     */
    @Override
    public int hashCode() {
        return Objects.hash(people);
    }

    @Override
    public Iterator<Person> iterator() {
        return people.iterator();
    }

    /**
     * Returns the number of persons in the list.
     *
     * @return The number of persons.
     */
    public int size() {
        return people.size();
    }

    /**
     * Retrieves a person from the list using the provided index.
     *
     * @param index The zero-based index of the person to retrieve.
     * @return The person at the specified index.
     */
    public Person get(int index) {
        return people.get(index);
    }

}
