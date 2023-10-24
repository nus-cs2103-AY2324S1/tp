package seedu.address.model.event;

import java.time.LocalDateTime;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.commons.core.index.Index;

public class Event {
    private final Index index;
    private final LocalDateTime start_time;
    private final LocalDateTime end_time;
    private final String description;

    public Event(Index index, String description, LocalDateTime start_time, LocalDateTime end_time) {
        this.index = index;
        this.description = description;
        this.start_time = start_time;
        this.end_time = end_time;
    }

    public Index getIndex() {
        return index;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getStart_time() {
        return start_time;
    }

    public LocalDateTime getEnd_time() {
        return end_time;
    }

    public boolean isSameEvent(Event otherEvent) {
        if (otherEvent == this) {
            return true;
        }

        return otherEvent != null && otherEvent.getIndex().equals(getIndex())
                && otherEvent.getDescription().equals(getDescription());
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

        return index.equals(otherEvent.index)
                && description.equals(otherEvent.description)
                && start_time.equals(otherEvent.start_time)
                && end_time.equals(otherEvent.end_time);
    }

    @Override
    public String toString() {
        ToStringBuilder builder = new ToStringBuilder(this);
        builder.add("index", index)
                .add("description", description)
                .add("start_time", start_time)
                .add("end_time", end_time);

        return builder.toString();
    }
}
