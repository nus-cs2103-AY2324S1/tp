package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

import java.time.MonthDay;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;

/**
 * Represents a Person's birthday in the address book.
 * Guarantees: immutable;
 */
public class Birthday {

    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM");

    /**
     * Message constraints for birthday which is the format used for user input.
     */
    public static final String MESSAGE_INVALID = "Birthday should be in the format of dd/MM and should be a valid day."
            + "\ne.g. 31/01";

    public final MonthDay birthday;

    /**
     * Constructs a {@code Birthday}.
     * @param birthday A valid month and day of the person's birthday.
     */
    public Birthday(MonthDay birthday) {
        requireNonNull(birthday);
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        String monthWord = birthday.getMonth().getDisplayName(TextStyle.FULL, Locale.getDefault());
        int day = birthday.getDayOfMonth();
        return day + " " + monthWord;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Birthday)) {
            return false;
        }

        Birthday otherBirthday = (Birthday) other;
        return birthday.equals(otherBirthday.birthday);
    }

    @Override
    public int hashCode() {
        return birthday.hashCode();
    }

}
