package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.DateTimeUtil.verbose;

import java.time.LocalDateTime;
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

    public String getDisplay() {
        if (lastContactedTime.equals(LocalDateTime.MIN)) {
            return "NA";
        }

        return verbose(lastContactedTime);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("lastContactedTime", lastContactedTime).toString();
    }
}
