package seedu.address.testutil;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import seedu.address.model.company.ApplicationStatus;
import seedu.address.model.company.Company;
import seedu.address.model.company.Deadline;
import seedu.address.model.company.Email;
import seedu.address.model.company.Name;
import seedu.address.model.company.Phone;
import seedu.address.model.company.Priority;
import seedu.address.model.company.RecruiterName;
import seedu.address.model.company.Remark;
import seedu.address.model.company.Role;

/**
 * A utility class to help with building Company objects.
 */
public class CompanyBuilder {

    public static final String DEFAULT_NAME = "Twitter";
    public static final String DEFAULT_PHONE = "98782423";
    public static final String DEFAULT_EMAIL = "john@gmail.com";
    public static final String DEFAULT_ROLE = "Software Engineer";
    public static final LocalDate DEFAULT_DEADLINE = LocalDate.of(2023, 10, 10);
    public static final String DEFAULT_STATUS = "PA";
    public static final String DEFAULT_RECRUITER_NAME = "John Doe";
    public static final String DEFAULT_PRIORITY = "LOW";
    public static final String DEFAULT_REMARK = "No remarks";

    private Name name;
    private Phone phone;
    private Email email;
    private Role role;
    private Deadline deadline;
    private ApplicationStatus status;
    private RecruiterName recruiterName;
    private Priority priority;
    private Remark remark;

    /**
     * Creates a {@code CompanyBuilder} with the default details.
     */
    public CompanyBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        role = new Role(DEFAULT_ROLE);
        deadline = new Deadline(DEFAULT_DEADLINE.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        status = new ApplicationStatus(DEFAULT_STATUS);
        recruiterName = new RecruiterName(DEFAULT_RECRUITER_NAME);
        priority = new Priority(DEFAULT_PRIORITY);
        remark = new Remark(DEFAULT_REMARK);
    }

    /**
     * Initializes the CompanyBuilder with the data of {@code companyToCopy}.
     */
    public CompanyBuilder(Company companyToCopy) {
        name = companyToCopy.getName();
        phone = companyToCopy.getPhone();
        email = companyToCopy.getEmail();
        role = companyToCopy.getRole();
        deadline = companyToCopy.getDeadline();
        status = companyToCopy.getStatus();
        recruiterName = companyToCopy.getRecruiterName();
        priority = companyToCopy.getPriority();
        remark = companyToCopy.getRemark();
    }

    /**
     * Sets the {@code Name} of the {@code Company} that we are building.
     */
    public CompanyBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Company} that we are building.
     */
    public CompanyBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Company} that we are building.
     */
    public CompanyBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Sets the {@code Role} of the {@code Company} that we are building.
     */
    public CompanyBuilder withRole(String role) {
        this.role = new Role(role);
        return this;
    }

    /**
     * Sets the {@code Deadline} of the {@code Company} that we are building.
     */
    public CompanyBuilder withDeadline(String deadline) {
        this.deadline = new Deadline(deadline);
        return this;
    }

    /**
     * Sets the {@code ApplicationStatus} of the {@code Company} that we are building.
     */
    public CompanyBuilder withStatus(String status) {
        this.status = new ApplicationStatus(status);
        return this;
    }

    /**
     * Sets the {@code RecruiterName} of the {@code Company} that we are building.
     */
    public CompanyBuilder withRecruiterName(String recruiterName) {
        this.recruiterName = new RecruiterName(recruiterName);
        return this;
    }

    /**
     * Sets the {@code Priority} of the {@code Company} that we are building.
     */
    public CompanyBuilder withPriority(String priority) {
        this.priority = new Priority(priority);
        return this;
    }

    /**
     * Sets the {@code Remark} of the {@code Company} that we are building.
     */
    public CompanyBuilder withRemark(String remark) {
        this.remark = new Remark(remark);
        return this;
    }
    public Company build() {
        return new Company(name, phone, email, role, deadline, status, recruiterName, priority, remark);
    }

}
