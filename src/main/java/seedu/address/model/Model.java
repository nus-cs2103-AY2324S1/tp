package seedu.address.model;

import java.nio.file.Path;
import java.util.Optional;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.index.Index;
import seedu.address.model.person.Name;
import seedu.address.model.person.SortIn;
import seedu.address.model.person.Student;
import seedu.address.model.person.Visual;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Student> PREDICATE_SHOW_ALL_PERSONS = unused -> true;

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
     * Returns true if a student with the same identity as {@code student} exists in
     * the address book.
     */
    boolean hasPerson(Student student);

    /**
     * Deletes the given student.
     * The student must exist in the address book.
     */
    void deletePerson(Student target);

    /**
     * Adds the given student.
     * {@code student} must not already exist in the address book.
     */
    void addPerson(Student student);

    /**
     * Replaces the given student {@code target} with {@code editedStudent}.
     * {@code target} must exist in the address book.
     * The student identity of {@code editedStudent} must not be the same
     * as another existing student in the address book.
     */
    void setPerson(Student target, Student editedStudent);

    // =========== Filtered Student List Accessors
    // =============================================================

    // =========== Filtered Student List Accessors
    // =============================================================

    // =========== Filtered Student List Accessors
    // =============================================================

    /** Returns an unmodifiable view of the filtered student list */
    ObservableList<Student> getFilteredPersonList();

    /**
     * Updates the filter of the filtered student list to filter by the given
     * {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Student> predicate);

    void updateSortedPersonList(SortIn sortIn);

    void export(Visual visual) throws Exception;

    /**
     * Get the student object from filtered student list by name if exists, return
     * None if the student does not exist.
     *
     * @param name the name of the student the caller want to get.
     * @return The student object in the filteredlist at the given index.
     */
    public Optional<Student> getStudentFromFilteredPersonListByName(Name name);

    /**
     * Get the student object from filtered student list by index if exists, return None if the student does not exist.
     * @param index the index of the student the caller want to get.
     * @return The student object in the filteredlist with the given name.
     */
    public Optional<Student> getStudentFromFilteredPersonListByIndex(Index index);
}
