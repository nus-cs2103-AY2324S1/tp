package seedu.address.model.department;

import static java.util.Objects.requireNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import seedu.address.model.employee.Employee;
import seedu.address.model.name.DepartmentName;
import seedu.address.model.name.EmployeeName;

/**
 * Represents a Department in the ManageHR.
 * Guarantees: immutable; name is valid as declared in {@link #isValidDepartmentName(String)}
 */
public class Department {

    public final DepartmentName name;
    private final Set<EmployeeName> employees = new HashSet<>();

    /**
     * Constructs a {@code Department}.
     *
     * @param name A valid department name of type String.
     */
    public Department(String name) {
        requireNonNull(name);
        this.name = new DepartmentName(name);
    }

    /**
     * Constructs a {@code Department}.
     *
     * @param name A valid department name of type Name.
     */
    public Department(DepartmentName name) {
        requireNonNull(name);
        this.name = name;
    }

    /**
     * Constructs a {@code Department}.
     *
     * @param name A valid department name of type Name.
     */
    public Department(DepartmentName name, Set<EmployeeName> employees) {
        requireNonNull(name);
        requireNonNull(employees);
        this.name = name;
        this.employees.addAll(employees);
    }

    /**
     * Returns the name of the department.
     *
     * @return A string representing the department's name.
     */
    public String getName() {
        return name.fullName;
    }

    /**
     * Returns an unmodifiable set of employees in the department.
     *
     * @return An unmodifiable set of EmployeeName representing the department's employees.
     */
    public Set<EmployeeName> getEmployees() {
        return Collections.unmodifiableSet(employees);
    }

    /**
     * Checks if the department contains a specific employee.
     *
     * @param employee The employee to be checked.
     * @return True if the employee exists in the department, false otherwise.
     */
    public boolean hasEmployee(Employee employee) {
        requireNonNull(employee);
        return employees.contains(employee.getName());
    }

    /**
     * Adds an employee to the department.
     *
     * @param employee The employee to be added.
     */
    public void addEmployee(Employee employee) {
        requireNonNull(employee);
        employees.add(employee.getName());
    }

    /**
     * Removes an employee from the department if present.
     *
     * @param employee The employee to be removed.
     */
    public void removeEmployeeIfPresent(Employee employee) {
        if (this.hasEmployee(employee)) {
            employees.remove(employee.getName());
        }
    }

    /**
     * Checks for equality with another object.
     *
     * @param other The object to be compared.
     * @return True if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Department)) {
            return false;
        }

        Department otherDepartment = (Department) other;
        return this.getName().equals(otherDepartment.getName());
    }

    /**
     * Checks if two departments have the same name.
     *
     * @param otherDepartment The department to compare names with.
     * @return True if both departments have the same name, false otherwise.
     */
    public boolean isSameDepartment(Department otherDepartment) {
        return otherDepartment.equals(this);
    }

    /**
     * Checks if the department's name is the same as the provided department name.
     *
     * @param name The department name to compare with.
     * @return True if the names are the same, false otherwise.
     */
    public boolean isSameDepartmentName(DepartmentName name) {
        return this.name.equals(name);
    }

    /**
     * Generates a hash code for the department.
     *
     * @return An integer representing the hash code.
     */
    @Override
    public int hashCode() {
        return name.hashCode();
    }

    /**
     * Represents the state of the department as text for viewing.
     *
     * @return A string representing the department's state.
     */
    @Override
    public String toString() {
        return '[' + name.fullName + ']';
    }
}
