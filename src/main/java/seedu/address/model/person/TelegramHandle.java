package seedu.address.model.person;

/**
 * Represents a Person's telegram handle in the address book.
 */
public class TelegramHandle {

    public static final String MESSAGE_CONSTRAINTS = "Telegram handles should only contain alphanumeric characters "
            + "and spaces, and it should not be blank";
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String value;

    /**
     * Constructs a {@code TelegramHandle}.
     *
     * @param telegramHandle A valid telegram handle.
     */
    public TelegramHandle(String telegramHandle) {
        value = telegramHandle;
    }

    /**
     * Returns true if a given string is a valid telegram handle.
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
        return other == this // short circuit if same object
                || (other instanceof TelegramHandle // instanceof handles nulls
                && value.equals(((TelegramHandle) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
