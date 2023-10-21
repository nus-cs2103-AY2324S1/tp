package seedu.address.model.attendance;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.shared.Name;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

/**
 * Represents an attendance in CcaCommander.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Attendance {

    // Identity fields
    private final Name memberName;
    private final Name eventName;

    // Data fields
    private final Present present;
    private final Hours hours;
    private final Note note;

    /**
     * Every field must be present and not null.
     */
    public Attendance(Name memberName, Name eventName, Present present, Hours hours, Note note) {
        requireAllNonNull(memberName, eventName, present, hours, note);
        this.memberName = memberName;
        this.eventName = eventName;
        this.present = present;
        this.hours = hours;
        this.note = note;
    }

    public Name getMemberName() {
        return memberName;
    }

    public Name getEventName() {
        return eventName;
    }

    public Hours getHours() {
        return hours;
    }

    public Present getPresent() {
        return present;
    }

    public Note getNote() {
        return note;
    }

    /**
     * Returns true if both attendance have the same identity and data fields.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Attendance)) {
            return false;
        }

        Attendance otherAttendance = (Attendance) other;
        return memberName.equals(otherAttendance.memberName)
                && eventName.equals(otherAttendance.eventName)
                && present == otherAttendance.present
                && hours == otherAttendance.hours
                && note.equals(otherAttendance.note);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("member name", memberName)
                .add("event name", eventName)
                .add("present", present)
                .add("hours", hours)
                .add("note", note)
                .toString();
    }
}
