package seedu.address.logic.commands.edit;

import java.util.ArrayList;
import java.util.Optional;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.person.timetable.DatedEvent;

/**
 * EditUserDescriptor class extends EditPersonDescriptor and represents a descriptor for editing a user.
 * It contains an ArrayList of DatedEvent objects representing the user's dated events.
 */
public class EditUserDescriptor extends EditPersonDescriptor {
    private ArrayList<DatedEvent> datedEvents;

    /**
     * Represents the descriptor for editing a user.
     * Contains the fields to be edited and their new values.
     */
    public EditUserDescriptor() {
        super();
        datedEvents = new ArrayList<>();
    }

    /**
     * Copy constructor.
     * A defensive copy of {@code tags} is used internally.
     */
    public EditUserDescriptor(EditUserDescriptor toCopy) {
        super(toCopy);
        setDatedEvents(toCopy.datedEvents);
    }

    /**
     * Returns an optional list of dated events of the user.
     * If the list is null, returns an empty optional.
     *
     * @return An optional list of dated events of the user.
     */
    public Optional<ArrayList<DatedEvent>> getDatedEvents() {
        return Optional.ofNullable(datedEvents);
    }

    /**
     * Sets the list of dated events for the user to the given list of dated events.
     * @param datedEvents The list of dated events to set for the user.
     */
    public void setDatedEvents(ArrayList<DatedEvent> datedEvents) {
        this.datedEvents = datedEvents;
    }

    /**
     * Returns true if the given object is equal to this EditUserDescriptor object.
     * Two EditUserDescriptor objects are considered equal if they have the same
     * datedEvents and if their super classes are equal.
     *
     * @param other The object to compare with this EditUserDescriptor object.
     * @return True if the given object is equal to this EditUserDescriptor object, false otherwise.
     */
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditUserDescriptor)) {
            return false;
        }

        // state check
        EditUserDescriptor e = (EditUserDescriptor) other;

        return super.equals(e)
                && datedEvents.equals(e.datedEvents);
    }

    /**
     * Returns a string representation of the EditUserDescriptor object.
     * Includes the name, phone, email, address, birthday, free times, tags, and dated events.
     *
     * @return String representation of the EditUserDescriptor object.
     */
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", super.getName())
                .add("phone", super.getPhone())
                .add("email", super.getEmail())
                .add("address", super.getAddress())
                .add("birthday", super.getBirthday())
                .add("free times", super.getSchedule())
                .add("tags", super.getTags())
                .add("dated events", datedEvents)
                .toString();
    }
}
