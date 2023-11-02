package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.department.Department;
import seedu.address.model.employee.Employee;
import seedu.address.model.name.DepartmentName;
import seedu.address.model.name.EmployeeName;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Employee> PREDICATE_SHOW_ALL_EMPLOYEES = unused -> true;

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
     * Returns the user prefs' ManageHR file path.
     */
    Path getManageHrFilePath();

    /**
     * Sets the user prefs' ManageHR file path.
     */
    void setManageHrFilePath(Path manageHrFilePath);

    /**
     * Replaces ManageHr data with the data in {@code manageHR}.
     */
    void setManageHr(ReadOnlyManageHr manageHr);

    /** Returns ManageHR */
    ReadOnlyManageHr getManageHr();

    /**
     * Returns true if an employee with the same identity as {@code employee} exists in ManageHR.
     */
    boolean hasEmployee(Employee employee);

    /**
     * Returns true if an employee with the identity as {@code name} exists in ManageHR.
     */
    boolean hasEmployeeWithName(EmployeeName name);

    /**
     * Deletes the given employee.
     * The employee must exist in ManageHR.
     */
    void deleteEmployee(Employee target);

    /**
     * Adds the given employee.
     * {@code employee} must not already exist in ManageHR.
     */
    void addEmployee(Employee employee);

    /**
     * Replaces the given employee {@code target} with {@code editedEmployee}.
     * {@code target} must exist in ManageHR.
     * The employee identity of {@code editedEmployee} must not be the same as another existing employee in ManageHR.
     */
    void setEmployee(Employee target, Employee editedEmployee);

    /** Returns an unmodifiable view of the filtered employee list */
    ObservableList<Employee> getFilteredEmployeeList();

    /**
     * Updates the filter of the filtered employee list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredEmployeeList(Predicate<Employee> predicate);

    /**
     * Returns true if an department with the same identity as {@code department} exists in ManageHR.
     */
    boolean hasDepartment(Department department);

    /**
     * Returns true if a department with the identity as {@code name} exists in ManageHR.
     */
    boolean hasDepartmentWithName(DepartmentName name);

    /**
     * Adds a department to ManageHR.
     * The department must not already exist in ManageHR.
     */
    void addDepartment(Department department);

    /**
     * Removes {@code key} from this {@code ManageHr}.
     * {@code key} must exist in the ManageHr's department list.
     */
    void deleteDepartment(Department target);
}
