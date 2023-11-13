package seedu.address.model.event;


import static seedu.address.model.event.EventTime.NULL_EVENT_TIME;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import seedu.address.model.group.Group;
import seedu.address.model.person.Name;

/**
 * Represents an Event in the address book.
 */
public abstract class Event {

    public static final String START_TIME_CONSTRAINTS = "You cannot enter a time that is before the current time!";
    public static final String END_TIME_CONSTRAINTS = "You cannot enter an end time that is before the start time!";

    private Set<Name> names;
    private EventDate startDate;
    private Optional<EventTime> startTime;
    private EventDate endDate;
    private Optional<EventTime> endTime;
    private EventName name;
    private EventType eventType;
    private Set<Group> groups;

    /**
     * Constructor for events with optional start and end time
     * @param name name of the event
     * @param startDate start date of the event
     * @param startTime start time of the event
     * @param endDate  end date of the event
     * @param endTime end time of the event
     * @param names names of the people attending the event
     */
    public Event(EventType eventType, EventName name, EventDate startDate, Optional<EventTime> startTime,
                 EventDate endDate, Optional<EventTime> endTime, Set<Name> names, Set<Group> groups) {
        this.eventType = eventType;
        this.name = name;
        this.startDate = startDate;
        this.startTime = startTime;
        this.endDate = endDate;
        this.endTime = endTime;
        this.names = names;
        this.groups = groups;
    }

    public EventType getEventType() {
        return this.eventType;
    }

    public Set<Group> getGroups() {
        return this.groups;
    }

    /**
     * Gets the start date time of the event
     * @return start date time of the event
     */
    public EventDate getStartDate() {
        return this.startDate;
    }

    public EventTime getStartTime() {
        return this.startTime.get();
    }

    /**
     * Returns true if the event has a start time.
     */
    public boolean hasStartTime() {
        return !(this.startTime.get() == NULL_EVENT_TIME);
    }


    /**
     * Returns true if the event has an end time.
     * @return true if the event has an end time
     */
    public boolean hasEndTime() {
        return !(this.endTime.get() == NULL_EVENT_TIME);
    }

    public EventDate getEndDate() {
        return this.endDate;
    }

    public EventTime getEndTime() {
        return this.endTime.get();
    }

    /**
     * Gets the name of the event
     * @return name of the event
     */
    public EventName getName() {
        return this.name;
    }

    /**
     * Returns true if both events are of the same type and have the same name.
     * @param event event to be compared
     * @return true if both events are of the same type and have the same name
     */
    public abstract boolean isSameEvent(Event event);

    public Set<Name> getNames() {
        return this.names;
    }

    /**
     * Returns a set of updated person names when a person's name is edited.
     * @param toEdit name of the person to be edited
     * @param editedName edited name of the person
     * @return set of updated person names
     */
    public Set<Name> getUpdatedNames(Name toEdit, Name editedName) {
        if (!this.names.contains(toEdit)) {
            return this.names;
        }
        Set<Name> newNames = new HashSet<>();
        for (Name name : this.names) {
            if (name.equals(toEdit)) {
                newNames.add(editedName);
            } else {
                newNames.add(name);
            }
        }
        return newNames;
    }

    public void removeEmptyGroups(Set<Group> groups) {
        this.groups.removeAll(groups);
    }

    /**
     * Forces the display to update
     */
    public void updateGroups() {
        this.groups = this.groups;
    }

    /**
     * Returns true if the event is overdue.
     */
    public boolean isOverDue() {
        if (!hasStartTime() && !hasEndTime()) {
            if (LocalDateTime.now().isBefore(this.getStartDate().getDate().atTime(LocalTime.MAX))) {
                return false;
            }
            return true;
        } else if (!hasStartTime()) {
            if (LocalDateTime.now().isBefore(this.getEndDate().getDate()
                    .atTime(this.getEndTime().getEventTime()))) {
                return false;
            }
            return true;
        } else if (!hasEndTime()) {
            if (LocalDateTime.now().isBefore(this.getStartDate().getDate()
                    .atTime(this.getStartTime().getEventTime()))) {
                return false;
            }
            return true;
        } else {
            if (LocalDateTime.now().isBefore(this.getStartDate().getDate()
                    .atTime(this.getStartTime().getEventTime()))) {
                return false;
            }
            return true;
        }
    }

    public boolean hasStartDateWithinDays(int days) {
        return this.startDate.isWithinDays(days);
    }
}
