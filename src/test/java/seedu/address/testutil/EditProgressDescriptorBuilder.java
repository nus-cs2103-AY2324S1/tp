package seedu.address.testutil;

import seedu.address.logic.commands.UpdateTaskProgressCommand.EditProgressDescriptor;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskProgress;

/**
 * A utility class to help with building EditProgressDescriptor objects.
 */
public class EditProgressDescriptorBuilder {

    private EditProgressDescriptor descriptor;

    public EditProgressDescriptorBuilder() {
        descriptor = new EditProgressDescriptor();
    }

    public EditProgressDescriptorBuilder(EditProgressDescriptor descriptor) {
        this.descriptor = new EditProgressDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditProgressDescriptor} with fields containing {@code task}'s details
     */
    public EditProgressDescriptorBuilder(Task task) {
        descriptor = new EditProgressDescriptor();
        descriptor.setProgress(task.getProgress());
    }

    /**
     * Sets the {@code Progress} of the {@code EditProgressDescriptor} that we are building.
     */
    public EditProgressDescriptorBuilder withProgress(String name) {
        descriptor.setProgress(TaskProgress.valueOf(name.toUpperCase()));
        return this;
    }

    public EditProgressDescriptor build() {
        return descriptor;
    }

}
