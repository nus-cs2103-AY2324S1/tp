package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.group.Group;
import seedu.address.model.group.tasks.TaskList;
import seedu.address.model.person.Email;
import seedu.address.model.person.Person;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final AddressBook addressBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Person> filteredPersons;
    private final FilteredList<Group> filteredGroups;


    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     *
     * @param addressBook The initial address book data.
     * @param userPrefs The user preferences.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs);

        this.addressBook = new AddressBook(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredPersons = new FilteredList<>(this.addressBook.getPersonList());
        filteredGroups = new FilteredList<>(this.addressBook.getGroupList());
    }

    /**
     * Default constructor for ModelManager.
     */
    public ModelManager() {
        this(new AddressBook(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getAddressBookFilePath() {
        return userPrefs.getAddressBookFilePath();
    }

    @Override
    public void setAddressBookFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        userPrefs.setAddressBookFilePath(addressBookFilePath);
    }

    //=========== AddressBook ================================================================================

    @Override
    public void setAddressBook(ReadOnlyAddressBook addressBook) {
        this.addressBook.resetData(addressBook);
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return addressBook;
    }

    @Override
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return addressBook.hasPerson(person);
    }

    @Override
    public void deletePerson(Person target) {
        addressBook.removePerson(target);
    }

    @Override
    public void addPerson(Person person) {
        addressBook.addPerson(person);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void setPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);

        addressBook.setPerson(target, editedPerson);
    }

    @Override
    public void addGroup(Group group) {
        addressBook.addGroup(group);
        updateFilteredGroupList(PREDICATE_SHOW_ALL_GROUPS);
    }

    @Override
    public void addPersonToGroup(Person person, Group group) {
        addressBook.addPersonToGroup(person, group);
    }

    @Override
    public boolean personIsInAGroup(Person person) {
        for (Group group : addressBook.getGroupList()) {
            if (group.hasMember(person)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Group getGroupThatPersonIsIn(Person person) {
        assert personIsInAGroup(person) : "This person is not in a group.";
        for (Group group : addressBook.getGroupList()) {
            if (group.hasMember(person)) {
                return group;
            }
        }
        return null;
    }

    @Override
    public void removePersonFromGroup(Person person, Group group) {
        requireAllNonNull(person, group);
        addressBook.removePersonFromGroup(person);
    }

    /**
     * Checks if the specified group exists in the address book.
     *
     * @param group The group to check for existence.
     * @return True if the group exists, false otherwise.
     * @throws NullPointerException if the given group is null.
     */
    public boolean hasGroup(Group group) {
        requireNonNull(group);
        return addressBook.hasGroup(group);
    }

    @Override
    public void addTasksToGroup(TaskList taskList, Group group) {
        requireAllNonNull(taskList, group);
        group.addTasks(taskList);
    }

    @Override
    public void deleteGroup(Group group) {
        requireNonNull(group);
        addressBook.removeGroup(group);
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

    @Override
    public void updateFilteredPersonList(Predicate<Person> predicate) {
        requireNonNull(predicate);
        filteredPersons.setPredicate(predicate);
    }

    //=========== Filtered Group List Accessors ==============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Group} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Group> getFilteredGroupList() {
        return filteredGroups;
    }

    @Override
    public void updateFilteredGroupList(Predicate<Group> predicate) {
        requireNonNull(predicate);
        filteredGroups.setPredicate(predicate);
    }

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
                && filteredPersons.equals(otherModelManager.filteredPersons)
                && filteredGroups.equals(otherModelManager.filteredGroups);
    }

    @Override
    public Optional<Person> getPersonWithEmail(Email email) {
        requireNonNull(email);

        // Iterate through the filtered list of persons
        for (Person person : filteredPersons) {
            if (person.getEmail().equals(email)) {
                return Optional.of(person);
            }
        }

        return Optional.empty();
    }

    @Override
    public Optional<Group> getGroupWithNumber(int number) {
        // Iterate through the filtered list of groups
        for (Group group : filteredGroups) {
            if (group.getNumber() == number) {
                return Optional.of(group);
            }
        }

        return Optional.empty();
    }

}
