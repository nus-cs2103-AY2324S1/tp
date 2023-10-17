package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION;

import seedu.address.model.event.EventDescription;

/**
 * Creates an EventDescriptionBuilder with default description.
 */
public class EventDescriptionBuilder {
    private static final String DEFAULT_DESCRIPTION = VALID_DESCRIPTION;
    private EventDescription eventDescription;

    /**
     * Creates an EventPeriodBuilder object with default description;
     */
    public EventDescriptionBuilder() {
        this.eventDescription = new EventDescription(DEFAULT_DESCRIPTION);
    }

    /**
     * Creates a EventDescription with given EventDescriptionBuilder attributes.
     *
     * @return EventDescription corresponding to the EventDescriptionBuilder attributes.
     */
    public EventDescription build() {
        return this.eventDescription;
    }

    /**
     * Change the description.
     *
     * @param newDescription String representing the description to be changed to.
     * @return EventDescriptionBuilder object with new description.
     */
    public EventDescriptionBuilder changeDescription(String newDescription) {
        this.eventDescription = new EventDescription(newDescription);
        return this;
    }
}
