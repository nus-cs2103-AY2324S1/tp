package seedu.staffsnap.testutil;

import java.util.HashSet;
import java.util.Set;

<<<<<<< HEAD:src/test/java/seedu/staffsnap/testutil/EmployeeBuilder.java
import seedu.staffsnap.model.employee.Email;
import seedu.staffsnap.model.employee.Employee;
import seedu.staffsnap.model.employee.JobTitle;
import seedu.staffsnap.model.employee.Name;
import seedu.staffsnap.model.employee.Phone;
import seedu.staffsnap.model.tag.Tag;
import seedu.staffsnap.model.util.SampleDataUtil;
=======
import seedu.address.model.employee.Address;
import seedu.address.model.employee.Department;
import seedu.address.model.employee.Employee;
import seedu.address.model.employee.Name;
import seedu.address.model.employee.Phone;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;
>>>>>>> 81681bbbe6672d8647326ca44cf820b987267d7b:src/test/java/seedu/address/testutil/EmployeeBuilder.java

/**
 * A utility class to help with building Employee objects.
 */
public class EmployeeBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
<<<<<<< HEAD:src/test/java/seedu/staffsnap/testutil/EmployeeBuilder.java
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_JOB_TITLE = "123, Jurong West Ave 6, #08-111";

    private Name name;
    private Phone phone;
    private Email email;
    private JobTitle jobTitle;
=======
    public static final String DEFAULT_DEPARTMENT = "amy@gmail.com";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";

    private Name name;
    private Phone phone;
    private Department department;
    private Address address;
>>>>>>> 81681bbbe6672d8647326ca44cf820b987267d7b:src/test/java/seedu/address/testutil/EmployeeBuilder.java
    private Set<Tag> tags;

    /**
     * Creates a {@code EmployeeBuilder} with the default details.
     */
    public EmployeeBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
<<<<<<< HEAD:src/test/java/seedu/staffsnap/testutil/EmployeeBuilder.java
        email = new Email(DEFAULT_EMAIL);
        jobTitle = new JobTitle(DEFAULT_JOB_TITLE);
=======
        department = new Department(DEFAULT_DEPARTMENT);
        address = new Address(DEFAULT_ADDRESS);
>>>>>>> 81681bbbe6672d8647326ca44cf820b987267d7b:src/test/java/seedu/address/testutil/EmployeeBuilder.java
        tags = new HashSet<>();
    }

    /**
     * Initializes the EmployeeBuilder with the data of {@code employeeToCopy}.
     */
    public EmployeeBuilder(Employee employeeToCopy) {
        name = employeeToCopy.getName();
        phone = employeeToCopy.getPhone();
<<<<<<< HEAD:src/test/java/seedu/staffsnap/testutil/EmployeeBuilder.java
        email = employeeToCopy.getEmail();
        jobTitle = employeeToCopy.getJobTitle();
=======
        department = employeeToCopy.getDepartment();
        address = employeeToCopy.getAddress();
>>>>>>> 81681bbbe6672d8647326ca44cf820b987267d7b:src/test/java/seedu/address/testutil/EmployeeBuilder.java
        tags = new HashSet<>(employeeToCopy.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code Employee} that we are building.
     */
    public EmployeeBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Employee} that we are building.
     */
    public EmployeeBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code JobTitle} of the {@code Employee} that we are building.
     */
    public EmployeeBuilder withJobTitle(String jobTitle) {
        this.jobTitle = new JobTitle(jobTitle);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Employee} that we are building.
     */
    public EmployeeBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Department} of the {@code Employee} that we are building.
     */
    public EmployeeBuilder withDepartment(String department) {
        this.department = new Department(department);
        return this;
    }

    public Employee build() {
<<<<<<< HEAD:src/test/java/seedu/staffsnap/testutil/EmployeeBuilder.java
        return new Employee(name, phone, email, jobTitle, tags);
=======
        return new Employee(name, phone, department, address, tags);
>>>>>>> 81681bbbe6672d8647326ca44cf820b987267d7b:src/test/java/seedu/address/testutil/EmployeeBuilder.java
    }

}
