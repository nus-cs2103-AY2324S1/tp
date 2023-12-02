package seedu.address.testutil;


import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditCompanyDescriptor;
import seedu.address.model.company.ApplicationStatus;
import seedu.address.model.company.Company;
import seedu.address.model.company.Deadline;
import seedu.address.model.company.Email;
import seedu.address.model.company.Name;
import seedu.address.model.company.Phone;
import seedu.address.model.company.Priority;
import seedu.address.model.company.RecruiterName;
import seedu.address.model.company.Role;

/**
 * A utility class to help with building EditCompanyDescriptor objects.
 */
public class EditCompanyDescriptorBuilder {

    private EditCompanyDescriptor descriptor;

    public EditCompanyDescriptorBuilder() {
        descriptor = new EditCompanyDescriptor();
    }

    public EditCompanyDescriptorBuilder(EditCompanyDescriptor descriptor) {
        this.descriptor = new EditCompanyDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditCompanyDescriptor} with fields containing {@code company}'s details
     */
    public EditCompanyDescriptorBuilder(Company company) {
        descriptor = new EditCommand.EditCompanyDescriptor();
        descriptor.setName(company.getName());
        descriptor.setPhone(company.getPhone());
        descriptor.setEmail(company.getEmail());
        descriptor.setPriority(company.getPriority());
        descriptor.setDeadline(company.getDeadline());
        descriptor.setRole(company.getRole());
        descriptor.setRecruiterName(company.getRecruiterName());
        descriptor.setStatus(company.getStatus());
    }

    /**
     * Sets the {@code Name} of the {@code EditCompanyDescriptor} that we are building.
     */
    public EditCompanyDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code EditCompanyDescriptor} that we are building.
     */
    public EditCompanyDescriptorBuilder withPhone(String phone) {
        descriptor.setPhone(new Phone(phone));
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code EditCompanyDescriptor} that we are building.
     */
    public EditCompanyDescriptorBuilder withEmail(String email) {
        descriptor.setEmail(new Email(email));
        return this;
    }

    /**
     * Sets the {@code Role} of the {@code EditCompanyDescriptor} that we are building.
     */

    public EditCompanyDescriptorBuilder withRole(String role) {
        descriptor.setRole(new Role(role));
        return this;
    }

    /**
     * Sets the {@code Deadline} of the {@code EditCompanyDescriptor} that we are building.
     */

    public EditCompanyDescriptorBuilder withDeadline(String deadline) {
        descriptor.setDeadline(new Deadline(deadline));
        return this;
    }

    /**
     * Sets the {@code ApplicationStatus} of the {@code EditCompanyDescriptor} that we are building.
     * @param status
     * @return
     */

    public EditCompanyDescriptorBuilder withStatus(String status) {
        descriptor.setStatus(new ApplicationStatus(status));
        return this;
    }

    /**
     * Sets the {@code RecruiterName} of the {@code EditCompanyDescriptor} that we are building.
     * @param recruiterName
     * @return
     */
    public EditCompanyDescriptorBuilder withRecruiterName(String recruiterName) {
        descriptor.setRecruiterName(new RecruiterName(recruiterName));
        return this;
    }

    /**
     * Sets the {@code Priority} of the {@code EditCompanyDescriptor} that we are building.
     */
    public EditCompanyDescriptorBuilder withPriority(String priority) {
        descriptor.setPriority(new Priority(priority));
        return this;
    }

    public EditCompanyDescriptor build() {
        return descriptor;
    }
}
