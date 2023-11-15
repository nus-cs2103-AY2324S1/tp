package seedu.address.model.user;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Birthday;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.timetable.DatedEvent;
import seedu.address.model.person.timetable.Schedule;
import seedu.address.model.tag.Tag;

/**
 * Represents a User in the address book.
 */
public class User extends Person {
    private ArrayList<DatedEvent> datedEvents = new ArrayList<>();

    /**
     * Every field must be present and not null.
     */
    public User(Name name, Phone phone, Email email, Address address, Birthday birthday,
                Schedule schedule, Set<Tag> tags, ArrayList<DatedEvent> datedEvents) {
        super(name, phone, email, address, birthday, schedule, tags);
        this.datedEvents = datedEvents;
    };

    public User() {
        super();
    }

    /**
     * Constructor for User with a person and dated events.
     */
    public User(Person user, ArrayList<DatedEvent> datedEvents) {
        super(user.getName(), user.getPhone(), user.getEmail(), user.getAddress(), user.getBirthday(),
                user.getSchedule(), user.getTags());
        this.datedEvents = datedEvents;
    }

    /**
     * Constructor for User with a person.
     */
    public User(User user) {
        this(user, user.getDatedEvents());
    }

    public ArrayList<DatedEvent> getDatedEvents() {
        return datedEvents;
    }

    public void setDatedEvents(ArrayList<DatedEvent> datedEvents) {
        this.datedEvents = datedEvents;
    }

    public Optional<DatedEvent> getDatedEvent(String name) {
        List<DatedEvent> datedEvents = getSchedule().getDatedEventsList();
        for (DatedEvent event: datedEvents) {
            if (event.getName().toLowerCase().equals(name.toLowerCase())) {
                return Optional.of(event);
            }
        }
        return Optional.empty();
    }

    /**
     * Sets the reminder for the given event.
     */
    public void setReminder(DatedEvent event) throws CommandException {
        getSchedule().deleteDatedEvent(event.getName());
        getSchedule().addDatedEvent(new DatedEvent(event.getName(), event.getTimeBlockString(),
                event.getDate().toString(), true));
    }

    /**
     * Removes the reminder for the given event.
     */
    public void removeReminder(DatedEvent event) throws CommandException {
        getSchedule().deleteDatedEvent(event.getName());
        getSchedule().addDatedEvent(new DatedEvent(event.getName(), event.getTimeBlockString(),
                event.getDate().toString(), false));
    }

    /**
     * Returns a string of the events that are happening today and have reminders.
     */
    public String returnRemindedEvent() {
        StringBuilder sb = new StringBuilder();
        List<DatedEvent> datedEvents = getSchedule().getTodayDatedEvents();
        for (DatedEvent event: datedEvents) {
            if (event.hasReminder() && event.getDate().equals(LocalDate.now())) {
                sb.append(event.getStringForReminder() + "\n");
            }
        }
        return sb.toString();
    }

}
