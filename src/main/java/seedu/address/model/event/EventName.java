package seedu.address.model.event;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents an Event's name in the events book.
 * Guarantees: immutable; is valid as declared in {@link #isValidEventName(String)}
 */
public class EventName {

    public static final String MESSAGE_CONSTRAINTS =
            "Event names have a limit of 256 characters and it should not be blank";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "^(?!\\s)[\\s\\S]{0,256}$";

    public final String value;

    /**
     * Constructs a {@code Name}.
     *
     * @param name A valid name.
     */
    public EventName(String name) {
        requireNonNull(name);
        if (name.equals("")) {
            throw new IllegalArgumentException(MESSAGE_CONSTRAINTS);
        }
        checkArgument(isValidEventName(name), MESSAGE_CONSTRAINTS);
        value = name;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidEventName(String test) {
        if (test.equals("")) {
            return false;
        }
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EventName)) {
            return false;
        }

        EventName otherEventName = (EventName) other;
        return value.equals(otherEventName.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public String toString() {
        return value;
    }
}
