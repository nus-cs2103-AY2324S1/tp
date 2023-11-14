package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.assignment.Assignment;
import seedu.address.model.internship.role.InternshipRole;
import seedu.address.model.internship.task.InternshipTask;

/**
 * The API of the Model component.
 */
public interface Model {
    /**
     * {@code Predicate} that always evaluate to true
     */
    Predicate<Assignment> PREDICATE_SHOW_ALL_ASSIGNMENTS = unused -> true;
    Predicate<InternshipRole> PREDICATE_SHOW_ALL_INTERNSHIP_ROLES = unused -> true;
    Predicate<InternshipTask> PREDICATE_SHOW_ALL_INTERNSHIP_TASKS = unused -> true;

    String MESSAGE_WRONG_VIEW_FIRST_HALF = "Please switch to ";

    String MESSAGE_WRONG_VIEW_SECOND_HALF = " before performing this operation "
            + "using its respective list command";

    void setView(View v);

    void addViewChangeListener(ListChangeListener<View> e, View defaultView);

    void removeViewChangeListener(ListChangeListener<View> e);

    /**
     * @param correctView the view that this command should be executed in
     */
    boolean isValidOperationWith(View correctView);

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

    /**
     * Returns the AddressBook
     */
    ReadOnlyAddressBook getAddressBook();

    boolean hasAssignment(Assignment assignment);

    void addAssignment(Assignment assignment);

    void deleteAssignment(Assignment target);

    /**
     * Returns an unmodifiable view of the filtered assignment list
     */
    ObservableList<Assignment> getFilteredAssignmentList();

    ObservableList<Assignment> getUnfilteredAssignmentList();

    /**
     * Sorts UniqueAssignmentList by endDate
     */
    void sortAssignments();

    /**
     * Updates the filter of the filtered assignment list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredAssignmentList(Predicate<Assignment> predicate);

    /**
     * Edits the description of an assignment.
     * The assignment must exist in Campus Companion.
     */
    void setAssignment(Assignment assignment, Assignment newAssignment);

    // ============ Internship Roles =============================================================
    void addInternshipRole(InternshipRole role);

    boolean hasInternshipRole(InternshipRole role);

    void setInternshipRole(InternshipRole role, InternshipRole newRole);

    ObservableList<InternshipRole> getFilteredInternshipRoleList();

    ObservableList<InternshipRole> getUnfilteredInternshipRoleList();

    void deleteInternshipRole(InternshipRole target);
    /**
     * Updates the filter of the filtered internship role list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredInternshipRoleList(Predicate<InternshipRole> predicate);

    // ============ Internship Tasks =============================================================

    /**
     * Returns an unmodifiable view of the filtered internship task list
     */
    ObservableList<InternshipTask> getFilteredInternshipTaskList();

    ObservableList<InternshipTask> getUnfilteredInternshipTaskList();

    void deleteInternshipTask(InternshipTask target);

    void addInternshipTask(InternshipTask internshipTask);

    boolean hasInternshipTask(InternshipTask internshipTask);

    /**
     * Updates the filter of the filtered internship task list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredInternshipTaskList(Predicate<InternshipTask> predicate);

    void sortInternshipTasks();

    void setInternshipTask(InternshipTask task, InternshipTask newTask);
}
