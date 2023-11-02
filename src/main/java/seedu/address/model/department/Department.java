package seedu.address.model.department;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import seedu.address.model.name.Name;
/**
 * Represents a Department in the ManageHR.
 * Guarantees: immutable; name is valid as declared in {@link #isValidDepartmentName(String)}
 */
public class Department {

    public final Name name;
    private final Set<Name> employees = new HashSet<>();

    /**
     * Constructs a {@code Department}.
     *
     * @param name A valid department name of type String.
     */
    public Department(String name) {
        requireNonNull(name);
        checkArgument(Name.isValidName(name), Name.MESSAGE_CONSTRAINTS);
        this.name = new Name(name);
    }

    /**
     * Constructs a {@code Department}.
     *
     * @param name A valid department name of type Name.
     */
    public Department(Name name) {
        requireNonNull(name);
        this.name = name;
    }

    /**
     * Constructs a {@code Department}.
     *
     * @param name A valid department name of type Name.
     */
    public Department(Name name, Set<Name> employees) {
        requireNonNull(name);
        requireNonNull(employees);
        this.name = name;
        this.employees.addAll(employees);
    }
    public String getName() {
        return name.fullName;
    }

    public Set<Name> getEmployees() { return Collections.unmodifiableSet(employees); }

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
