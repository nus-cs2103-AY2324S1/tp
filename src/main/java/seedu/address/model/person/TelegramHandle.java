package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Telegram handle of a person.
 * A Telegram handle can only consist of lowercase and uppercase letters, digits, and underscores.
 * Usernames are case-insensitive.
 */
public class TelegramHandle {
    public static final String MESSAGE_CONSTRAINTS = "Telegram Handles can only use a-z, 0-9 and underscores. "
            + "Usernames are case-insensitive\n"
            + "Omit the @ in front of the handle";

    public static final String VALIDATION_REGEX = "^[a-zA-Z0-9_]{1,}$";

    public final String value;

    /**
     * Constructs an {@code TelegramHandle}.
     *
     * @param username A valid username.
     */
    public TelegramHandle(String username) {
        requireNonNull(username);
        checkArgument(isValidTelegramHandle(username), MESSAGE_CONSTRAINTS);
        value = username;
    }

    /**
     * Returns true if a given string is a valid email.
     */
    public static boolean isValidTelegramHandle(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof TelegramHandle)) {
            return false;
        }

        TelegramHandle otherTelegramHandle = (TelegramHandle) other;
        return value.equals(otherTelegramHandle.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
