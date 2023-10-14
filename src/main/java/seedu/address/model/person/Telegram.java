package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;


/**
 * Represents a Member's telegram handle in the address book.
 */
public class Telegram {

    public static final String TELE_REGEX = "^@[a-zA-Z0-9_]{5,32}$";
    public static final String MESSAGE_CONSTRAINTS = "Telegram handle should follow the format: @exampleHandle ";

    public final String value;

    /**
     * Constructs an {@code Telegram}.
     *
     * @param telegramHandle A valid Telegram Handle.
     */
    public Telegram(String telegramHandle) {
        requireNonNull(telegramHandle);
        checkArgument(isValidHandle(telegramHandle), MESSAGE_CONSTRAINTS);
        value = telegramHandle;
    }

    /**
     * Returns if a given string is a valid telegram handle.
     */
    public static boolean isValidHandle(String test) {
        return test.matches(TELE_REGEX);
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

        if (!(other instanceof Telegram)) {
            return false;
        }

        Telegram otherTele = (Telegram) other;
        return value.equals(otherTele.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
