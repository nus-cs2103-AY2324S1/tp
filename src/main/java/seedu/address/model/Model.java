package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.person.IdentityCode;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.team.Team;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Person> PREDICATE_SHOW_ALL_PERSONS = unused -> true;
    Predicate<Team> PREDICATE_SHOW_ALL_TEAMS = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' address book file path.
     */
    Path getAddressBookFilePath();
    /**
     * Returns the user prefs' Team book file path.
     */
    Path getTeamBookFilePath();
    /**
     * Sets the user prefs' address book file path.
     */
    void setAddressBookFilePath(Path addressBookFilePath);
    /**
     * Sets the user prefs' team book file path.
     */
    void setTeamBookFilePath(Path teamBookFilePath);

    /**
     * Replaces address book data with the data in {@code addressBook}.
     */
    void setAddressBook(ReadOnlyAddressBook addressBook);

    void clearAddressBook();

    /** Returns the AddressBook */
    ReadOnlyAddressBook getAddressBook();
    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    boolean hasPerson(Person person);
    boolean hasPerson(Name name);
    Person getPersonByName(Name name);
    IdentityCode getIdentityCodeByName(Name developerName);
    Name getNameByIdentityCode(IdentityCode developerID);
    boolean invalidAddToTeam(String teamToAddTo);
    /**
     * Deletes the given person.
     * The person must exist in the address book.
     */
    void deletePerson(Person target);

    /**
     * Adds the given person.
     * {@code person} must not already exist in the address book.
     */
    void addPerson(Person person);

    /**
     * Replaces the given person {@code target} with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    void setPerson(Person target, Person editedPerson);

    /** Returns an unmodifiable view of the filtered person list */
    ObservableList<Person> getFilteredPersonList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Person> predicate);


    void addToTeam(String teamToAddTo, Name devToAdd);




    /**
     * Replaces the given team {@code target} in the list with {@code editedTeam}.
     * {@code target} must exist in the address book.
     * The team identity of {@code editedTeam} must not be the same as another existing team in the address book.
     */
    //void setTeams(Team target, Team editedTeam);

    AddressBook getWritableAddressBook();

    //=========== TeamBook related methods =================================================================

    /**
     * Replaces team book data with the data in {@code teamBook}.
     */
    void setTeamBook(ReadOnlyTeamBook teamBook);

    /** Returns the TeamBook */
    ReadOnlyTeamBook getTeamBook();

    /**
     * Returns true if a team with the same identity as {@code teamName} exists in the team book.
     */
    boolean hasTeam(String teamName);

    /**
     * Deletes the given team.
     * The team must exist in the team book.
     */
    void deleteTeam(String teamName);

    /**
     * Adds the given team.
     * {@code team} must not already exist in the team book.
     */
    void addTeam(Team team);

    /**
     * Deletes the given developer from the specified team.
     * The developer and team must exist in the model.
     */
    void deleteDeveloperFromTeam(String teamName, IdentityCode developerIdentityCOde);

    boolean personAlreadyInTeam(String teamToAddTo, Name devToAdd);

    void editTeamName(String originalTeamName, String newTeamName);

    Name getTeamLeaderOfTeam(String teamName);

    void setTeamLeaderOfTeam(String teamName, IdentityCode newTeamLeaderID);

    /** Returns an unmodifiable view of the filtered team list */
    ObservableList<Team> getFilteredTeamList();

    /**
     * Updates the filter of the filtered team list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredTeamList(Predicate<Team> predicate);
}
