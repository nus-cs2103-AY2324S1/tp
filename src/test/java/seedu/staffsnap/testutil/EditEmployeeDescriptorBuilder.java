package seedu.staffsnap.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

<<<<<<< HEAD:src/test/java/seedu/staffsnap/testutil/EditEmployeeDescriptorBuilder.java
import seedu.staffsnap.logic.commands.EditCommand.EditEmployeeDescriptor;
import seedu.staffsnap.model.employee.Email;
import seedu.staffsnap.model.employee.Employee;
import seedu.staffsnap.model.employee.JobTitle;
import seedu.staffsnap.model.employee.Name;
import seedu.staffsnap.model.employee.Phone;
import seedu.staffsnap.model.tag.Tag;
=======
import seedu.address.logic.commands.EditCommand.EditEmployeeDescriptor;
import seedu.address.model.employee.Address;
import seedu.address.model.employee.Department;
import seedu.address.model.employee.Employee;
import seedu.address.model.employee.Name;
import seedu.address.model.employee.Phone;
import seedu.address.model.tag.Tag;
>>>>>>> 81681bbbe6672d8647326ca44cf820b987267d7b:src/test/java/seedu/address/testutil/EditEmployeeDescriptorBuilder.java

/**
 * A utility class to help with building EditEmployeeDescriptor objects.
 */
public class EditEmployeeDescriptorBuilder {

    private EditEmployeeDescriptor descriptor;

    public EditEmployeeDescriptorBuilder() {
        descriptor = new EditEmployeeDescriptor();
    }

    public EditEmployeeDescriptorBuilder(EditEmployeeDescriptor descriptor) {
        this.descriptor = new EditEmployeeDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditEmployeeDescriptor} with fields containing {@code employee}'s details
     */
    public EditEmployeeDescriptorBuilder(Employee employee) {
        descriptor = new EditEmployeeDescriptor();
        descriptor.setName(employee.getName());
        descriptor.setPhone(employee.getPhone());
<<<<<<< HEAD:src/test/java/seedu/staffsnap/testutil/EditEmployeeDescriptorBuilder.java
        descriptor.setEmail(employee.getEmail());
        descriptor.setJobTitle(employee.getJobTitle());
=======
        descriptor.setDepartment(employee.getDepartment());
        descriptor.setAddress(employee.getAddress());
>>>>>>> 81681bbbe6672d8647326ca44cf820b987267d7b:src/test/java/seedu/address/testutil/EditEmployeeDescriptorBuilder.java
        descriptor.setTags(employee.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code EditEmployeeDescriptor} that we are building.
     */
    public EditEmployeeDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code EditEmployeeDescriptor} that we are building.
     */
    public EditEmployeeDescriptorBuilder withPhone(String phone) {
        descriptor.setPhone(new Phone(phone));
        return this;
    }

    /**
     * Sets the {@code Department} of the {@code EditEmployeeDescriptor} that we are building.
     */
    public EditEmployeeDescriptorBuilder withDepartment(String department) {
        descriptor.setDepartment(new Department(department));
        return this;
    }

    /**
     * Sets the {@code JobTitle} of the {@code EditEmployeeDescriptor} that we are building.
     */
    public EditEmployeeDescriptorBuilder withJobTitle(String jobTitle) {
        descriptor.setJobTitle(new JobTitle(jobTitle));
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditEmployeeDescriptor}
     * that we are building.
     */
    public EditEmployeeDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    public EditEmployeeDescriptor build() {
        return descriptor;
    }
}
