package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.employee.Employee;
import seedu.address.model.employee.UniqueEmployeeList;

/**
 * Wraps all data at the ManageHR level
 * Duplicates are not allowed (by .isSameEmployee comparison)
 */
public class ManageHr implements ReadOnlyManageHr {

    private final UniqueEmployeeList employees;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
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
     * Replaces the contents of the employee list with {@code people}.
     * {@code people} must not contain duplicate people.
     */
    public void setEmployees(List<Employee> people) {
        this.employees.setEmployees(people);
    }

    /**
     * Resets the existing data of this {@code ManageHR} with {@code newData}.
     */
    public void resetData(ReadOnlyManageHr newData) {
        requireNonNull(newData);

        setEmployees(newData.getEmployeeList());
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
     * Adds an employee to ManageHR.
     * The employee must not already exist in ManageHR.
     */
    public void addEmployee(Employee p) {
        employees.add(p);
    }

    /**
     * Replaces the given employee {@code target} in the list with {@code editedEmployee}.
     * {@code target} must exist in the ManageHR.
     * The employee identity of {@code editedEmployee} must not be the same as another existing employee in ManageHR.
     */
    public void setEmployee(Employee target, Employee editedEmployee) {
        requireNonNull(editedEmployee);

        employees.setEmployee(target, editedEmployee);
    }

    /**
     * Removes {@code key} from this {@code ManageHR}.
     * {@code key} must exist in the ManageHr.
     */
    public void removeEmployee(Employee key) {
        employees.remove(key);
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
