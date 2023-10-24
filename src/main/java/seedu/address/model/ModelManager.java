package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.student.ClassDetails;
import seedu.address.model.student.Student;
import seedu.address.model.student.StudentNumber;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final VersionedAddressBook versionedAddressBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Student> filteredStudents;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs);

        this.versionedAddressBook = new VersionedAddressBook(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredStudents = new FilteredList<>(this.versionedAddressBook.getStudentList());

        logger.info("Set the tutorial count to " + this.userPrefs.getTutorialCount());
        logger.info("Set the assignment count to " + this.userPrefs.getAssignmentCount());
        ClassDetails.setTutorialCount(this.userPrefs.getTutorialCount());
        ClassDetails.setAssignmentCount(this.userPrefs.getAssignmentCount());
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

    /**
     * Returns true if the user has configured the module information.
     */
    @Override
    public boolean getConfigured() {
        return userPrefs.getConfigured();
    }

    /**
     * User has configured the module information.
     */
    @Override
    public void setConfigured(boolean isConfigured) {
        userPrefs.setConfigured(isConfigured);
    }

    /**
     * Assignment count that the user configured.
     */
    @Override
    public void setAssignmentCount(int assignmentCount) {
        userPrefs.setAssignmentCount(assignmentCount);
    }

    /**
     * Tutorial count that the user configured.
     */
    @Override
    public void setTutorialCount(int tutorialCount) {
        userPrefs.setTutorialCount(tutorialCount);
    }

    //=========== AddressBook ================================================================================

    @Override
    public void setAddressBook(ReadOnlyAddressBook addressBook) {
        this.versionedAddressBook.resetData(addressBook);
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return versionedAddressBook;
    }

    @Override
    public boolean hasStudent(Student student) {
        requireNonNull(student);
        return versionedAddressBook.hasStudent(student);
    }

    @Override
    public void deleteStudent(Student target) {
        versionedAddressBook.removeStudent(target);
    }

    @Override
    public void addStudent(Student student) {
        versionedAddressBook.addStudent(student);
        updateFilteredStudentList(PREDICATE_SHOW_ALL_STUDENTS);
    }

    @Override
    public void setStudent(Student target, Student editedStudent) {
        requireAllNonNull(target, editedStudent);
        versionedAddressBook.setStudent(target, editedStudent);
    }

    @Override
    public Student getStudent(StudentNumber studentNumber) {
        requireAllNonNull(studentNumber);
        return versionedAddressBook.getStudent(studentNumber);
    }

    @Override
    public void setSelectedStudent(Student student) {
        versionedAddressBook.setSelectedStudent(student);
    }
    //=========== Filtered Student List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Student} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Student> getFilteredStudentList() {
        return filteredStudents;
    }

    @Override
    public ObservableList<Student> getSelectedStudent() {
        return versionedAddressBook.getSelectedStudent();
    }

    @Override
    public void updateFilteredStudentList(Predicate<Student> predicate) {
        requireNonNull(predicate);
        filteredStudents.setPredicate(predicate);
        if (!filteredStudents.isEmpty()) {
            versionedAddressBook.setSelectedStudent(filteredStudents.get(0));
        }
    }

    /**
     * Returns true if the model has previous address book states to restore.
     */
    @Override
    public boolean canUndoAddressBook() {
        return versionedAddressBook.canUndo();
    }

    /**
     * Returns true if the model has undone address book states to restore.
     */
    @Override
    public boolean canRedoAddressBook() {
        return versionedAddressBook.canRedo();
    }

    /**
     * Restores the model's address book to its previous state.
     */
    @Override
    public void undoAddressBook() {
        versionedAddressBook.undo();
    }

    /**
     * Restores the model's address book to its previously undone state.
     */
    @Override
    public void redoAddressBook() {
        versionedAddressBook.redo();
    }

    /**
     * Saves the current address book state for undo/redo.
     */
    @Override
    public void commitAddressBook() {
        versionedAddressBook.commit();
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
        return versionedAddressBook.equals(otherModelManager.versionedAddressBook)
                && userPrefs.equals(otherModelManager.userPrefs)
                && filteredStudents.equals(otherModelManager.filteredStudents);
    }

    @Override
    public int hashCode() {
        return Objects.hash(versionedAddressBook, userPrefs, filteredStudents);
    }
}
