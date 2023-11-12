package seedu.address.model.event;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.person.Person;

/**
 * Represents an Event in JABPro.
 */
public class Event {
    private final Person person;
    private final Index index;
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;
    private final String description;

    /**
     * Initialises an Event object.
     */
    public Event(Person person, String description, LocalDateTime startTime, LocalDateTime endTime) {
        this.person = person;
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
        this.index = null;
    }

    /**
     * Initialises an Event object.
     */
    public Event(Index index, String description, LocalDateTime startTime, LocalDateTime endTime) {
        this.index = index;
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
        this.person = null;
    }

    public Person getPerson() {
        return person;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getStart_time() {
        return startTime;
    }

    public LocalDateTime getEnd_time() {
        return endTime;
    }

    public Index getIndex() {
        return index;
    }

    /**
     * Returns true if both events are associated with the same person, and have the same description.
     * This defines a weaker notion of equality between two events.
     */
    public boolean isSameEvent(Event otherEvent) {
        if (otherEvent == this) {
            return true;
        }

        return otherEvent != null && otherEvent.getPerson().isSamePerson(getPerson())
                && otherEvent.getDescription().equalsIgnoreCase(getDescription());
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Event)) {
            return false;
        }

        Event otherEvent = (Event) other;
        if (person == null) {
            return index.equals(otherEvent.index)
                    && description.equals(otherEvent.description);
        }
        return person.isSamePerson(otherEvent.person)
                && description.equals(otherEvent.description);
    }

    @Override
    public String toString() {
        ToStringBuilder builder = new ToStringBuilder(this.getClass().getSimpleName());
        builder.add("name", person.getName().fullName)
                .add("description", description)
                .add("startTime", startTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")))
                .add("endTime", endTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));

        return builder.toString();
    }
}
