package seedu.application.testutil;

import seedu.application.logic.commands.EditCommand.EditJobDescriptor;
import seedu.application.model.job.Company;
import seedu.application.model.job.Deadline;
import seedu.application.model.job.Job;
import seedu.application.model.job.Role;

/**
 * A utility class to help with building EditJobDescriptor objects.
 */
public class EditJobDescriptorBuilder {

    private EditJobDescriptor descriptor;

    public EditJobDescriptorBuilder() {
        descriptor = new EditJobDescriptor();
    }

    public EditJobDescriptorBuilder(EditJobDescriptor descriptor) {
        this.descriptor = new EditJobDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditJobDescriptor} with fields containing {@code job}'s details
     */
    public EditJobDescriptorBuilder(Job job) {
        descriptor = new EditJobDescriptor();
        descriptor.setRole(job.getRole());
        descriptor.setCompany(job.getCompany());
        descriptor.setDeadline(job.getDeadline());
    }

    /**
     * Sets the {@code Role} of the {@code EditJobDescriptor} that we are building.
     */
    public EditJobDescriptorBuilder withRole(String role) {
        descriptor.setRole(new Role(role));
        return this;
    }

    /**
     * Sets the {@code Company} of the {@code EditJobDescriptor} that we are building.
     */
    public EditJobDescriptorBuilder withCompany(String company) {
        descriptor.setCompany(new Company(company));
        return this;
    }

    /**
     * Sets the {@code Deadline} of the {@code EditJobDescriptor} that we are building.
     */
    public EditJobDescriptorBuilder withDeadline(String deadline) {
        descriptor.setDeadline(new Deadline(deadline));
        return this;
    }

    public EditJobDescriptor build() {
        return descriptor;
    }
}
