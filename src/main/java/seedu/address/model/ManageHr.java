package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.department.Department;
import seedu.address.model.department.UniqueDepartmentList;
import seedu.address.model.department.exceptions.DepartmentNotFoundException;
import seedu.address.model.employee.Employee;
import seedu.address.model.employee.UniqueEmployeeList;
import seedu.address.model.employee.exceptions.InvalidEmployeeDepartmentMatchingException;
import seedu.address.model.employee.exceptions.SubordinatePresentException;
import seedu.address.model.employee.exceptions.SupervisorNotFoundException;
import seedu.address.model.name.DepartmentName;
import seedu.address.model.name.EmployeeName;

/**
 * Wraps all data at the ManageHR level
 * Duplicates are not allowed (by .isSameEmployee comparison)
 */
public class ManageHr implements ReadOnlyManageHr {

    private final UniqueEmployeeList employees;

    private final UniqueDepartmentList departments;
    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        departments = new UniqueDepartmentList();
        employees = new UniqueEmployeeList();
    }

    public ManageHr() {}

    /**
     * Creates a ManageHR instance using the Employees in the {@code toBeCopied}
     */
    public ManageHr(ReadOnlyManageHr toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the department list with {@code departments}.
     * {@code departments} must not contain duplicate department.
     */
    public void setDepartments(List<Department> departments) {
        this.departments.setDepartments(departments);
    }

    /**
     * Replaces the contents of the employee list with {@code people}.
     * {@code people} must not contain duplicate people.
     */
    public void setEmployees(List<Employee> people) {
        for (Employee employee : people) {
            for (DepartmentName departmentName : employee.getDepartments()) {
                if (!departments.contains(departmentName)) {
                    throw new DepartmentNotFoundException();
                }
            }
        }
        this.employees.setEmployees(people);
    }

    /**
     * Resets the existing data of this {@code ManageHr} with {@code newData}.
     */
    public void resetData(ReadOnlyManageHr newData) {
        requireNonNull(newData);
        setDepartments(newData.getDepartmentList());
        setEmployees(newData.getEmployeeList());
        checkEmployeeDepartmentValidity();
    }

    /**
     * Checks for any errors in the fields of employees and department
     */
    public void checkEmployeeDepartmentValidity() {
        for (Employee employee : employees) {
            for (Department department : departments) {
                if ((employee.isInDepartment(department) && !department.hasEmployee(employee))
                        || (department.hasEmployee(employee) && !employee.isInDepartment(department))) {
                    throw new InvalidEmployeeDepartmentMatchingException();
                }
            }
        }
    }
    //// employee-level operations

    /**
     * Returns true if an employee with the same identity as {@code employee} exists in the storage.
     */
    public boolean hasEmployee(Employee employee) {
        requireNonNull(employee);
        return employees.contains(employee);
    }

    /**
     * Returns true if an employee with the same identity as {@code name} exists in the storage.
     */
    public boolean hasEmployeeWithName(EmployeeName name) {
        requireNonNull(name);
        return employees.contains(name);
    }

    /**
     * Updates the departments with respect to changes in employee
     *
     * @param employee The employee to be added/changed
     */
    private void updateDepartments(Employee employee) {
        for (DepartmentName departmentName : employee.getDepartments()) {
            if (!departments.contains(departmentName)) {
                throw new DepartmentNotFoundException();
            }
            for (Department department : departments) {
                if (department.isSameDepartmentName(departmentName)) {
                    department.addEmployee(employee);
                    departments.setDepartment(department, department);
                }
            }
        }
    }

    /**
     * Adds an employee to ManageHR.
     * The employee must not already exist in ManageHR.
     *
     * @param employee The employee to be added to the list.
     * @throws SupervisorNotFoundException If the supervisor of the employee is not found in the list.
     */

    public void addEmployee(Employee employee) {
        requireNonNull(employee);
        updateDepartments(employee);
        if (!employees.containsManager(employee)) {
            throw new SupervisorNotFoundException();
        }
        employees.add(employee);
    }

    /**
     * Adds an employee to the internal list without applying constraints.
     * This method is intended for use when adding an employee retrieved from storage,
     * where constraints related to the supervisor-subordinate relationship are not enforced.
     *
     * @param employee The employee to be added. Must not be {@code null}.
     */
    public void addEmployeeFromStorageWithoutConstraints(Employee employee) {
        requireNonNull(employee);
        updateDepartments(employee);
        employees.addWithoutConstraints(employee);
    }

    /**
     * Enforces constraints on the internal list of employees.
     * This method ensures that constraints related to the supervisor-subordinate relationship
     * are applied to the internal list of employees.
     */
    public void enforceConstraints() {
        employees.enforceConstraints();
    }

    /**
     * Replaces the given employee {@code target} in the list with {@code editedEmployee}.
     * {@code target} must exist in the ManageHR.
     * The employee identity of {@code editedEmployee} must not be the same as another existing employee in ManageHR.
     *
     * @param target The original employee to be updated.
     * @param editedEmployee The updated employee.
     * @throws SubordinatePresentException If the original employee manages subordinates, preventing the update.
     * @throws SupervisorNotFoundException If the target employee is the supervisor of the editedEmployee.
     */
    public void setEmployee(Employee target, Employee editedEmployee) throws CommandException {
        requireNonNull(editedEmployee);
        for (Department department : departments) {
            department.removeEmployeeIfPresent(target);
            departments.setDepartment(department, department);
        }

        if (!target.isSameEmployee(editedEmployee) && employees.hasSubordinates(target)) {
            throw new SubordinatePresentException();
        }
        if (!employees.containsManager(editedEmployee)) {
            throw new SupervisorNotFoundException();
        }
        if (target.isSupervisorOf(editedEmployee)) {
            throw new SupervisorNotFoundException();
        }
        if (target.isSameEmployee(editedEmployee) && !hasPermissibleEdits(target, editedEmployee)
                && employees.hasSubordinates(target)) {
            throw new CommandException("Name and role should not be edited when he is still managing other employees");
        }
        employees.setEmployee(target, editedEmployee);
    }

    private boolean hasPermissibleEdits(Employee target, Employee editedEmployee) {
        return !target.isSameEmployee(editedEmployee) || target.hasSameRole(editedEmployee);
    }

    /**
     * Removes {@code key} from this {@code ManageHr}.
     * {@code key} must exist in the ManageHr.
     *
     * @param key The employee to be removed.
     * @throws SubordinatePresentException If the employee manages subordinates, preventing removal.
     */
    public void removeEmployee(Employee key) {
        if (employees.hasSubordinates(key)) {
            throw new SubordinatePresentException();
        }
        for (Department department : departments) {
            department.removeEmployeeIfPresent(key);
            departments.setDepartment(department, department);
        }
        employees.remove(key);
    }

    /**
     * Returns true if a department with the same identity as {@code department} exists in the storage.
     */
    public boolean hasDepartment(Department department) {
        requireNonNull(department);
        return departments.contains(department);
    }

    /**
     * Returns true if a department with the same identity as {@code name} exists in the storage.
     */
    public boolean hasDepartmentWithName(DepartmentName name) {
        requireNonNull(name);
        return departments.contains(name);
    }

    /**
     * Adds a department to ManageHR.
     * The employee must not already exist in ManageHR.
     */
    public void addDepartment(Department d) {
        requireNonNull(d);
        departments.add(d);
    }

    /**
     * Removes {@code key} from this {@code ManageHr}.
     * {@code key} must exist in the ManageHr.
     */
    public void removeDepartment(Department key) {
        requireNonNull(key);
        if (departments.contains(key)) {
            departments.remove(key);
            for (Employee employee : employees) {
                employee.removeDepartmentIfPresent(key);
                employees.setEmployee(employee, employee);
            }
        }
    }
    //// util methods

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("employees", employees)
                .toString();
    }

    @Override
    public ObservableList<Employee> getEmployeeList() {
        return employees.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Department> getDepartmentList() {
        return departments.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ManageHr)) {
            return false;
        }

        ManageHr otherManageHr = (ManageHr) other;
        return employees.equals(otherManageHr.employees);
    }

    @Override
    public int hashCode() {
        return employees.hashCode();
    }
}
