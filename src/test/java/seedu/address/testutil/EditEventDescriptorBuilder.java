package seedu.address.testutil;

import seedu.address.logic.commands.EditEventCommand.EditEventDescriptor;
import seedu.address.model.event.Event;
import seedu.address.model.event.EventName;

/**
 * A utility class to help with building EditMeetingDescriptor objects.
 */
public class EditEventDescriptorBuilder {

    private EditEventDescriptor descriptor;

    /**
     * Returns an {@code EditMeetingDescriptor} with fields containing {@code meeting}'s details
     */
    public EditEventDescriptorBuilder(Event event) {
        descriptor = new EditEventDescriptor();
        descriptor.setName(event.getName());
        descriptor.setDate(event.getStartDate());
        descriptor.setStartTime(event.getStartTime());
        descriptor.setEndTime(event.getEndTime());
        descriptor.setPersonNames(event.getNames());
        descriptor.setGroups(event.getGroups());
    }

    /**
     * Sets the {@code Name} of the {@code EditMeetingDescriptor} that we are building.
     */
    public EditEventDescriptorBuilder withName(String name) {
        descriptor.setName(new EventName(name));
        return this;
    }

    public EditEventDescriptor build() {
        return this.descriptor;
    }
}
