package seedu.address.model.event;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;

/**
 * Tests that a {@code Event}'s {@code TimeStart} is before the given time.
 */
public class EventTimeBeforePredicate implements Predicate<Event> {
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
    private final LocalDateTime eventTime;

    public EventTimeBeforePredicate(LocalDateTime eventTime) {
        this.eventTime = eventTime;
    }

    @Override
    public boolean test(Event event) {
        return event.getDuration().hasStartTimeBefore(eventTime);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EventTimeBeforePredicate)) {
            return false;
        }

        EventTimeBeforePredicate otherEventTimeBeforePredicate = (EventTimeBeforePredicate) other;
        return eventTime.equals(otherEventTimeBeforePredicate.eventTime);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("event time", eventTime).toString();
    }
}
