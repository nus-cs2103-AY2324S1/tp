package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
//import java.util.TreeMap;

import javafx.collections.ObservableList;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.person.Person;
import seedu.address.model.person.Team;
import seedu.address.model.person.UniquePersonList;
import seedu.address.model.person.UniqueTeamList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class AddressBook implements ReadOnlyAddressBook {

    private final UniquePersonList persons;

    private final UniqueTeamList teams;
    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        persons = new UniquePersonList();
        teams = new UniqueTeamList();
    }

    public AddressBook() {}

    /**
     * Creates an AddressBook using the Persons in the {@code toBeCopied}
     */
    public AddressBook(ReadOnlyAddressBook toBeCopied, List<Team> teamStructure) {
        this();
        resetData(toBeCopied, teamStructure);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the person list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setPersons(List<Person> persons) {
        this.persons.setPersons(persons);
    }
    public void setTeamsStructure(List<Team> teams) {
        this.teams.setTeams(teams);
    }

    /**
     * Clears all existing data in the address book.
     */
    public void clear() {
        AddressBook empty = new AddressBook();
        setPersons(empty.getPersonList());
        setTeamsStructure(empty.getTeamList());
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyAddressBook newData, List<Team> newStructure) {
        requireNonNull(newData);
        requireNonNull(newStructure);

        setPersons(newData.getPersonList());
        setTeamsStructure(newStructure);
    }

    //// person-level operations-------------------------------------------------------------------------------------

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return persons.contains(person);
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

    public Person getPersonByHashCode(int hash) {
        return persons.getPersonByHashCode(hash);
    }


    //// teams-level operations----------------------------------------------------------------------------------------

    /**
     * @param team TODO(I added to pass checkstyle but im not sure what it is)
     * @return a boolean indicating whether there is any existing team in the teams of address book
     */
    public boolean hasTeam(Team team) {
        requireNonNull(team);
        return teams.contains(team);
    }

    /**
     * Adds a team to the team structure.
     * The team must not already exist in the teams of address book.
     */
    public void addTeam(Team t) {
        teams.add(t);
    }

    /**
     * Removes {@code key} from team structure.
     * {@code key} must exist in the teams of address book.
     */
    public void removeTeam(Team key) {
        teams.remove(key);
    }



    /**
     * Replaces the given team {@code target} in the list with {@code editedTeam}.
     * {@code target} must exist in the teams of address book.
     * The team identity of {@code editedTeam} must not be the same as another existing Team in the
     * teams of address book.
     */
    public void setTeam(Team target, Team editedTeam) {
        requireNonNull(editedTeam);

        teams.setTeam(target, editedTeam);


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
    public ObservableList<Team> getTeamList() {
        return teams.asUnmodifiableObservableList();
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
        return persons.equals(otherAddressBook.persons) && teams.equals(otherAddressBook.teams);
    }


    //todo: see if we need to incorporate the hash code of teams
    @Override
    public int hashCode() {

        //return persons.hashCode();
        return Objects.hash(persons, teams);
    }
}
