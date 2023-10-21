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
import seedu.address.model.employee.Employee;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final ManageHr manageHr;
    private final UserPrefs userPrefs;
    private final FilteredList<Employee> filteredPeople;

    /**
     * Initializes a ModelManager with the given manageHr and userPrefs.
     */
    public ModelManager(ReadOnlyManageHr manageHr, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(manageHr, userPrefs);

        logger.fine("Initializing with address book: " + manageHr + " and user prefs " + userPrefs);

        this.manageHr = new ManageHr(manageHr);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredPeople = new FilteredList<>(this.manageHr.getEmployeeList());
    }

    public ModelManager() {
        this(new ManageHr(), new UserPrefs());
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
    public Path getManageHrFilePath() {
        return userPrefs.getManageHrFilePath();
    }

    @Override
    public void setManageHrFilePath(Path manageHrFilePath) {
        requireNonNull(manageHrFilePath);
        userPrefs.setManageHrFilePath(manageHrFilePath);
    }

    //=========== AddressBook ================================================================================

    @Override
    public void setManageHr(ReadOnlyManageHr manageHr) {
        this.manageHr.resetData(manageHr);
    }

    @Override
    public ReadOnlyManageHr getManageHr() {
        return manageHr;
    }

    @Override
    public boolean hasEmployee(Employee employee) {
        requireNonNull(employee);
        return manageHr.hasEmployee(employee);
    }

    @Override
    public void deleteEmployee(Employee target) {
        manageHr.removeEmployee(target);
    }

    @Override
    public void addEmployee(Employee employee) {
        manageHr.addEmployee(employee);
        updateFilteredEmployeeList(PREDICATE_SHOW_ALL_EMPLOYEES);
    }

    @Override
    public void setEmployee(Employee target, Employee editedEmployee) {
        requireAllNonNull(target, editedEmployee);

        manageHr.setEmployee(target, editedEmployee);
    }

    //=========== Filtered Employee List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Employee} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Employee> getFilteredEmployeeList() {
        return filteredPeople;
    }

    @Override
    public void updateFilteredEmployeeList(Predicate<Employee> predicate) {
        requireNonNull(predicate);
        filteredPeople.setPredicate(predicate);
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
        return manageHr.equals(otherModelManager.manageHr)
                && userPrefs.equals(otherModelManager.userPrefs)
                && filteredPeople.equals(otherModelManager.filteredPeople);
    }

}
