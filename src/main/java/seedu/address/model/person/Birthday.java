package seedu.address.model.person;

import static seedu.address.commons.util.AppUtil.checkArgument;
import static java.util.Objects.requireNonNull;
import java.time.LocalDate;

import java.time.format.DateTimeFormatter;

public class Birthday {

    public static final String MESSAGE_CONSTRAINTS =
            "Birthdays should only contain numbers, and it should be in yyyy-MM-dd format";
    
    public static final String VALIDATION_REGEX = "\\d{4}-\\d{2}-\\d{2}";
//    public final String value;
    public final LocalDate value;
    /**
     * Constructs a {@code Birthday}.
     *
     * @param birthday A valid birthday.
     */
    
    public Birthday(String birthday) {
        requireNonNull(birthday);
        checkArgument(isValidBirthday(birthday), MESSAGE_CONSTRAINTS);
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate parsedDate = LocalDate.parse(birthday, formatter);
            this.value = parsedDate;
        } catch (java.time.format.DateTimeParseException e) {
            throw new IllegalArgumentException(e);
        }
    }
    
    /**
     * Returns true if a given string is a valid birthday.
     */
    public static boolean isValidBirthday(String test) {
        return test.matches(VALIDATION_REGEX);
    }
    
    @Override
    public String toString() {
        return value.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
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
    
    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
