package seedu.letsgethired.testutil;

import seedu.letsgethired.logic.commands.EditCommand.EditInternApplicationDescriptor;
import seedu.letsgethired.model.application.Company;
import seedu.letsgethired.model.application.Cycle;
import seedu.letsgethired.model.application.Deadline;
import seedu.letsgethired.model.application.InternApplication;
import seedu.letsgethired.model.application.Role;
import seedu.letsgethired.model.application.Status;

/**
 * A utility class to help with building EditInternApplicationDescriptorBuilder objects.
 */
public class EditInternApplicationDescriptorBuilder {

    private EditInternApplicationDescriptor descriptor;

    public EditInternApplicationDescriptorBuilder() {
        descriptor = new EditInternApplicationDescriptor();
    }

    public EditInternApplicationDescriptorBuilder(EditInternApplicationDescriptor descriptor) {
        this.descriptor = new EditInternApplicationDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditInternApplicationDescriptor} with fields containing {@code internApplication}'s details
     */
    public EditInternApplicationDescriptorBuilder(InternApplication internApplication) {
        descriptor = new EditInternApplicationDescriptor();
        descriptor.setCompany(internApplication.getCompany());
        descriptor.setRole(internApplication.getRole());
        descriptor.setCycle(internApplication.getCycle());
        descriptor.setStatus(internApplication.getStatus());
        descriptor.setDeadline(internApplication.getDeadline());
    }

    /**
     * Sets the {@code Company} of the {@code EditInternApplicationDescriptor} that we are building.
     */
    public EditInternApplicationDescriptorBuilder withCompany(String name) {
        descriptor.setCompany(new Company(name));
        return this;
    }

    /**
     * Sets the {@code Role} of the {@code EditInternApplicationDescriptor} that we are building.
     */
    public EditInternApplicationDescriptorBuilder withRole(String role) {
        descriptor.setRole(new Role(role));
        return this;
    }

    /**
     * Sets the {@code Cycle} of the {@code EditInternApplicationDescriptor} that we are building.
     */
    public EditInternApplicationDescriptorBuilder withCycle(String cycle) {
        descriptor.setCycle(new Cycle(cycle));
        return this;
    }

    /**
     * Sets the {@code Status} of the {@code EditInternApplicationDescriptor} that we are building.
     */
    public EditInternApplicationDescriptorBuilder withStatus(String status) {
        descriptor.setStatus(new Status(status));
        return this;
    }

    /**
     * Sets the {@code Deadline} of the {@code EditInternApplicationDescriptor} that we are building.
     */
    public EditInternApplicationDescriptorBuilder withDeadline(String deadline) {
        descriptor.setDeadline(new Deadline(deadline));
        return this;
    }

    public EditInternApplicationDescriptor build() {
        return descriptor;
    }
}
