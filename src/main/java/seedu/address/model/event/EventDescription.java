package seedu.address.model.event;

/**
 * Represents the description of an event.
 */
public class EventDescription {
    public static final String MESSAGE_CONSTRAINTS = "Description cannot be empty.";
    private final String description;

    /**
     * Constructs an EventDescription object with the given description.
     *
     * @param description The description of the event.
     */
    public EventDescription(String description) {
        this.description = description;
    }

    /**
     * Creates and returns a new EventDescription object with a placeholder description.
     *
     * @return A new EventDescription object with a placeholder description.
     */
    public static EventDescription createUnusedDescription() {
        return new EventDescription("THIS IS A PLACEHOLDER");
    }

    /**
     * Checks if the provided event description is valid.
     *
     * @param description The event description to be validated.
     * @return True if the description is not empty, false otherwise.
     */
    public boolean isEventDescriptionValid(String description) {
        return !description.isEmpty();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof EventDescription)) {
            return false;
        }

        EventDescription otherDescription = (EventDescription) other;
        return otherDescription.description.equals(this.description);
    }

    @Override
    public String toString() {
        return this.description;
    }
}
