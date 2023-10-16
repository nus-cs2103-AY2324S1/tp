package seedu.application.model.job;

import java.util.Objects;

import seedu.application.commons.util.CollectionUtil;
import seedu.application.commons.util.ToStringBuilder;

/**
 * Represents a Job in the application book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Job {

    // Identity fields
    private final Role role;
    private final Company company;
    private final Status status;
    private final Deadline deadline;

    /**
     * Not all fields are compulsory
     */
    public Job(Role role, Company company, Status status, Deadline deadline) {
        CollectionUtil.requireAllNonNull(role, company, deadline);
        this.role = role;
        this.company = company;
        this.status = status;
        this.deadline = deadline;
    }

    public Role getRole() {
        return role;
    }

    public Company getCompany() {
        return company;
    }

    public Status getStatus() {
        return status;
    }

    public Deadline getDeadline() {
        return deadline;
    }

    /**
     * Returns true if both jobs have the same role and company.
     * This defines a weaker notion of equality between two jobs.
     */
    public boolean isSameJob(Job otherJob) {
        if (otherJob == this) {
            return true;
        }

        return otherJob != null
                && otherJob.getRole().equals(getRole())
                && otherJob.getCompany().equals(getCompany());
    }

    /**
     * Returns true if both jobs have the same role, company and data fields.
     * This defines a stronger notion of equality between two jobs.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Job)) {
            return false;
        }

        Job otherJob = (Job) other;
        return role.equals(otherJob.role)
                && company.equals(otherJob.company)
                && status.equals(otherJob.status)
                && deadline.equals(otherJob.deadline);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(role, company);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("role", role)
                .add("company", company)
                .add("status", status)
                .add("deadline", deadline)
                .toString();
    }
}
