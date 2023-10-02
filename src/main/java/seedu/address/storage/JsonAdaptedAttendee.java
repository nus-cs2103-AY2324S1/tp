package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.meeting.Attendee;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Tag}.
 */
class JsonAdaptedAttendee {

    private final String attendee;

    /**
     * Constructs a {@code JsonAdaptedAttendee} with the given {@code attendee}.
     */
    @JsonCreator
    public JsonAdaptedAttendee(String attendee) {
        this.attendee = attendee;
    }

    public JsonAdaptedAttendee(Attendee attendee) {
        this.attendee = attendee.getAttendeeName();
    }

    @JsonValue
    public String getAttendee() {
        return attendee;
    }

    /**
     * Converts this Jackson-friendly adapted tag object into the model's {@code attendee} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted attendee.
     */
    public Attendee toModelType() throws IllegalValueException {
        if (!Attendee.isValidAttendee(attendee)) {
            throw new IllegalValueException(Attendee.MESSAGE_CONSTRAINTS);
        }
        return new Attendee(attendee);
    }

}
