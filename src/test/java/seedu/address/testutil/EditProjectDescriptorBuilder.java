package seedu.address.testutil;

import java.util.ArrayList;
import java.util.List;

import seedu.address.logic.commands.edit.EditProjectCommand;
import seedu.address.model.project.Deadline;
import seedu.address.model.project.Description;
import seedu.address.model.project.Project;

/**
 * A utility class to help with building EditProjectDescriptor objects.
 */
public class EditProjectDescriptorBuilder {

    private final EditProjectCommand.EditProjectDescriptor descriptor;

    public EditProjectDescriptorBuilder() {
        descriptor = new EditProjectCommand.EditProjectDescriptor();
    }

    public EditProjectDescriptorBuilder(EditProjectCommand.EditProjectDescriptor descriptor) {
        this.descriptor = new EditProjectCommand.EditProjectDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditProjectDescriptor} with fields containing {@code project}'s details
     */
    public EditProjectDescriptorBuilder(Project project) {
        descriptor = new EditProjectCommand.EditProjectDescriptor();
        descriptor.setDescription(project.getProjectDescription());
        descriptor.setDeadlines(project.getProjectDeadlines());
    }

    /**
     * Sets the {@code Description} of the {@code EditProjectDescriptor} that we are building.
     */
    public EditProjectDescriptorBuilder withDescription(String description) {
        descriptor.setDescription(new Description(description));
        return this;
    }

    /**
     * Sets the {@code Deadlines} of the {@code EditProjectDescriptor} that we are building.
     */
    public EditProjectDescriptorBuilder withDeadlines(String... deadlines) {
        List<Deadline> deadlineList = new ArrayList<>();
        int index = 1;
        for (String deadline : deadlines) {
            deadlineList.add(new Deadline(deadline, index));
            index += 1;
        }
        descriptor.setDeadlines(deadlineList);
        return this;
    }

    /**
     * Sets the {@code Deadline} of the {@code EditProjectDescriptor} that we are building.
     */
    public EditProjectDescriptorBuilder withDeadline(String deadline) {
        List<Deadline> deadlineList = new ArrayList<>();
        deadlineList.add(new Deadline(deadline, 1));
        descriptor.setDeadlines(deadlineList);
        return this;
    }

    /**
     * Builds and returns the EditProjectDescriptor.
     */
    public EditProjectCommand.EditProjectDescriptor build() {
        return descriptor;
    }
}
