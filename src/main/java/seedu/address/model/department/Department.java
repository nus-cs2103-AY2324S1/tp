package seedu.address.model.department;

import static java.util.Objects.requireNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

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
    public String getName() {
        return name.fullName;
    }

    public Set<EmployeeName> getEmployees() {
        return Collections.unmodifiableSet(employees);
    }

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
     * Returns true if both department have the same name.
     * This defines a weaker notion of equality between two department.
     */
    public boolean isSameDepartment(Department otherDepartment) {
        return otherDepartment.equals(this);
    }

    public boolean isSameDepartmentName(DepartmentName name) {
        return this.name.equals(name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return '[' + name.fullName + ']';
    }

}
