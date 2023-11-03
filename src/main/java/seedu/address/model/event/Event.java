package seedu.address.model.event;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDateTime;

/**
 * The class for holding an Event
 */
public class Event {
    private final EventTime start;
    private final EventTime end;
    private final EventName name;

    private final EventLocation location;

    private final EventInformation information;

    /**
     * Constructor for the `Event` class
     * @param name The event name
     * @param start The start time of the event
     * @param end The end time of the event
     * @param location The location of the event
     * @param information The information of the event
     */
    public Event(EventName name, EventTime start, EventTime end, EventLocation location, EventInformation information) {
        requireAllNonNull(name, start);
        this.name = name;
        this.start = start;
        this.end = end;
        this.location = location;
        this.information = information;
    }

    /**
     * Constructor for the `Event` class, with Strings as parameters
     * @param name The event name
     * @param start The start time of the event
     * @param end The end time of the event
     * @param location The location of the event
     * @param information The information of the event
     */
    public Event(String name, String start, String end, String location, String information) {
        this.name = EventName.fromString(name);
        this.start = EventTime.fromString(start);
        this.end = EventTime.fromString(end);
        this.location = EventLocation.fromString(location);
        this.information = EventInformation.fromString(information);
    }

    /**
     * Get the name of the event
     * @return The name of the event
     */
    public String getName() {
        return name.toString();
    }

    /**
     * Get the start time of the event, represented in string
     * @return The start time in string
     */
    public String getStartString() {
        return this.start.toString();
    }

    /**
     * Get the end time of the event, represented in string
     * @return The end time in string
     */
    public String getEndString() {
        return this.end.toString();
    }

    /**
     * Get the start time of the event, represented in {@code LocalDateTime}
     * @return The start time in {@code LocalDateTime}
     */
    public LocalDateTime getStartTime() {
        return this.start.getTime();
    }

    /**
     * Get the end time of the event, represented in {@code LocalDateTime}
     * @return The end time in {@code LocalDateTime}
     */
    public LocalDateTime getEndTime() {
        return this.end.getTime();
    }

    /**
     * Get the location of the event, represented in string
     * @return The location in string
     */
    public String getLocationStr() {
        return this.location.toString();
    }

    /**
     * Get the information of the event, represented in string
     * @return The information in string
     */
    public String getInformationStr() {
        return this.information.toString();
    }

    /**
     * Get a string that can be used to represent this event on GUI
     * @return The information in string
     */
    public String getUiText() {
        String result = this.getName() + "\nStarts at: " + this.start;
        if (!this.end.equals(this.start)) {
            result += "\nEnds at: " + this.end;
        }
        if (!this.location.toString().isEmpty()) {
            result += "\nLocation: " + this.location;
        }
        if (!this.information.toString().isEmpty()) {
            result += "\nInformation: " + this.information;
        }
        return result;
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

        Event otherPerson = (Event) other;
        return name.equals(otherPerson.name)
                && start.equals(otherPerson.start)
                && end.equals(otherPerson.end)
                && location.equals(otherPerson.location)
                && information.equals(otherPerson.information);
    }
}
