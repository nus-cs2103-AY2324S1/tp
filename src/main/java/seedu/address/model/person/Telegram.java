package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's telegram in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidTelegram(String)}
 */
public class Telegram {

    /**
     * Message constraints for telegram which is the format used for user input.
     */
    public static final String MESSAGE_CONSTRAINTS = "Telegram should start with the '@' symbol, "
            + "should not contain whitespace, should not be blank, with a minimum length of 5 characters.\nUse "
            + "a-z, 0-9 and underscores.";

    /**
     * Regex for Telegram when represented as a telegram handle
     */
    public static final String VALIDATION_REGEX = "@[a-z0-9_]{5,}$";

    public final String value;

    /**
     * Constructs an {@code Telegram}.
     *
     * @param telegram A valid telegram.
     */
    public Telegram(String telegram) {
        requireNonNull(telegram);
        checkArgument(isValidTelegram(telegram), MESSAGE_CONSTRAINTS);
        value = telegram;
    }

    /**
     * Returns true if a given string is a valid telegram handle.
     */
    public static boolean isValidTelegram(String test) {
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
        if (!(other instanceof Telegram)) {
            return false;
        }

        Telegram otherTelegram = (Telegram) other;
        return value.equals(otherTelegram.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
