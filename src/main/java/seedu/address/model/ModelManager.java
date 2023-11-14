package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.assignment.Assignment;
import seedu.address.model.internship.role.InternshipRole;
import seedu.address.model.internship.task.InternshipTask;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final AddressBook addressBook;

    private final UserPrefs userPrefs;

    private final FilteredList<Assignment> filteredAssignments;

    private final FilteredList<InternshipRole> filteredInternshipRoles;

    private final FilteredList<InternshipTask> filteredInternshipTasks;

    private final ObservableList<View> selectedView = FXCollections.observableArrayList();


    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs);

        this.addressBook = new AddressBook(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredAssignments = new FilteredList<>(this.addressBook.getAssignmentList());
        filteredInternshipTasks = new FilteredList<>(this.addressBook.getInternshipTaskList());
        filteredInternshipRoles = new FilteredList<>(this.addressBook.getInternshipRoleList());

    }

    public ModelManager() {
        this(new AddressBook(), new UserPrefs());
    }

    //=========== Views =====================================================================================

    @Override
    public void setView(View v) {
        if (selectedView.size() == 0) {
            selectedView.add(v);
        } else {
            selectedView.set(0, v);
        }
    }

    @Override
    public void addViewChangeListener(ListChangeListener<View> listener, View defaultView) {
        selectedView.addListener(listener);
        setView(defaultView);
    }

    @Override
    public void removeViewChangeListener(ListChangeListener<View> listener) {
        selectedView.removeListener(listener);
    }

    @Override
    public boolean isValidOperationWith(View correctView) {
        View currView = selectedView.get(0);
        return currView == correctView;
    }

    //========== Internship Roles ===========================================================================

    @Override
    public void addInternshipRole(InternshipRole role) {
        addressBook.addInternshipRole(role);
    }

    @Override
    public boolean hasInternshipRole(InternshipRole role) {
        return addressBook.hasInternshipRole(role);
    }

    @Override
    public ObservableList<InternshipRole> getFilteredInternshipRoleList() {
        return filteredInternshipRoles;
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

    //====== Filtered Assignment List Accessors==========================

    @Override
    public boolean hasAssignment(Assignment assignment) {
        return addressBook.hasAssignment(assignment);
    }

    @Override
    public void deleteAssignment(Assignment target) {
        addressBook.removeAssignment(target);
    }

    @Override
    public void sortAssignments() {
        addressBook.sortAssignments();
    }

    @Override
    public void addAssignment(Assignment assignment) {
        requireNonNull(assignment);
        addressBook.addAssignment(assignment);
    }

    @Override
    public void setAssignment(Assignment assignment, Assignment newAssignment) {
        requireNonNull(assignment);
        addressBook.editAssignment(assignment, newAssignment);
    }

    @Override
    public ObservableList<Assignment> getFilteredAssignmentList() {
        return filteredAssignments;
    }

    @Override
    public ObservableList<Assignment> getUnfilteredAssignmentList() {
        return addressBook.getAssignmentList();
    }

    @Override
    public void updateFilteredAssignmentList(Predicate<Assignment> predicate) {
        requireNonNull(predicate);
        filteredAssignments.setPredicate(predicate);
    }

    //====== Filtered Internship Task List Accessors==========================

    @Override
    public boolean hasInternshipTask(InternshipTask internshipTask) {
        return addressBook.hasInternshipTask(internshipTask);
    }

    @Override
    public void deleteInternshipTask(InternshipTask target) {
        addressBook.removeInternshipTask(target);
    }

    @Override
    public void addInternshipTask(InternshipTask internshipTask) {
        requireNonNull(internshipTask);
        addressBook.addInternshipTask(internshipTask);
    }

    @Override
    public ObservableList<InternshipTask> getFilteredInternshipTaskList() {
        return filteredInternshipTasks;
    }

    @Override
    public ObservableList<InternshipTask> getUnfilteredInternshipTaskList() {
        return addressBook.getInternshipTaskList();
    }

    @Override
    public void updateFilteredInternshipTaskList(Predicate<InternshipTask> predicate) {
        requireNonNull(predicate);
        filteredInternshipTasks.setPredicate(predicate);
    }

    @Override
    public void sortInternshipTasks() {
        addressBook.sortInternshipTasks();
    }

    @Override
    public void setInternshipTask(InternshipTask task, InternshipTask newTask) {
        requireNonNull(task);
        addressBook.setInternshipTask(task, newTask);
    }

    //====== Filtered Internship Role List Accessors==========================

    @Override
    public ObservableList<InternshipRole> getUnfilteredInternshipRoleList() {
        return addressBook.getInternshipRoleList();
    }

    @Override
    public void updateFilteredInternshipRoleList(Predicate<InternshipRole> predicate) {
        requireNonNull(predicate);
        filteredInternshipRoles.setPredicate(predicate);
    }

    @Override
    public void deleteInternshipRole(InternshipRole target) {
        addressBook.removeInternshipRole(target);
    }

    @Override
    public void setInternshipRole(InternshipRole role, InternshipRole newRole) {
        requireNonNull(role);
        addressBook.setInternshipRole(role, newRole);
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
                && filteredAssignments.equals(otherModelManager.filteredAssignments)
                && filteredInternshipTasks.equals(otherModelManager.filteredInternshipTasks);
    }
}
