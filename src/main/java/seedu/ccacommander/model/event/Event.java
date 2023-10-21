package seedu.ccacommander.model.event;

import static seedu.ccacommander.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

import seedu.ccacommander.commons.util.ToStringBuilder;
import seedu.ccacommander.model.shared.Name;

/**
 * Represents an Event in the ccacommander book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Event {

    // Identity fields
    private final Name name;

    // Data fields
    private final Location location;
    private final EventDate eventDate;

    /**
     * Every field must be present and not null.
     */
    public Event(Name name, EventDate eventDate, Location location) {
        requireAllNonNull(name, eventDate, location);
        this.name = name;
        this.eventDate = eventDate;
        this.location = location;
    }

    public Name getName() {
        return this.name;
    }

    public Location getLocation() {
        return this.location;
    }
    public EventDate getDate() {
        return this.eventDate;
    }

    /**
     * Returns true if both events have the same name.
     * This defines a weaker notion of equality between two events.
     */
    public boolean isSameEvent(Event otherEvent) {
        if (otherEvent == this) {
            return true;
        }

        return otherEvent != null
                && otherEvent.getName().equals(getName())
                && otherEvent.getDate().equals(getDate())
                && otherEvent.getLocation().equals(getLocation());
    }

    /**
     * Returns true if both events have the same identity and data fields.
     * This defines a stronger notion of equality between two events.
     */
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
        return this.name.equals(otherEvent.name)
                && this.location.equals(otherEvent.location)
                && this.eventDate.equals(otherEvent.eventDate);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(this.name, this.location, this.eventDate);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", name)
                .add("date", eventDate)
                .add("location", location)
                .toString();
    }

}
