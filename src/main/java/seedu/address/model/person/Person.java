package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.event.Event;
import seedu.address.model.event.EventID;
import seedu.address.model.event.exceptions.EventNotFoundException;
import seedu.address.model.note.Note;
import seedu.address.model.note.NoteID;
import seedu.address.model.tag.Tag;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated,
 * immutable.
 */
public class Person {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;

    // Data fields
    private final Address address;
    private final Set<Tag> tags = new HashSet<>();
    private final ObservableList<Note> notes = FXCollections.observableArrayList();
    private final ObservableList<Event> events = FXCollections.observableArrayList();

    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Phone phone, Email email, Address address, Set<Tag> tags, List<Note> notes,
            List<Event> events) {
        requireAllNonNull(name, phone, email, address, tags, notes, events);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.tags.addAll(tags);
        this.notes.addAll(notes);
        this.events.addAll(events);
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

    /**
     * Returns an immutable tag set, which throws
     * {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Checks whether the person contains a certain tag.
     *
     * @param tag the tag to be checked.
     * @return A boolean representing contains or doesn't contain the tag.
     */
    public boolean containsTag(Tag tag) {
        return tags.contains(tag);
    }

    /**
     * Returns an immutable notes, which throws
     * {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public ObservableList<Note> getNotes() {
        return FXCollections.unmodifiableObservableList(notes);
    }

    /**
     * Returns an immutable events, which throws
     * {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public ObservableList<Event> getEvents() {
        return FXCollections.unmodifiableObservableList(events);
    }

    /**
     * Adds a note to this person
     *
     * @param note The note to be added.
     */
    public void addNote(Note note) {
        this.notes.add(note);
    }

    /**
     * Adds a set of {@code Tag} to this person
     *
     * @param tags The tags to be added.
     */
    public void addTags(Set<Tag> tags) {
        this.tags.addAll(tags);
    }

    /**
     * Removes a set of {@code Tag} from this person
     *
     * @param tags The tags to be removed.
     */
    public void removeTags(Set<Tag> tags) {
        tags.forEach(tag -> this.tags.remove(tag));
    }

    /**
     * Removes a note by its user-friendly id
     *
     * @param id The id of the note you want to remove
     * @return The note object that is just deleted if the operation is successful
     *         or {@code null} if the note with this name does not exist
     */
    public Note removeNoteByUserFriendlyId(NoteID id) {
        return this.removeNoteByIndex(id.getId() - 1);
    }

    private Note removeNoteByIndex(int index) {
        if (index < 0 || index >= this.notes.size()) {
            return null;
        }
        return this.notes.remove(index);
    }

    /**
     * Adds an event to this person.
     *
     * @param event The event to be added.
     */
    public void addEvent(Event event) {
        this.events.add(event);
    }

    /**
     * Removes an event by its user-friendly id
     *
     * @param id The id of the event you want to remove
     * @return The event object that is just deleted if the operation is successful
     *         or {@code null} if the event with this name does not exist
     */
    public Event removeEventByUserFriendlyId(EventID id) {
        return this.removeEventByIndex(id.getId() - 1);
    }

    private Event removeEventByIndex(int index) {
        if (index < 0 || index >= this.events.size()) {
            throw new EventNotFoundException();
        }
        return this.events.remove(index);
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     */
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(otherPerson instanceof Person)) {
            return false;
        }

        // Checks everything except tags
        return name.equals(otherPerson.name)
                && phone.equals(otherPerson.phone)
                && email.equals(otherPerson.email)
                && address.equals(otherPerson.address);
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
                && address.equals(otherPerson.address);
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
