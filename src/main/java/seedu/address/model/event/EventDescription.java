package seedu.address.model.event;

import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.Objects;


/**
 * Represents a Event's description in the events book.
 */
public class EventDescription {
    public static final String MESSAGE_CONSTRAINTS = "Descriptions can take any values up to 256 characters"
            + " and should not be blank.";
    public static final String VALIDATION_REGEX = "^(?!\\s)[\\s\\S]{0,256}$";
    public final String value;
    /**
     * Constructs a {@code EventDescription}.
     *
     * @param descriptionText A valid description.
     */
    public EventDescription(String descriptionText) {
        checkArgument(isValidDescription(descriptionText), MESSAGE_CONSTRAINTS);
        this.value = descriptionText;
    }

    public String getDescription() {
        return value;
    }

    public static boolean isValidDescription(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        EventDescription eventDescription = (EventDescription) obj;
        return Objects.equals(value, eventDescription.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return value;
    }
}
