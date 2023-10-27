package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import seedu.address.commons.util.ToStringBuilder;

/**
 * Represents a Person's last contacted time for a meeting in the address book.
 * Guarantees: details are present and not null, field values are validated, mutable.
 */
public class LastContactedTime {

    public static final String MESSAGE_CONSTRAINTS =
            "Last Contacted Time should have the format [dd.mm.yyyy HHmm]\n"
            + "eg. 18.09.2023 1500 represents 18 September 2023, 3PM";
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

    public static String toDisplayFormat(LastContactedTime dateTime) {
        return dateTime.lastContactedTime.format(FORMATTER);
    }

    /**
     * Returns true if a given LocalDateTime input is valid.
     */
    public static boolean isValidLastContactedTime(LocalDateTime input) {
        return input.isEqual(LocalDateTime.MIN)
                || input.isAfter(LocalDateTime.MIN) && input.isBefore(LocalDateTime.MAX);
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
        return new ToStringBuilder(this).add("lastContactedTime", lastContactedTime).toString();
    }
}
