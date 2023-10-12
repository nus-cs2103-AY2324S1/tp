package seedu.address.model.event;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import seedu.address.model.person.Person;

/**
 * Represents an Event in the address book.
 */
public class Event {
    private String title;
    private String description;
    private LocalDateTime dateTime;
    private ArrayList<Duration> reminderDeltas;
    private Set<Person> members;

    /**
     * Initialises {@code Event} instance.
     * @param title Title of event.
     * @param description Description of event.
     * @param dateTime Date and time of event.
     */
    public Event(String title, String description, LocalDateTime dateTime) {
        this.title = title;
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

    private boolean shouldTriggerReminder(LocalDateTime currentDateTime) {
        // Should not remind if event has already passed
        if (currentDateTime.isAfter(dateTime)) {
            return false;
        }

        for (Duration reminderDelta: reminderDeltas) {
            if (dateTime.minus(reminderDelta).isBefore(currentDateTime)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Gets notification for event at a specific time.
     * @param currentDateTime Current time.
     * @return {@code Optional.empty()} if no suitable notifications, else returns the notification.
     */
    public Optional<Notification> getNotificationAtTime(LocalDateTime currentDateTime) {
        if (shouldTriggerReminder(currentDateTime)) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            String description = String.format("[%s] %s", dateTime.format(formatter), this.description);
            return Optional.of(new Notification(title, description));
        } else {
            return Optional.empty();
        }
    }
}
