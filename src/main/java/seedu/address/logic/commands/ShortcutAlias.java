package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a default command keyword.
 * Guarantees: immutable; is valid as declared in {@link #isValidShortcutAlias(String)}
 */
public class ShortcutAlias {

    public static final String MESSAGE_CONSTRAINTS =
            "Shortcuts should only contain alphanumeric characters and should not be blank!\n"
                    + "It should also not match any default command keywords.";

    /*
     * The first character of the shortcut alias must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "^[\\p{Alnum}]+$";

    public final String alias;

    /**
     * Constructs a {@code ShortcutAlias}.
     *
     * @param alias A valid shortcut alias.
     */
    public ShortcutAlias(String alias) {
        requireNonNull(alias);
        checkArgument(isValidShortcutAlias(alias), MESSAGE_CONSTRAINTS);
        this.alias = alias;
    }

    /**
     * Returns true if a given string is a valid shortcut alias.
     */
    public static boolean isValidShortcutAlias(String test) {
        return test.matches(VALIDATION_REGEX) && !CommandWord.isValidCommandWord(test);
    }

    @Override
    public String toString() {
        return alias;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ShortcutAlias)) {
            return false;
        }

        ShortcutAlias otherShortcutAlias = (ShortcutAlias) other;
        return alias.equals(otherShortcutAlias.alias);
    }

    @Override
    public int hashCode() {
        return alias.hashCode();
    }
}
