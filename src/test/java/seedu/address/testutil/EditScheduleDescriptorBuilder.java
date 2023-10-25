package seedu.address.testutil;

import java.time.LocalDateTime;

import seedu.address.logic.commands.EditScheduleCommand;
import seedu.address.model.person.Person;
import seedu.address.model.schedule.EndTime;
import seedu.address.model.schedule.Schedule;
import seedu.address.model.schedule.StartTime;

/**
 * A utility class to help with building EditScheduleDescriptor objects.
 */
public class EditScheduleDescriptorBuilder {
    private EditScheduleCommand.EditScheduleDescriptor descriptor;

    public EditScheduleDescriptorBuilder() {
        descriptor = new EditScheduleCommand.EditScheduleDescriptor();
    }

    public EditScheduleDescriptorBuilder(EditScheduleCommand.EditScheduleDescriptor descriptor) {
        this.descriptor = new EditScheduleCommand.EditScheduleDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditScheduleDescriptor} with fields containing {@code schedule}'s details
     */
    public EditScheduleDescriptorBuilder(Schedule schedule) {
        descriptor = new EditScheduleCommand.EditScheduleDescriptor();
        descriptor.setStartTime(schedule.getStartTime());
        descriptor.setEndTime(schedule.getEndTime());
    }

    /**
     * Sets the {@code StartTime} of the {@code EditScheduleDescriptor} that we are building.
     */
    public EditScheduleDescriptorBuilder withStartTime(String startTime) {
        descriptor.setStartTime(new StartTime(LocalDateTime.parse(startTime)));
        return this;
    }

    /**
     * Sets the {@code StartTime} of the {@code EditScheduleDescriptor} that we are building.
     */
    public EditScheduleDescriptorBuilder withStartTime(StartTime startTime) {
        descriptor.setStartTime(startTime);
        return this;
    }

    /**
     * Sets the {@code EndTime} of the {@code EditScheduleDescriptor} that we are building.
     */
    public EditScheduleDescriptorBuilder withEndTime(String endTime) {
        descriptor.setEndTime(new EndTime(LocalDateTime.parse(endTime)));
        return this;
    }

    /**
     * Sets the {@code EndTime} of the {@code EditScheduleDescriptor} that we are building.
     */
    public EditScheduleDescriptorBuilder withEndTime(EndTime endTime) {
        descriptor.setEndTime(endTime);
        return this;
    }

    public EditScheduleCommand.EditScheduleDescriptor build() {
        return descriptor;
    }
}
