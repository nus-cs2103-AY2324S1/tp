package seedu.address.model.event;

import seedu.address.model.person.Person;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Represents an Event in the address book.
 */
public class Event {
    private String name;
    private String description;
    private LocalDateTime dateTime;
    private ArrayList<Duration> reminderDeltas;
    private Set<Person> members;

    public Event(String name, String description, LocalDateTime dateTime) {
        this.name = name;
        this.description = description;
        this.dateTime = dateTime;
        this.reminderDeltas = new ArrayList<>();
        this.members = new HashSet<>();
    }

    public void addMember(Person member) {
        members.add(member);
    }

    public void addReminder(Duration reminderDelta) {
        reminderDeltas.add(reminderDelta);
    }

    public boolean shouldTriggerReminder(LocalDateTime currentDateTime) {
        for (Duration reminderDelta: reminderDeltas) {
            if (dateTime.minus(reminderDelta).isBefore(currentDateTime)) {
                return true;
            }
        }

        return false;
    }
}
