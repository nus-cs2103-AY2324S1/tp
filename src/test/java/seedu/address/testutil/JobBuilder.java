package seedu.address.testutil;

import seedu.address.model.job.Company;
import seedu.address.model.job.Job;
import seedu.address.model.job.Remark;
import seedu.address.model.job.Role;

/**
 * A utility class to help with building Job objects.
 */
public class JobBuilder {

    public static final String DEFAULT_ROLE = "Student";
    public static final String DEFAULT_COMPANY = "Sparkletots";
    public static final String DEFAULT_REMARK = "Pays not bad";

    private Role role;
    private Company company;
    private Remark remark;

    /**
     * Creates a {@code JobBuilder} with the default details.
     */
    public JobBuilder() {
        role = new Role(DEFAULT_ROLE);
        company = new Company(DEFAULT_COMPANY);
        remark = new Remark(DEFAULT_REMARK);
    }

    /**
     * Initializes the JobBuilder with the data of {@code jobToCopy}.
     */
    public JobBuilder(Job jobToCopy) {
        role = jobToCopy.getRole();
        company = jobToCopy.getCompany();
        remark = jobToCopy.getRemark();
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
     * Sets the {@code Remark} of the {@code Person} that we are building.
     */
    public JobBuilder withRemark(String remark) {
        this.remark = new Remark(remark);
        return this;
    }

    public Job build() {
        return new Job(role, company, remark);
    }

}
