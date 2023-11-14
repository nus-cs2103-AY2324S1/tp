package seedu.address.model.event;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.person.Person;

/**
 * Represents an Event in the events book.
 * Guarantees: field values are validated, immutable.
 */
public class Event implements Comparable<Event> {

    private final EventName eventName;
    private final Duration duration;
    private Set<Person> clients = new HashSet<>();
    private final Location location;
    private final EventDescription eventDescription;

    /**
     * Constructs an {@code Event}.
     *
     * @param eventName A valid name.
     * @param duration A valid duration with start and end
     * @param clients An optional list of clients.
     * @param location An optional location.
     * @param eventDescription An optional description.
     */
    public Event(EventName eventName, Duration duration, Set<Person> clients, Location location,
                 EventDescription eventDescription) {
        requireAllNonNull(eventName, duration);
        this.eventName = eventName;
        this.duration = duration;
        this.clients.addAll(clients);
        this.location = location;
        this.eventDescription = eventDescription;
    }

    public EventName getEventName() {
        return eventName;
    }

    public Duration getDuration() {
        return duration;
    }

    public Set<Person> getClients() {
        return Collections.unmodifiableSet(clients); // Return a copy of the client list
    }

    public String getClientNames() {
        return clients.stream()
                .map(Person::getName)
                .map(Object::toString)
                .collect(Collectors.joining(", "));
    }


    public Location getLocation() {
        return location;
    }

    public EventDescription getDescription() {
        return eventDescription;
    }

    /**
     * Replace {@code clients} with set of {@code actualClients} found in the address book.
     */
    public void setMatchedClientInstance(Set<Person> actualClients) {
        clients = actualClients;
    }

    /**
     * Checks if both events have the same parameters.
     * This defines a weaker notion of equality between two events.
     */
    public boolean isSameEvent(Event other) {
        boolean isSame = eventName.equals(other.getEventName());
        isSame = isSame && duration.equals(other.getDuration());
        isSame = isSame && clients.equals(other.getClients());
        isSame = isSame && location.equals(other.getLocation());
        isSame = isSame && eventDescription.equals(other.getDescription());
        return isSame;
    }

    public boolean isAfterToday() {
        return duration.isAfterNow();
    }

    /**
     * Returns true if both events have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
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
        return eventName.equals(otherEvent.eventName)
                && duration.equals(otherEvent.duration)
                && clients.stream().allMatch(client -> otherEvent.clients.stream().anyMatch(
                        otherClient -> client.isSamePerson(otherClient)))
                && clients.size() == otherEvent.clients.size()
                && location.equals(otherEvent.location)
                && eventDescription.equals(otherEvent.eventDescription);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(eventName, duration, clients, location, eventDescription);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", eventName)
                .add("duration", duration)
                .add("clients", clients)
                .add("location", location)
                .add("description", eventDescription)
                .toString();
    }

    /**
     * Compares {@code Event} objects using the {@code Duration} parameter.
     *
     * @param other the object to be compared.
     */
    @Override
    public int compareTo(Event other) {
        return duration.compareTo(other.duration);
    }
}
