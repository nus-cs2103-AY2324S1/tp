package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.internship.ApplicationStatus;
import seedu.address.model.internship.CompanyName;
import seedu.address.model.internship.Deadline;
import seedu.address.model.internship.Duration;
import seedu.address.model.internship.Internship;
import seedu.address.model.internship.Role;
import seedu.address.model.internship.StartDate;
import seedu.address.model.requirement.Requirement;
import seedu.address.model.util.InternshipSampleDataUtil;

/**
 * A utility class to help with building Internship objects.
 */
public class InternshipBuilder {
    public static final String DEFAULT_COMPANY_NAME = "Jane Street";
    public static final String DEFAULT_ROLE = "Software Engineer";
    public static final String DEFAULT_DEADLINE = "06/06/2001";
    public static final String DEFAULT_APPLICATION_STATUS = "Yet to apply";
    public static final String DEFAULT_START_DATE = "20/01/2099";
    public static final String DEFAULT_DURATION = "3";

    private CompanyName companyName;
    private Role role;
    private ApplicationStatus applicationStatus;
    private Deadline deadline;
    private StartDate startDate;
    private Duration duration;
    private Set<Requirement> requirements;

    /**
     * Creates a {@code InternshipBuilder} with the default details.
     */
    public InternshipBuilder() {
        this.companyName = new CompanyName(DEFAULT_COMPANY_NAME);
        this.role = new Role(DEFAULT_ROLE);
        this.applicationStatus = new ApplicationStatus(DEFAULT_APPLICATION_STATUS);
        this.deadline = new Deadline(DEFAULT_DEADLINE, DEFAULT_START_DATE);
        this.startDate = new StartDate(DEFAULT_START_DATE);
        this.duration = new Duration(DEFAULT_DURATION);
        this.requirements = new HashSet<>();
    }

    /**
     * Initializes the InternshipBuilder with the data of {@code internshipToCopy}.
     */
    public InternshipBuilder(Internship internshipToCopy) {
        this.companyName = internshipToCopy.getCompanyName();
        this.role = internshipToCopy.getRole();
        this.applicationStatus = internshipToCopy.getApplicationStatus();
        this.deadline = internshipToCopy.getDeadline();
        this.startDate = internshipToCopy.getStartDate();
        this.duration = internshipToCopy.getDuration();
        this.requirements = new HashSet<>(internshipToCopy.getRequirements());
    }

    /**
     * Sets the {@code CompanyName} of the {@code Internship} that we are building.
     */
    public InternshipBuilder withCompanyName(String companyName) {
        this.companyName = new CompanyName(companyName);
        return this;
    }

    /**
     * Parses the {@code requirements} into a {@code Set<Requirement>} and set it to the {@code Internship} that we are
     * building.
     */
    public InternshipBuilder withRequirements(String ... requirements) {
        this.requirements = InternshipSampleDataUtil.getRequirementSet(requirements);
        return this;
    }

    /**
     * Sets the {@code Role} of the {@code Internship} that we are building.
     */
    public InternshipBuilder withRole(String role) {
        this.role = new Role(role);
        return this;
    }

    /**
     * Sets the {@code ApplicationStatus} of the {@code Internship} that we are building.
     */
    public InternshipBuilder withApplicationStatus(String applicationStatus) {
        this.applicationStatus = new ApplicationStatus(applicationStatus);
        return this;
    }

    /**
     * Sets the {@code Deadline} of the {@code Internship} that we are building.
     */
    public InternshipBuilder withDeadline(String deadline, String startDate) {
        this.deadline = new Deadline(deadline, startDate);
        return this;
    }

    /**
     * Sets the {@code Duration} of the {@code Internship} that we are building.
     */
    public InternshipBuilder withDuration(String duration) {
        this.duration = new Duration(duration);
        return this;
    }

    /**
     * Sets the {@code StartDate} of the {@code Internship} that we are building.
     */
    public InternshipBuilder withStartDate(String startDate) {
        this.startDate = new StartDate(startDate);
        return this;
    }

    /**
     * Builds an internship for testing.
     */
    public Internship build() {
        return new Internship(this.companyName, this.role, this.applicationStatus, this.deadline,
                this.startDate, this.duration, this.requirements);
    }



}
