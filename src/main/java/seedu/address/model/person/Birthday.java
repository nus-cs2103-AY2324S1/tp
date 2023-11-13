package seedu.address.model.person;

import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Person's birthday in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidBirthday(String)}
 */
public class Birthday {

    public static final String MESSAGE_CONSTRAINTS =
            "Birthdays should only contain numbers, and it should be in yyyy-MM-dd format";
    public static final String VALIDATION_REGEX = "\\d{4}-\\d{2}-\\d{2}";
    public static final Birthday NULL_BIRTHDAY;

    static {
        try {
            NULL_BIRTHDAY = new Birthday("");
        } catch (IllegalArgumentException e) {
            throw new RuntimeException(e);
        }
    }
    private String stringValue;
    private LocalDate value;
    /**
     * Constructs a {@code Birthday}.
     *
     * @param birthday A valid birthday.
     */
    public Birthday(String birthday) {
        checkArgument(isValidBirthday(birthday), MESSAGE_CONSTRAINTS);
        this.stringValue = birthday;
        if (birthday.trim().isEmpty()) {
            this.value = null;
            return;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate parsedDate = LocalDate.parse(birthday, formatter);
        this.value = parsedDate;
    }
    /**
     * Returns true if a given string is a valid birthday.
     */
    public static boolean isValidBirthday(String test) {
        if (test.trim().isEmpty()) {
            return true;
        }

        if (test.matches(VALIDATION_REGEX)) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate parsedDate = LocalDate.parse(test, formatter);
                return true;
            } catch (java.time.format.DateTimeParseException e) {
                return false;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return (value == null) ? "" : value.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    public String getStringValue() {
        return stringValue;
    }

    public LocalDate getValue() {
        return value;
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
        return value.equals(otherBirthday.value);
    }

    /**
     * Factory method for Birthday.
     * @param birthday the birthday in yyyy-MM-dd format.
     * @return an instance of Birthday.
     * @throws IllegalArgumentException if the given birthday does not follow the format.
     */
    public static Birthday of(String birthday) throws IllegalArgumentException {
        if (!isValidBirthday(birthday)) {
            throw new IllegalArgumentException(MESSAGE_CONSTRAINTS);
        }

        if (birthday.isBlank()) {
            return NULL_BIRTHDAY;
        }

        return new Birthday(birthday);
    }

    /**
     * Returns a string for display.
     */
    public String forDisplay() {
        return (value == null) ? "" : value.format(DateTimeFormatter.ofPattern("dd MMM yyyy"));
    }
    @Override
    public int hashCode() {
        return value.hashCode();
    }

    /**
     * Returns true if the birthday is within the next {@code days} days.
     */
    public boolean isWithinDays(int days) {
        if (value == null) {
            return false;
        }
        LocalDate now = LocalDate.now();
        LocalDate endDate = LocalDate.now().plusDays(days + 1);
        LocalDate birthday = value.withYear(now.getYear());
        if (birthday.isBefore(now)) {
            birthday = birthday.plusYears(1);
        }
        return (birthday.isBefore(endDate) && birthday.isAfter(now)) || birthday.isEqual(now);
    }
}
