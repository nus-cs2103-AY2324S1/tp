package seedu.address.model.commons;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Represents the date a developer Joined. In the format: dd-MM-YYYY
 */
public class Date {
    public static final String MESSAGE_CONSTRAINTS =
            "Date should be of the format dd-MM-yyyy! Eg: 31-12-2019\n"
                    + "For Developers: Date joined should not be in the future.";
    public static final String VALIDATION_REGEX = "[0-3]\\d-[01]\\d-\\d{4}";
    public final java.util.Date value;
    public final boolean allowFuture;

    /**
     * Constructs a {@code DateJoined}.
     *
     * @param date A valid date string.
     */
    public Date(String date, boolean allowFuture) {
        requireNonNull(date);
        this.allowFuture = allowFuture;
        checkArgument(isValidDate(date, allowFuture), MESSAGE_CONSTRAINTS);
        try {
            value = new SimpleDateFormat("dd-MM-yyyy").parse(date);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * Returns true if a given string is a valid date.
     */
    public static boolean isValidDate(String text, boolean allowFuture) {
        if (text == null || !text.matches(VALIDATION_REGEX)) {
            return false;
        }
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        df.setLenient(false);
        try {
            java.util.Date date = df.parse(text);
            if (date.after(new java.util.Date()) && !allowFuture) {
                return false;
            }
            return true;
        } catch (ParseException ex) {
            return false;
        }
    }

    @Override
    public String toString() {
        return new SimpleDateFormat("dd-MM-yyyy").format(value);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Date)) {
            return false;
        }

        Date otherDate = (Date) other;
        return value.equals(otherDate.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
