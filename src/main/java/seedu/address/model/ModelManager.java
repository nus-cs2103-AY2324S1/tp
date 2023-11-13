package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.List;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.assignment.Assignment;
import seedu.address.model.module.Module;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;
import seedu.address.model.tutorial.Tutorial;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final AddressBook addressBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Person> filteredPersons;
    private final FilteredList<Module> modules;
    private final FilteredList<Tutorial> tutorials;
    private final FilteredList<Assignment> assignments;
    private List<Tag> attendanceTags;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs);

        this.addressBook = new AddressBook(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredPersons = new FilteredList<>(this.addressBook.getPersonList());
        modules = new FilteredList<>(this.addressBook.getModuleList());
        tutorials = new FilteredList<>(this.addressBook.getTutorialList());
        assignments = new FilteredList<>(this.addressBook.getAssignmentList());
        attendanceTags = this.addressBook.getAttendanceTagsList();
    }

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

    //=========== Module Management =========================================================================

    @Override
    public void addModule(Module module) {
        addressBook.addModule(module);
    }

    @Override
    public void deleteModule(Module module) {
        addressBook.removeModule(module);
    }

    @Override
    public boolean hasModule(Module module) {
        requireNonNull(module);
        return addressBook.hasModule(module);
    }

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Module> getModuleList() {
        return modules;
    }

    //=========== Tutorial Management =========================================================================

    @Override
    public void addTutorial(Tutorial tutorial) {
        addressBook.addTutorial(tutorial);
    }

    @Override
    public void deleteTutorial(Tutorial tutorial) {
        addressBook.removeTutorial(tutorial);
    }

    @Override
    public boolean hasTutorial(Tutorial tutorial) {
        requireNonNull(tutorial);
        return addressBook.hasTutorial(tutorial);
    }

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Tutorial> getTutorialList() {
        return tutorials;
    }

    //=========== Assignment Management =========================================================================

    @Override
    public void addAssignment(Assignment assignment) {
        addressBook.addAssignment(assignment);
    }

    @Override
    public void deleteAssignment(Assignment assignment) {
        addressBook.removeAssignment(assignment);
    }

    @Override
    public boolean hasAssignment(Assignment assignment) {
        requireNonNull(assignment);
        return addressBook.hasAssignment(assignment);
    }

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Assignment> getAssignmentList() {
        return assignments;
    }

    // others

    @Override
    public void addAttendanceTag(Tag tag) {
        addressBook.addAttendanceTag(tag);
    }

    @Override
    public void deleteAttendanceTag(Tag tag) {
        addressBook.deleteAttendanceTag(tag);
    }

    @Override
    public boolean hasAttendanceTag(Tag tag) {
        return addressBook.hasAttendanceTag(tag);
    }

    @Override
    public List<Tag> getAttendanceTagsList() {
        return attendanceTags;
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
                && modules.equals(otherModelManager.modules)
                && tutorials.equals(otherModelManager.tutorials);
    }

}
