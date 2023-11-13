package seedu.address.testutil;

import java.time.format.DateTimeFormatter;

import seedu.address.logic.commands.EditContactEventCommand;
import seedu.address.model.event.Event;
import seedu.address.model.event.EventDescription;

/**
 * A builder class for creating an EditContactEventCommand.EditEventDescriptor object.
 */
public class EditEventDescriptorBuilder {

    private EditContactEventCommand.EditEventDescriptor descriptor;

    public EditEventDescriptorBuilder() {
        descriptor = new EditContactEventCommand.EditEventDescriptor();
    }

    /**
     * Constructs a new EditEventDescriptorBuilder with a given EditEventDescriptor.
     */
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

    /**
     * Builds and returns the EditContactEventCommand.EditEventDescriptor object.
     *
     * @return The EditContactEventCommand.EditEventDescriptor object.
     */
    public EditContactEventCommand.EditEventDescriptor build() {
        return descriptor;
    }
}
