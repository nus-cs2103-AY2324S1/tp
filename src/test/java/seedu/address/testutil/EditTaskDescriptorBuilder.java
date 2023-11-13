package seedu.address.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commands.EditCommand.EditTaskDescriptor;
import seedu.address.model.member.Member;
import seedu.address.model.task.Deadline;
import seedu.address.model.task.Description;
import seedu.address.model.task.Note;
import seedu.address.model.task.Priority;
import seedu.address.model.task.Task;

/**
 * A utility class to help with building EditTaskDescriptor objects.
 */
public class EditTaskDescriptorBuilder {

    private EditTaskDescriptor descriptor;

    public EditTaskDescriptorBuilder() {
        descriptor = new EditTaskDescriptor();
    }

    public EditTaskDescriptorBuilder(EditTaskDescriptor descriptor) {
        this.descriptor = new EditTaskDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditTaskDescriptor} with fields containing {@code task}'s details
     */
    public EditTaskDescriptorBuilder(Task task) {
        descriptor = new EditTaskDescriptor();
        descriptor.setDescription(task.getDescription());
        descriptor.setDeadline(task.getDeadline());
        descriptor.setMembers(task.getMembers());
    }


    /**
     * Sets the {@code deadline} of the {@code EditTaskDescriptor} that we are building.
     */
    public EditTaskDescriptorBuilder withDeadline(Deadline deadline) {
        descriptor.setDeadline(deadline);
        return this;
    }

    /**
     * Sets the {@code Name} of the {@code EditTaskDescriptor} that we are building.
     */
    public EditTaskDescriptorBuilder withDescription(String name) {
        descriptor.setDescription(new Description(name));
        return this;
    }

    /**
     * Parses the {@code members} into a {@code Set<Member>} and set it to the {@code EditTaskDescriptor}
     * that we are building.
     */
    public EditTaskDescriptorBuilder withMembers(String... members) {
        Set<Member> memberSet = Stream.of(members).map(Member::new).collect(Collectors.toSet());

        descriptor.setMembers(memberSet);
        return this;
    }

    /**
     * Sets the {@code note} of the {@code EditTaskDescriptor} that we are building.
     */
    public EditTaskDescriptorBuilder withNote(String note) {
        descriptor.setNote(new Note(note));
        return this;
    }


    /**
     * Sets the {@code priority} of the {@code EditTaskDescriptor} that we are building.
     */
    public EditTaskDescriptorBuilder withPriority(Priority priority) {
        descriptor.setPriority(priority);
        return this;
    }

    public EditTaskDescriptor build() {
        return descriptor;
    }
}
