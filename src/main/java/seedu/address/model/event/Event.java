package seedu.address.model.event;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.commons.core.index.Index;
import seedu.address.model.person.Person;

public class Event {
    private final Person person;
    private final Index index;
    private final LocalDateTime start_time;
    private final LocalDateTime end_time;
    private final String description;

    public Event(Person person, String description, LocalDateTime start_time, LocalDateTime end_time) {
        this.person = person;
        this.description = description;
        this.start_time = start_time;
        this.end_time = end_time;
        this.index = null;
    }

    public Event(Index index, String description, LocalDateTime start_time, LocalDateTime end_time) {
        this.index = index;
        this.description = description;
        this.start_time = start_time;
        this.end_time = end_time;
        this.person = null;
    }

    public Person getPerson() {
        return person;
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

    public Index getIndex() {
        return index;
    }

    public boolean isSameEvent(Event otherEvent) {
        if (otherEvent == this) {
            return true;
        }

        return otherEvent != null && otherEvent.getPerson().equals(getPerson())
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

        return person.equals(otherEvent.person)
                && description.equals(otherEvent.description)
                && start_time.equals(otherEvent.start_time)
                && end_time.equals(otherEvent.end_time);
    }

    @Override
    public String toString() {
        ToStringBuilder builder = new ToStringBuilder(this.getClass().getSimpleName());
        builder.add("name", person.getName().fullName)
                .add("description", description)
                .add("start_time", start_time.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")))
                .add("end_time", end_time.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")));

        return builder.toString();
    }
}
