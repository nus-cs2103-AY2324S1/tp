package seedu.staffsnap.model.applicant;

import static seedu.staffsnap.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.staffsnap.commons.util.ToStringBuilder;
import seedu.staffsnap.model.tag.Tag;

/**
 * Represents an Applicant in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Applicant {

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
    public Applicant(Name name, Phone phone, Department department, JobTitle jobTitle, Set<Tag> tags) {
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
     * Returns true if both applicants have the same name.
     * This defines a weaker notion of equality between two applicants.
     */
    public boolean isSameApplicant(Applicant otherApplicant) {
        if (otherApplicant == this) {
            return true;
        }

        return otherApplicant != null
                && otherApplicant.getName().equals(getName());
    }

    /**
     * Returns true if both applicants have the same identity and data fields.
     * This defines a stronger notion of equality between two applicants.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Applicant)) {
            return false;
        }

        Applicant otherApplicant = (Applicant) other;
        return name.equals(otherApplicant.name)
                && phone.equals(otherApplicant.phone)
                && department.equals(otherApplicant.department)
                && jobTitle.equals(otherApplicant.jobTitle)
                && tags.equals(otherApplicant.tags);
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

}
