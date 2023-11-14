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
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.department.Department;
import seedu.address.model.employee.Employee;
import seedu.address.model.name.DepartmentName;
import seedu.address.model.name.EmployeeName;

/**
 * Represents the in-memory model of the ManageHR data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);
    private final ManageHr manageHr;
    private final UserPrefs userPrefs;
    private final FilteredList<Employee> filteredPeople;

    /**
     * Initializes a ModelManager with the given manageHR and userPrefs.
     */
    public ModelManager(ReadOnlyManageHr manageHr, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(manageHr, userPrefs);

        logger.fine("Initializing with ManageHR: " + manageHr + " and user prefs " + userPrefs);

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

    //=========== ManageHR ================================================================================

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
    public boolean hasEmployeeWithName(EmployeeName name) {
        requireNonNull(name);
        return manageHr.hasEmployeeWithName(name);
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
    public void setEmployee(Employee target, Employee editedEmployee) throws CommandException {
        requireAllNonNull(target, editedEmployee);

        manageHr.setEmployee(target, editedEmployee);
    }

    @Override
    public boolean hasDepartment(Department department) {
        requireNonNull(department);
        return manageHr.hasDepartment(department);
    }

    @Override
    public boolean hasDepartmentWithName(DepartmentName name) {
        requireNonNull(name);
        return manageHr.hasDepartmentWithName(name);
    }

    @Override
    public void deleteDepartment(Department target) {
        manageHr.removeDepartment(target);
    }

    @Override
    public void addDepartment(Department department) {
        manageHr.addDepartment(department);
    }

    //=========== Filtered Employee List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Employee} backed by the internal list of
     * {@code versionedManageHr}
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
