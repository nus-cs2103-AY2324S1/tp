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
     *
     * @param userPrefs the user prefs
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     *
     * @return the user prefs
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     *
     * @return the gui settings
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     *
     * @param guiSettings the gui settings
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' address book file path.
     *
     * @return the address book file path
     */
    Path getAddressBookFilePath();

    /**
     * Returns the user prefs' Team book file path.
     *
     * @return the team book file path
     */
    Path getTeamBookFilePath();

    /**
     * Sets the user prefs' address book file path.
     *
     * @param addressBookFilePath the address book file path
     */
    void setAddressBookFilePath(Path addressBookFilePath);

    /**
     * Sets the user prefs' team book file path.
     *
     * @param teamBookFilePath the team book file path
     */
    void setTeamBookFilePath(Path teamBookFilePath);

    /**
     * Replaces address book data with the data in {@code addressBook}.
     *
     * @param addressBook the address book
     */
    void setAddressBook(ReadOnlyAddressBook addressBook);

    /**
     * @return Returns the AddressBook
     */
    ReadOnlyAddressBook getAddressBook();

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     *
     * @param person the person
     * @return the boolean
     */
    boolean hasPerson(Person person);

    /**
     * Returns true if a person with given {@code name} exists in the address book.
     *
     * @param name the name
     * @return the boolean
     */
    boolean containsPerson(Name name);

    /**
     * Returns person based on the {@code name}.
     *
     * @param name the name
     * @return the person by name
     */
    Person getPersonByName(Name name);

    /**
     * Gets identity code by name.
     *
     * @param developerName the developer name
     * @return the identity code by name
     */
    IdentityCode getIdentityCodeByName(Name developerName);

    /**
     * Gets name by identity code.
     *
     * @param developerID the developer id
     * @return the name by identity code
     */
    Name getNameByIdentityCode(IdentityCode developerID);

    /**
     * returns true if the team does not exist.
     */
    boolean invalidAddToTeam(String teamToAddTo);

    /**
     * Deletes the given person.
     * The person must exist in the address book.
     *
     * @param target the target
     */
    void deletePerson(Person target);

    /**
     * Adds the given person.
     * {@code person} must not already exist in the address book.
     *
     * @param person the person
     */
    void addPerson(Person person);

    /**
     * Replaces the given person {@code target} with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     *
     * @param target       the target
     * @param editedPerson the edited person
     */
    void setPerson(Person target, Person editedPerson);

    /**
     * @return Returns an unmodifiable view of the filtered person list
     */
    ObservableList<Person> getFilteredPersonList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     *
     * @param predicate the predicate
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Person> predicate);


    /**
     * Add to team.
     *
     * @param teamToAddTo the team to add to
     * @param devToAdd    the dev to add
     */
    void addToTeam(String teamToAddTo, Name devToAdd);

    //=========== TeamBook related methods =================================================================

    /**
     * Replaces team book data with the data in {@code teamBook}.
     *
     * @param teamBook the team book
     */
    void setTeamBook(ReadOnlyTeamBook teamBook);

    /**
     * @return the TeamBook
     */
    ReadOnlyTeamBook getTeamBook();


    /**
     * Returns true if a team with the same identity as {@code teamName} exists in the team book.
     *
     * @param teamName the team name
     * @return the boolean
     */
    boolean hasTeam(String teamName);

    /**
     * Deletes the given team.
     * The team must exist in the team book.
     *
     * @param teamName the team name
     */
    void deleteTeam(String teamName);

    /**
     * Adds the given team.
     * {@code team} must not already exist in the team book.
     *
     * @param team the team
     */
    void addTeam(Team team);

    boolean isLeaderOfTeam(String teamName, Name devToBeAdded);

    /**
     * Deletes the given developer from the specified team.
     * The developer and team must exist in the model.
     *
     * @param teamName              the team name
     * @param developerIdentityCOde the developer identity c ode
     */
    void deleteDeveloperFromTeam(String teamName, IdentityCode developerIdentityCOde);

    /**
     * Person already in team boolean.
     *
     * @param teamToAddTo the team to add to
     * @param devToAdd    the dev to add
     * @return the boolean
     */
    boolean personAlreadyInTeam(String teamToAddTo, Name devToAdd);

    /**
     * Edit team name.
     *
     * @param originalTeamName the original team name
     * @param newTeamName      the new team name
     */
    void editTeamName(String originalTeamName, String newTeamName);

    /**
     * Gets team leader of team.
     *
     * @param teamName the team name
     * @return the team leader of team
     */
    Name getTeamLeaderOfTeam(String teamName);

    IdentityCode getTeamLeaderIdOfTeam(String teamName);

    /**
     * Sets team leader of team.
     *
     * @param teamName        the team name
     * @param newTeamLeaderID the new team leader id
     */
    void setTeamLeaderOfTeam(String teamName, IdentityCode newTeamLeaderID);

    /**
     * @return An unmodifiable view of the filtered team list
     */
    ObservableList<Team> getFilteredTeamList();

    /**
     * Updates the filter of the filtered team list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredTeamList(Predicate<Team> predicate);
}
