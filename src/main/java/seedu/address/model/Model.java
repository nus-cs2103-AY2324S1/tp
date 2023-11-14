package seedu.address.model;

import java.nio.file.Path;
import java.util.Optional;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.group.Group;
import seedu.address.model.group.tasks.TaskList;
import seedu.address.model.person.Email;
import seedu.address.model.person.Person;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Person> PREDICATE_SHOW_ALL_PERSONS = unused -> true;

    /** {@code Predicate} that always evaluate to true */
    Predicate<Group> PREDICATE_SHOW_ALL_GROUPS = unused -> true;

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
     * Sets the user prefs' address book file path.
     */
    void setAddressBookFilePath(Path addressBookFilePath);

    /**
     * Replaces address book data with the data in {@code addressBook}.
     */
    void setAddressBook(ReadOnlyAddressBook addressBook);

    /** Returns the AddressBook */
    ReadOnlyAddressBook getAddressBook();

    /**
     * Returns true if a person with the same identity as {@code person} exists in StudentConnect.
     */
    boolean hasPerson(Person person);

    /**
     * Deletes the given person.
     * The person must exist in StudentConnect.
     */
    void deletePerson(Person target);

    /**
     * Adds the given person.
     * {@code person} must not already exist in StudentConnect.
     */
    void addPerson(Person person);

    /**
     * Replaces the given person {@code target} with {@code editedPerson}.
     * {@code target} must exist in StudentConnect.
     * The person identity of {@code editedPerson} must not be the same as another existing person in StudentConnect.
     */
    void setPerson(Person target, Person editedPerson);

    /** Returns an unmodifiable view of the filtered person list */
    ObservableList<Person> getFilteredPersonList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Person> predicate);

    /**
     * Adds the given group.
     */
    void addGroup(Group group);

    /**
     * Adds the given {@code Person} to the give {@code Group}.
     *
     * @param person The person to be added.
     * @param group The group that the person will be added to.
     */
    void addPersonToGroup(Person person, Group group);

    /** Returns an unmodifiable view of the filtered group list */
    ObservableList<Group> getFilteredGroupList();

    /**
     * Updates the filter of the filtered group list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredGroupList(Predicate<Group> predicate);

    /**
     * Retrieves a {@code Person} with the specified email.
     *
     * @param email The email associated with the person.
     * @return An {@code Optional} containing the {@code Person} if found, or empty otherwise.
     */
    Optional<Person> getPersonWithEmail(Email email);

    /**
     * Retrieves a {@code Group} with the specified group number.
     *
     * @param number The number associated with the group.
     * @return An {@code Optional} containing the {@code Group} if found, or empty otherwise.
     */
    Optional<Group> getGroupWithNumber(int number);

    /**
     * Returns true if the given person is in a group.
     *
     * @param person The person to be checked.
     */
    boolean personIsInAGroup(Person person);

    /**
     * Returns the group that the given person is in.
     *
     * @param person
     */
    Group getGroupThatPersonIsIn(Person person);

    /**
     * Removes a person from a group.
     *
     * @param person The person to be removed from the group.
     * @param group The group from which the person should be removed.
     */
    void removePersonFromGroup(Person person, Group group);

    /**
     * Adds the given {@code TaskList} to the give {@code Group}.
     *
     * @param taskList The taskList to be added.
     * @param group The group that the task will be added to.
     */
    void addTasksToGroup(TaskList taskList, Group group);

    /**
     * Returns true if a group with the same identity as {@code group} exists in StudentConnect.
     */
    boolean hasGroup(Group group);

    /**
     * Deletes the given group.
     * The group must exist in StudentConnect.
     */
    void deleteGroup(Group group);

}
