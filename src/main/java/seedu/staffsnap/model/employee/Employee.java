package seedu.staffsnap.model.employee;

import static seedu.staffsnap.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.staffsnap.commons.util.ToStringBuilder;
import seedu.staffsnap.model.tag.Tag;

/**
 * Represents an Employee in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Employee {

    // Identity fields
    private final Name name;
    private final Phone phone; //identifier
    private final Department department; //department
    // Data fields
    private final JobTitle jobTitle;
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Employee(Name name, Phone phone, Department department, JobTitle jobTitle, Set<Tag> tags) {
        requireAllNonNull(name, phone, department, jobTitle, tags);
        this.name = name;
        this.phone = phone;
        this.department = department;
        this.jobTitle = jobTitle;
        this.tags.addAll(tags);
    }

    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public Department getDepartment() {
        return department;
    }

    public JobTitle getJobTitle() {
        return jobTitle;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if both employees have the same name.
     * This defines a weaker notion of equality between two employees.
     */
    public boolean isSameEmployee(Employee otherEmployee) {
        if (otherEmployee == this) {
            return true;
        }

        return otherEmployee != null
                && otherEmployee.getName().equals(getName());
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
                && department.equals(otherEmployee.department)
                && jobTitle.equals(otherEmployee.jobTitle)
                && tags.equals(otherEmployee.tags);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, department, jobTitle, tags);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", name)
                .add("phone", phone)
                .add("department", department)
                .add("jobTitle", jobTitle)
                .add("tags", tags)
                .toString();
    }

    /**
     * @param o the Employee to be compared.
     * @return the value 0 if the argument Name is equal to this Name; a value less than 0 if this Name is
     * lexicographically less than the Name argument; and a value greater than 0 if this string is lexicographically
     * greater than the Name argument.
     */
    public int compareByName(Employee o) {
        return this.name.compareTo(o.name);
    }
}
