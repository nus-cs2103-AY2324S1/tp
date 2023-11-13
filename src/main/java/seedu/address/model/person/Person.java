package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javafx.collections.ObservableList;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.calendar.ReadOnlyCalendar;
import seedu.address.model.calendar.UniMateCalendar;
import seedu.address.model.event.Event;
import seedu.address.model.tag.Tag;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;

    // Data fields
    private final Address address;
    private final Set<Tag> tags = new HashSet<>();
    private final UniMateCalendar calendar = new UniMateCalendar();

    /**
     * Primary constructor for a person object.
     * Every field must be present and not null.
     */
    public Person(Name name, Phone phone, Email email, Address address, Set<Tag> tags) {
        requireAllNonNull(name, phone, email, address, tags);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.tags.addAll(tags);
    }

    /**
     * Alternative constructor to be used when replacing the calendar of a contact.
     * Every field must be present and not null.
     *
     */
    public Person(Name name, Phone phone, Email email, Address address, Set<Tag> tags, UniMateCalendar calendar) {
        requireAllNonNull(name, phone, email, address, tags);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.tags.addAll(tags);
        this.calendar.resetData(calendar);
    }


    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }

    public UniMateCalendar getCalendar() {
        return calendar;
    }

    public ReadOnlyCalendar getReadOnlyCalendar() {
        return calendar;
    }

    /**
     * Returns a view of the events belonging to this person
     */
    public ObservableList<Event> getEventList() {
        return calendar.getEventList();
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if both persons have the same name.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return otherPerson != null
                && otherPerson.getName().equals(getName());
    }

    /**
     * Adds an event to the calendar of this Person.
     */
    public void addEvent(Event toAdd) {
        calendar.addEvent(toAdd);
    }

    /**
     * Checks if an event can be added to the calendar of this Person.
     */
    public boolean canAddEvent(Event toAdd) {
        return calendar.canAddEvent(toAdd);
    }

    /**
     * Deletes an event at the specified time from the calendar of this person.
     */
    public void deleteEvent(LocalDateTime targetTime) {
        calendar.deleteEventAt(targetTime);
    }

    /**
     * Looks for an event at the specified time and returns it.
     */
    public Event findEvent(LocalDateTime targetTime) {
        return calendar.findEventAt(targetTime).get();
    }

    /**
     * Checks if the person is tagged with the input tag.
     *
     * @param tag input tag to be checked.
     * @return true if this person is tagged with the input tag.
     */
    public boolean hasTag(Tag tag) {
        return getTags().stream().anyMatch(tag::equals);
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Person)) {
            return false;
        }

        Person otherPerson = (Person) other;
        return name.equals(otherPerson.name)
                && phone.equals(otherPerson.phone)
                && email.equals(otherPerson.email)
                && address.equals(otherPerson.address)
                && tags.equals(otherPerson.tags);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address, tags);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", name)
                .add("phone", phone)
                .add("email", email)
                .add("address", address)
                .add("tags", tags)
                .toString();
    }

}
