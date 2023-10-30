package seedu.address.model.employee;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.department.Department;

/**
 * Represents an Employee in the ManageHR app.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Employee {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;

    // Data fields
    private final Address address;
    private final Salary salary;
    private final Leave leave;
    private final Role role;
    private final Set<Name> managersInCharge = new HashSet<>();
    private final Set<Department> departments = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Employee(Name name, Phone phone, Email email, Address address, Salary salary, Leave leave,
                    Role role, Set<Name> managersInCharge, Set<Department> departments) {
        requireAllNonNull(name, phone, email, address, salary, leave, departments);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.salary = salary;
        this.leave = leave;
        this.role = role;
        this.managersInCharge.addAll(managersInCharge);
        this.departments.addAll(departments);
    }

    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }

    public Salary getSalary() {
        return salary;
    }

    public Leave getLeave() {
        return leave;
    }

    public Role getRole() {
        return role;
    }

    /**
     * Returns an immutable department set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Department> getDepartments() {
        return Collections.unmodifiableSet(departments);
    }

    /**
     * Returns an immutable name set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Name> getManagersInCharge() {
        return Collections.unmodifiableSet(managersInCharge);
    }

    /**
     * Checks if another employee is the same as this employee.
     *
     * @param otherEmployee The other employee to compare with this employee.
     * @return {@code true} if the provided 'otherEmployee' is the same as this employee,
     *         {@code false} otherwise. Two employees are considered the same if they have
     *         the same name (case-sensitive) and are not both null.
     */
    public boolean isSameEmployee(Employee otherEmployee) {
        if (otherEmployee == this) {
            return true;
        }

        return otherEmployee != null
                && otherEmployee.getName().equals(getName());
    }

    /**
     * Checks if the name of another employee matches the name of this employee.
     *
     * @param otherEmployee The other employee whose name is to be compared.
     * @return {@code true} if the provided 'otherEmployee' is not null and has the same name
     *         as this employee, {@code false} otherwise.
     */
    public boolean hasSameEmployeeName(Name otherEmployee) {
        return otherEmployee != null
                && otherEmployee.equals(getName());
    }

    public boolean isManager() {
        return role.isManager();
    }

    /**
     * Returns true if both employees have the same identity and data fields.
     * This defines a stronger notion of equality between two employees.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Employee)) {
            return false;
        }

        Employee otherEmployee = (Employee) other;
        return name.equals(otherEmployee.name)
                && phone.equals(otherEmployee.phone)
                && email.equals(otherEmployee.email)
                && address.equals(otherEmployee.address)
                && salary.equals(otherEmployee.salary)
                && leave.equals(otherEmployee.leave)
                && role.equals(otherEmployee.role)
                && managersInCharge.equals(otherEmployee.managersInCharge)
                && departments.equals(otherEmployee.departments);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address, salary, leave, role, managersInCharge, departments);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", name)
                .add("phone", phone)
                .add("email", email)
                .add("address", address)
                .add("salary", salary)
                .add("leave", leave)
                .add("role", role)
                .add("managersInCharge", managersInCharge)
                .add("departments", departments)
                .toString();
    }

}
