package seedu.address.model.employee;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.name.DepartmentName;
import seedu.address.model.name.EmployeeName;

/**
 * Represents an Employee in the ManageHR app.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Employee {

    // Identity fields
    private final EmployeeName name;
    private final Phone phone;
    private final Email email;

    // Data fields
    private final Address address;
    private final Salary salary;
    private final Leave leave;
    private final Role role;
    private final Set<EmployeeName> supervisors = new HashSet<>();
    private final Set<DepartmentName> departments = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Employee(EmployeeName name, Phone phone, Email email, Address address, Salary salary, Leave leave,
                    Role role, Set<EmployeeName> supervisors, Set<DepartmentName> departments) {
        requireAllNonNull(name, phone, email, address, salary, leave, departments);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.salary = salary;
        this.leave = leave;
        this.role = role;
        this.supervisors.addAll(supervisors);
        this.departments.addAll(departments);
    }

    public EmployeeName getName() {
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
    public Set<DepartmentName> getDepartments() {
        return Collections.unmodifiableSet(departments);
    }

    /**
     * Returns an immutable name set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<EmployeeName> getSupervisors() {
        return Collections.unmodifiableSet(supervisors);
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
    public boolean hasSameEmployeeName(EmployeeName otherEmployee) {
        return otherEmployee != null
                && otherEmployee.equals(getName());
    }

    /**
     * Checks if this employee holds a managerial role.
     *
     * @return {@code true} if this employee's role is a managerial role, {@code false} otherwise.
     */
    public boolean isManager() {
        return role.isManager();
    }

    /**
     * Checks if this employee is a supervisor of the provided subordinate.
     *
     * @param subordinate The subordinate employee to check.
     * @return {@code true} if this employee is a supervisor of the provided 'subordinate,' {@code false} otherwise.
     */
    public boolean isSupervisorOf(Employee subordinate) {
        return subordinate.getSupervisors().stream().anyMatch(x -> x.equals(this.getName()));
    }

    /**
     * Checks if this employee has the same role as the given employee.
     *
     * @param employee The employee to compare roles with.
     * @return true if this employee has the same role as the given employee, false otherwise.
     */
    public boolean hasSameRole(Employee employee) {
        return role.equals(employee.getRole());
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
                && supervisors.equals(otherEmployee.supervisors)
                && departments.equals(otherEmployee.departments);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address, salary, leave, role, supervisors, departments);
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
                .add("supervisors", supervisors)
                .add("departments", departments)
                .toString();
    }

}
