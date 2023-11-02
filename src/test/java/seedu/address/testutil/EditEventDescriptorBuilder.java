package seedu.address.testutil;

import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditContactEventCommand;
import seedu.address.model.calendar.Calendar;
import seedu.address.model.event.Event;
import seedu.address.model.event.EventDescription;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;

import java.time.format.DateTimeFormatter;

public class EditEventDescriptorBuilder {

    private EditContactEventCommand.EditEventDescriptor descriptor;

    public EditEventDescriptorBuilder() {
        descriptor = new EditContactEventCommand.EditEventDescriptor();
    }

    public EditEventDescriptorBuilder(EditContactEventCommand.EditEventDescriptor descriptor) {
        this.descriptor = new EditContactEventCommand.EditEventDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditEventDescriptor} with fields containing {@code event}'s details
     */
    public EditEventDescriptorBuilder(Event event) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        descriptor = new EditContactEventCommand.EditEventDescriptor();
        descriptor.setEventDescription(event.getDescription());
        descriptor.setStart(event.getEventPeriod().getStart().format(formatter));
        descriptor.setEnd(event.getEventPeriod().getEnd().format(formatter));
    }

    /**
     * Sets the {@code eventDescription} of the {@code EditEventDescriptor} that we are building.
     */
    public EditEventDescriptorBuilder withEventDescription(String eventDescription) {
        descriptor.setEventDescription(new EventDescription(eventDescription));
        return this;
    }

    /**
     * Sets the {@code eventDescription} of the {@code EditEventDescriptor} that we are building.
     */
    public EditEventDescriptorBuilder withEventStartTime(String eventStartTime) {
        descriptor.setStart(eventStartTime);
        return this;
    }

    /**
     * Sets the {@code eventDescription} of the {@code EditEventDescriptor} that we are building.
     */
    public EditEventDescriptorBuilder withEventEndTime(String eventEndTime) {
        descriptor.setEnd(eventEndTime);
        return this;
    }

    public EditContactEventCommand.EditEventDescriptor build() {
        return descriptor;
    }
}
