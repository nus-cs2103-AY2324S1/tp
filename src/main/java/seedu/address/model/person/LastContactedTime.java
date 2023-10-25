package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * Represents a Person's last contacted time for a meeting in the address book.
 * Guarantees: details are present and not null, field values are validated, mutable.
 */
public class LastContactedTime {

    public static final String MESSAGE_CONSTRAINTS = "LastContactedTime should be in LocalDateTime";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd MMMM yyyy, HHmm");
    private LocalDateTime lastContactedTime;

    /**
     * Constructs a {@code LastContactedTime}
     */
    public LastContactedTime(LocalDateTime lastContactedTime) {
        requireNonNull(lastContactedTime);
        this.lastContactedTime = lastContactedTime;
    }

    public LocalDateTime getTime() {
        return lastContactedTime;
    }

    public static String toDisplayFormat(LocalDateTime dateTime) {
        return dateTime.format(FORMATTER);
    }

    /**
     * Returns true if a given LocalDateTime input is valid.
     */
    public static boolean isValidLastContactedTime(LocalDateTime input) {
        if (input == null) {
            return false;
        }
        return true;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof LastContactedTime)) {
            return false;
        }

        LastContactedTime otherLastContactedTime = (LastContactedTime) other;
        return lastContactedTime.equals(otherLastContactedTime.lastContactedTime);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(lastContactedTime);
    }

    @Override
    public String toString() {
        return lastContactedTime.toString();
    }
}
