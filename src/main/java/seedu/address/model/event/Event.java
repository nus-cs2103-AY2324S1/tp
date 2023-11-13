package seedu.address.model.event;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

/**
 * Represents an event in the calendar
 */
public class Event {
    private EventDescription description;
    private EventPeriod eventPeriod;
    private Optional<Event> parentEvent;

    /**
     * Constructs an event with a given description occurring over the given time period.
     *
     * @param description The description of the event.
     * @param eventPeriod The period of time when the event occurs.
     */
    public Event(EventDescription description, EventPeriod eventPeriod) {
        requireAllNonNull(description, eventPeriod);

        this.description = description;
        this.eventPeriod = eventPeriod;
        this.parentEvent = Optional.<Event>empty();
    }

    /**
     * Constructs an event with a given description occurring over a given time period with a parent Event.
     *
     * @param description The description of the event.
     * @param eventPeriod The period of the time when the event occurs.
     * @param parent parent Event object this Event object was dervied from.
     */
    private Event(EventDescription description, EventPeriod eventPeriod, Event parent) {
        requireAllNonNull(description, eventPeriod, parent);

        this.description = description;
        this.eventPeriod = eventPeriod;
        this.parentEvent = Optional.<Event>of(parent);
    }

    /**
     * Creates and returns a new Event object that is guaranteed to not conflict with any other event.
     *
     * @return A new non-conflicting Event object.
     */
    public static Event createNonConflictingEvent() {
        return new Event(EventDescription.createUnusedDescription(), EventPeriod.createNonConflictingPeriod());
    }

    /**
     * Gets the description of the event.
     *
     * @return The description of the event.
     */
    public EventDescription getDescription() {
        return this.description;
    }

    /**
     * Gets the time period during which the event occurs.
     *
     * @return The time period of the event.
     */
    public EventPeriod getEventPeriod() {
        return this.eventPeriod;
    }

    /**
     * Checks if this event overlaps with another event period
     *
     * @param period The other event period.
     * @return True if there is an overlap, false otherwise.
     */
    public boolean isPeriodConflicting(EventPeriod period) {
        requireNonNull(period);

        return eventPeriod.isOverlapping(period);
    }

    /**
     * Checks if this event conflicts with another event.
     *
     * @param other The other event to check for conflicts with.
     * @return True if there is a conflict, false otherwise.
     */
    public boolean isConflicting(Event other) {
        requireNonNull(other);

        return isPeriodConflicting(other.eventPeriod);
    }

    /**
     * Changes the description of the event.
     *
     * @param description The new description for the event.
     */
    public void changeDescription(EventDescription description) {
        requireNonNull(description);

        this.description = description;
    }

    /**
     * Changes the time period during which the event occurs.
     *
     * @param eventPeriod The new time period for the event.
     */
    public void changeEventPeriod(EventPeriod eventPeriod) {
        requireNonNull(eventPeriod);

        this.eventPeriod = eventPeriod;
    }

    /**
     * Get the dates the event spans, stored in a list.
     *
     * @return list of the dates the event spans.
     */
    public List<LocalDate> getEventDays() {
        return this.eventPeriod.getDates();
    }

    /**
     * Checks if the {@code @LocalDateTime} is during the event.
     *
     * @param dateTime the specified {@code @LocalDateTime}.
     * @return true if the time is during the event, false otherwise.
     */
    public boolean isDuring(LocalDateTime dateTime) {
        requireNonNull(dateTime);

        return this.eventPeriod.isWithin(dateTime);
    }

    /**
     * Bounds the {@code @Event} such that the {@code @EventPeriod} of the formatted event happens within
     * the specified {@code @LocalDate}.
     *
     * @param date the specified {@code @LocalDate}.
     * @return new {@code @Event} with adjusted {@code @EventPeriod}.
     */
    public Event boundEventByDate(LocalDate date) {
        requireNonNull(date);
        if (hasParent()) {
            return this;
        }
        return new Event(description, eventPeriod.boundPeriodByDate(date), this);
    }

    /**
     * Get the parent Event object of this Event object.
     *
     * @return the parent Event object. If there is no parent, returns itself.
     */
    public Event getParentEvent() {
        return parentEvent.orElse(this);
    }

    /**
     * Checks if this Event object has a parent Event.
     *
     * @return true if it has a parent Event object, false otherwise.
     */
    public boolean hasParent() {
        return parentEvent.isPresent();
    }

    public LocalDateTime getStartDateTime() {
        if (hasParent()) {
            return parentEvent.get().getStartDateTime();
        }
        return eventPeriod.getStart();
    }

    /**
     * Checks if the Event's eventDuration spans a single day.
     *
     * @return true if it spans a single day, false otherwise.
     */
    private boolean isEventDurationSingleDay() {
        return eventPeriod.isSingleDay();
    }

    /**
     * Checks if parent Event's EventPeriod overlaps with the start and end dates(inclusive).
     *
     * @param start start date.
     * @param end end date.
     * @return true if the parent Event's EventPeriod overlaps with the start and end date.
     */
    public boolean occursBetweenDates(LocalDate start, LocalDate end) {
        requireAllNonNull(start, end);
        return eventPeriod.isOverlapping(start, end);
    }

    /**
     * Compare the start time (independent of date) of this Event with another.
     *
     * @param other other Event.
     * @return a negative integer if this Event has an earlier start time than the other, 0 if they have the same start
     *     time and a positive integer otherwise.
     */
    public int compareStartTime(Event other) {
        requireNonNull(other);

        return this.eventPeriod.compareStartTime(other.eventPeriod);
    }

    /**
     * Compare the end time (independent of date) of this Event with another.
     *
     * @param other other Event.
     * @return a negative integer if this Event has an earlier end time than the other, 0 if they have the same end time
     *     and a positive integer otherwise.
     */
    public int compareEndTime(Event other) {
        requireNonNull(other);

        return this.eventPeriod.compareEndTime(other.eventPeriod);
    }

    /**
     * Get the start time of the event as a LocalTime object, omitting the date.
     *
     * @return the start time of the event as a LocalTime object, omitting the date.
     */
    public LocalTime getStartTime() {
        return eventPeriod.getStartTime();
    }

    /**
     * Get the end time of the event as a LocalTime object, omitting the date.
     *
     * @return the end time of the event as a LocalTime object, omitting the date.
     */
    public LocalTime getEndTime() {
        return eventPeriod.getEndTime();
    }

    /**
     * Get the duration of the Event, stored in a LocalTime object.
     *
     * @return LocalTime object of the duration of the Event.
     */
    public Duration getDurationOfEvent() {
        return eventPeriod.getDuration();
    }

    /**
     * Get the description of the Event in a String format.
     *
     * @return String of the description of the Event.
     */
    public String getDescriptionString() {
        return description.getDescription();
    }

    /**
     * Gets the time period during which the event occurs in a String format.
     *
     * @return String of the time period of the event.
     */
    public String getEventPeriodString() {
        return eventPeriod.getFormattedPeriod();
    }

    /**
     * Get the DayOfWeek of the Event. Event has to have a EventPeriod that spans a single day.
     *
     * @return DayOfWeek of the Event.
     */
    public DayOfWeek getDayOfWeek() {
        assert(isEventDurationSingleDay());

        return eventPeriod.getDayOfWeek();
    }

    /**
     * Get the number of minutes elapsed from the input time to the start time of the Event.
     *
     * @param time input time.
     * @return number of minutes elapsed from the input time to the start time of the Event.
     */
    public long getMinutesFromTimeToStartTime(LocalTime time) {
        requireNonNull(time);
        return eventPeriod.getMinutesFromTimeToStartTime(time);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Event)) {
            return false;
        }

        Event otherEvent = (Event) other;
        return (otherEvent.description.equals(this.description)
                && (otherEvent.eventPeriod.equals(this.eventPeriod)
                        || otherEvent.eventPeriod.isContinuous(this.eventPeriod)));
    }
}
