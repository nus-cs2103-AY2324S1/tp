package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.Person;
import seedu.address.model.person.Team;

/**
 * Represents the management model for address and team books.
 * Provides functionality to interact and manipulate address and team books, as well as user preferences.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final AddressBook addressBook;
    private final TeamBook teamBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Person> filteredPersons;
    // Assuming teams can be filtered, let's add a FilteredList for Teams
    private final FilteredList<Team> filteredTeams;

    /**
     * Initializes a ModelManager with the given addressBook, teamBook, and userPrefs.
     *
     * @param addressBook the initial address book data.
     * @param teamBook the initial team book data.
     * @param userPrefs the initial user preferences data.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyTeamBook teamBook, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(addressBook, teamBook, userPrefs);

        logger.fine("Initializing with address book: "
                + addressBook + ", team book: " + teamBook + " and user prefs " + userPrefs);

        this.addressBook = new AddressBook(addressBook);
        this.teamBook = new TeamBook(teamBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredPersons = new FilteredList<>(this.addressBook.getPersonList());
        filteredTeams = new FilteredList<>(this.teamBook.getTeamList());
    }

    /**
     * Default constructor that initializes a ModelManager with default values.
     */
    public ModelManager() {
        this.addressBook = new AddressBook();
        this.userPrefs = new UserPrefs();
        this.teamBook = new TeamBook();
        filteredPersons = new FilteredList<>(this.addressBook.getPersonList());
        filteredTeams = new FilteredList<>(this.teamBook.getTeamList());
    }

    //=========== UserPrefs ==================================================================================

    /**
     * Updates the current user preferences.
     *
     * @param userPrefs the new user preferences data.
     */
    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }
    /**
     * Returns the current user preferences.
     *
     * @return the user preferences.
     */
    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }
    /**
     * Retrieves the current GUI settings from user preferences.
     *
     * @return the GUI settings.
     */
    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }
    /**
     * Updates the GUI settings in user preferences.
     *
     * @param guiSettings the new GUI settings.
     */
    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }
    /**
     * Returns the file path to the address book.
     *
     * @return the file path to the address book.
     */
    @Override
    public Path getAddressBookFilePath() {
        return userPrefs.getAddressBookFilePath();
    }
    /**
     * Sets the file path for the address book in user preferences.
     *
     * @param addressBookFilePath the new file path for the address book.
     */
    @Override
    public void setAddressBookFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        userPrefs.setAddressBookFilePath(addressBookFilePath);
    }
    /**
     * Returns the file path to the address book.
     *
     * @return the file path to the address book.
     */
    @Override
    public Path getTeamBookFilePath() {
        return userPrefs.getTeamBookFilePath();
    }
    /**
     * Sets the file path for the address book in user preferences.
     *
     * @param teamBookFilePath the new file path for the address book.
     */
    @Override
    public void setTeamBookFilePath(Path teamBookFilePath) {
        requireNonNull(teamBookFilePath);
        userPrefs.setAddressBookFilePath(teamBookFilePath);
    }
    //=========== AddressBook ================================================================================

    /**
     * Replaces the current address book data with the given address book data.
     *
     * @param addressBook the new address book data.
     */
    @Override
    public void setAddressBook(ReadOnlyAddressBook addressBook) {
        this.addressBook.resetData(addressBook);
    }

    /**
     * Returns the current address book.
     *
     * @return the address book.
     */
    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return addressBook;
    }

    /**
     * Clears all entries in the address book.
     */
    @Override
    public void clearAddressBook() {
        this.addressBook.clear();
    }

    /**
     * Checks if the given person exists in the address book.
     *
     * @param person the person to check.
     * @return true if the person exists, false otherwise.
     */
    @Override
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return addressBook.hasPerson(person);
    }

    /**
     * Deletes the specified person from the address book.
     *
     * @param target the person to be deleted.
     */
    @Override
    public void deletePerson(Person target) {
        addressBook.removePerson(target);
    }

    /**
     * Adds a new person to the address book.
     *
     * @param person the person to be added.
     */
    @Override
    public void addPerson(Person person) {
        addressBook.addPerson(person);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }


    /**
     * Sets (or replaces) a person's details in the address book.
     *
     * @param target the target person whose details are to be replaced.
     * @param editedPerson the new details of the person.
     */
    @Override
    public void setPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);

        addressBook.setPerson(target, editedPerson);
    }
    //=========== Filtered Person List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return filteredPersons;
    }

    /**
     * Updates the predicate used for filtering the list of persons.
     *
     * @param predicate the predicate for filtering.
     */
    @Override
    public void updateFilteredPersonList(Predicate<Person> predicate) {
        requireNonNull(predicate);
        filteredPersons.setPredicate(predicate);
    }

    //    @Override
    //    public boolean equals(Object other) {
    //        if (other == this) {
    //            return true;
    //        }
    //
    //        // instanceof handles nulls
    //        if (!(other instanceof ModelManager)) {
    //            return false;
    //        }
    //
    //        ModelManager otherModelManager = (ModelManager) other;
    //        return addressBook.equals(otherModelManager.addressBook)
    //                && userPrefs.equals(otherModelManager.userPrefs)
    //                && filteredPersons.equals(otherModelManager.filteredPersons);
    //    }

    //=========== TeamBook ================================================================================

    /**
     * Replaces the current team book data with the given team book data.
     *
     * @param teamBook the new team book data to be set.
     */
    @Override
    public void setTeamBook(ReadOnlyTeamBook teamBook) {
        this.teamBook.resetData(teamBook);
    }

    /**
     * Returns the current team book.
     *
     * @return the team book.
     */
    @Override
    public ReadOnlyTeamBook getTeamBook() {
        return teamBook;
    }

    /**
     * Checks if a team with the given team name exists in the team book.
     *
     * @param teamName the name of the team to check.
     * @return true if the team exists, false otherwise.
     */
    @Override
    public boolean hasTeam(String teamName) {
        requireNonNull(teamName);
        return teamBook.hasTeam(teamName);
    }

    /**
     * Deletes a team with the specified team name from the team book.
     *
     * @param teamName the name of the team to be deleted.
     */
    @Override
    public void deleteTeam(String teamName) {
        teamBook.removeTeamByName(teamName);
    }
    /**
     * Adds a new team to the team book.
     *
     * @param team the team to be added.
     */
    @Override
    public void addTeam(Team team) {
        teamBook.addTeam(team);
        updateFilteredTeamList(PREDICATE_SHOW_ALL_TEAMS);
    }


    //=========== Filtered Team List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Team} backed by the internal list of
     * {@code versionedTeamBook}
     */
    @Override
    public ObservableList<Team> getFilteredTeamList() {
        return filteredTeams;
    }

    /**
     * Updates the predicate used for filtering the list of teams.
     *
     * @param predicate the predicate for filtering.
     */
    @Override
    public void updateFilteredTeamList(Predicate<Team> predicate) {
        requireNonNull(predicate);
        filteredTeams.setPredicate(predicate);
    }

    /**
     * Determines if two ModelManager objects are equal.
     *
     * @param other the other object to be compared with.
     * @return true if the two objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ModelManager)) {
            return false;
        }

        ModelManager otherModelManager = (ModelManager) other;
        return addressBook.equals(otherModelManager.addressBook)
                && userPrefs.equals(otherModelManager.userPrefs)
                && teamBook.equals(otherModelManager.teamBook)
                && filteredPersons.equals(otherModelManager.filteredPersons)
                && filteredTeams.equals(otherModelManager.filteredTeams);
    }


}
