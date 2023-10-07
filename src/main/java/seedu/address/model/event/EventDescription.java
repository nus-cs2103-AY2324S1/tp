package seedu.address.model.event;

public class EventDescription {
    public static final String MESSAGE_CONSTRAINTS = "Description cannot be empty.";
    private final String description;

    public EventDescription(String description) {
        this.description = description;
    }

    public static EventDescription createUnusedDescription() {
        return new EventDescription("THIS IS A PLACEHOLDER");
    }

    public boolean isEventDescriptionValid(String description) {
        return !description.isEmpty();
    }

    @Override
    public String toString() {
        return this.description;
    }
}
