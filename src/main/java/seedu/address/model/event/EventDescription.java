package seedu.address.model.event;

import static java.util.Objects.requireNonNull;

import seedu.address.model.event.exceptions.InvalidDescriptionException;

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
        requireNonNull(description);
        if (description.isEmpty()) {
            throw new InvalidDescriptionException();
        }
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
     * Checks if the given string is a valid description for creating a EventDescription object.
     *
     * @param description description String to be checked.
     * @return true if description is non-empty, false if it is empty.
     */
    public static boolean isValid(String description) {
        requireNonNull(description);
        return !description.isEmpty();
    }

    /**
     * Retrieve the underlying String of the description.
     *
     * @return String of the description.
     */
    public String getDescription() {
        return this.description;
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
