package seedu.application.testutil;

import seedu.application.model.job.Company;
import seedu.application.model.job.Deadline;
import seedu.application.model.job.Job;
import seedu.application.model.job.Role;

/**
 * A utility class to help with building Job objects.
 */
public class JobBuilder {

    public static final String DEFAULT_ROLE = "Student";
    public static final String DEFAULT_COMPANY = "Sparkletots";
    public static final String DEFAULT_DEADLINE = Deadline.TO_ADD_DEADLINE;

    private Role role;
    private Company company;
    private Deadline deadline;

    /**
     * Creates a {@code JobBuilder} with the default details.
     */
    public JobBuilder() {
        role = new Role(DEFAULT_ROLE);
        company = new Company(DEFAULT_COMPANY);
        deadline = new Deadline(DEFAULT_DEADLINE);
    }

    /**
     * Initializes the JobBuilder with the data of {@code jobToCopy}.
     */
    public JobBuilder(Job jobToCopy) {
        role = jobToCopy.getRole();
        company = jobToCopy.getCompany();
        deadline = jobToCopy.getDeadline();
    }

    /**
     * Sets the {@code role} of the {@code Job} that we are building.
     */
    public JobBuilder withRole(String role) {
        this.role = new Role(role);
        return this;
    }

    /**
     * Sets the {@code Company} of the {@code Job} that we are building.
     */
    public JobBuilder withCompany(String company) {
        this.company = new Company(company);
        return this;
    }

    /**
     * Sets the {@code Deadline} of the {@code Job} that we are building.
     */
    public JobBuilder withDeadline(String deadline) {
        this.deadline = new Deadline(deadline);
        return this;
    }

    public Job build() {
        return new Job(role, company, deadline);
    }

}
