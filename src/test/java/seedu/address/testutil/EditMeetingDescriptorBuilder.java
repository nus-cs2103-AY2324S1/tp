package seedu.address.testutil;

import seedu.address.logic.commands.EditMeetingCommand.EditMeetingDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.event.EventDate;
import seedu.address.model.event.EventName;
import seedu.address.model.event.Meeting;

/**
 * A utility class to help with building EditMeetingDescriptor objects.
 */
public class EditMeetingDescriptorBuilder {

    private EditMeetingDescriptor descriptor;

    /**
     * Returns an {@code EditMeetingDescriptor} with fields containing {@code meeting}'s details
     */
    public EditMeetingDescriptorBuilder(Meeting meeting) {
        descriptor = new EditMeetingDescriptor();
        descriptor.setName(meeting.getName());
        descriptor.setDate(meeting.getStartDate());
        descriptor.setPersonNames(meeting.getNames());
    }

    /**
     * Sets the {@code Name} of the {@code EditMeetingDescriptor} that we are building.
     */
    public EditMeetingDescriptorBuilder withName(String name) {
        descriptor.setName(new EventName(name));
        return this;
    }

    /**
     * Sets the {@code Date} of the {@code EditMeetingDescriptor} that we are building.
     */
    public EditMeetingDescriptorBuilder withDate(String date) throws ParseException {
        descriptor.setDate(new EventDate(date));
        return this;
    }

    public EditMeetingDescriptor build() {
        return this.descriptor;
    }
}
