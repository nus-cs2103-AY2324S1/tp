package seedu.address.model.internship;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.model.internship.Deadline.DATE_FORMATTER;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.requirement.Requirement;

/**
 * Represents an Internship in Flagship.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Internship {

    /**
     * Identity fields
     *
     * To keep validation simple and Flagship easier to use, we will assume that two internships with identical roles
     * and company names are the same as verified in {@link #isSameInternship(Internship)} even if they may have
     * different data fields such as different requirements.
     */
    private final CompanyName companyName;
    private final Role role;

    // Data fields
    private final ApplicationStatus applicationStatus;

    private final Deadline deadline;
    private final StartDate startDate;
    private final Duration duration;
    private final Set<Requirement> requirements = new HashSet<>();

    /**
     * Constructs a {@code Internship}.
     *
     * @param companyName The company name of the internship.
     * @param role The role of the internship.
     * @param applicationStatus The application status of the internship.
     * @param deadline The deadline to apply for the internship.
     * @param startDate The start date of the internship.
     * @param duration The duration of the internship.
     * @param requirements The requirements of the internship.
     */
    public Internship(CompanyName companyName, Role role, ApplicationStatus applicationStatus, Deadline deadline,
                      StartDate startDate, Duration duration, Set<Requirement> requirements) {
        requireAllNonNull(companyName, role, requirements, applicationStatus, startDate, duration);

        // Final check to make sure that deadline is before start date at the point of Internship's instantiation
        LocalDate deadlineDate = LocalDate.parse(deadline.toString(), DATE_FORMATTER);
        LocalDate startDateDate = LocalDate.parse(startDate.toString(), DATE_FORMATTER);
        assert(deadlineDate.isBefore(startDateDate));

        this.companyName = companyName;
        this.role = role;
        this.applicationStatus = applicationStatus;
        this.deadline = deadline;
        this.startDate = startDate;
        this.duration = duration;
        this.requirements.addAll(requirements);
    }

    public CompanyName getCompanyName() {
        return this.companyName;
    }

    public Role getRole() {
        return this.role;
    }


    /**
     * Returns an immutable requirement set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Requirement> getRequirements() {
        return Collections.unmodifiableSet(this.requirements);
    }

    public ApplicationStatus getApplicationStatus() {
        return this.applicationStatus;
    }

    public Deadline getDeadline() {
        return this.deadline;
    }

    public StartDate getStartDate() {
        return this.startDate;
    }

    public Duration getDuration() {
        return this.duration;
    }

    /**
     * Returns true if both internships have the same company name and role (case-insensitive).
     * This defines a weaker notion of equality between two internships and will be primarily used throughout the
     * program.
     *
     * @param otherInternship The other internship that will be compared to.
     * @return A boolean representing whether the two internships are the same.
     */
    public boolean isSameInternship(Internship otherInternship) {
        if (otherInternship == this) {
            return true;
        }

        return otherInternship != null
                && otherInternship.getCompanyName().equals(this.getCompanyName())
                && otherInternship.getRole().equals(this.getRole());
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("Company Name", this.companyName)
                .add("Role", this.role)
                .add("Application Status", this.applicationStatus)
                .add("Deadline", this.deadline)
                .add("Start Date", this.startDate)
                .add("Duration", this.duration)
                .add("Requirements", this.requirements)
                .toString();
    }

    /**
     * Returns the string representation of the internship entry's role.
     * It is a conscious design choice to implement getters for the various parameters here instead of simply
     * calling the parameters' toString() method. The toString() method may contain additional styling elements in
     * the future based on how we want to modify it for other use cases. These getters are purely called by the
     * filter method and will always return the raw string representation of the parameters without any accessories.
     * This separation ensures that there is no regression in our development when we add more styling to the toString()
     * method in the future.
     */
    public String getRoleString() {
        return this.role.getRole();
    }

    /**
     * Returns string representation of the internship entry's company name.
     */
    public String getCompanyNameString() {
        return this.companyName.getCompanyName();
    }

    /**
     * Returns string representation of the internship entry's application status.
     */
    public String getApplicationStatusString() {
        return this.applicationStatus.getApplicationStatus();
    }

    /**
     * Returns string representation of the internship entry's duration.
     */
    public String getDurationString() {
        return this.duration.getDuration();
    }

    /**
     * Returns true if both internships have the same identity and data fields.
     * This defines a stronger notion of equality between two internships.
     *
     * @param other The other internship to be compared with.
     * @return A boolean representing whether two internships are equal.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Internship)) {
            return false;
        }

        Internship otherInternship = (Internship) other;
        return this.companyName.equals(otherInternship.companyName)
                && this.role.equals(otherInternship.role)
                && this.applicationStatus.equals(otherInternship.applicationStatus)
                && this.deadline.equals(otherInternship.deadline)
                && this.startDate.equals(otherInternship.startDate)
                && this.duration.equals(otherInternship.duration)
                && this.requirements.equals(otherInternship.requirements);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.companyName, this.role, this.applicationStatus, this.deadline,
                this.startDate, this.duration, this.requirements);
    }
}
