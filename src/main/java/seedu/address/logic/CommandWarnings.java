package seedu.address.logic;

import java.util.HashSet;
import java.util.Set;

/**
 * Encapsulates a set of warning messages, Serves a purpose similar to exceptions,
 * but without terminating the current method.
 */
public class CommandWarnings {
    private final Set<String> warnings;
    public CommandWarnings() {
        this.warnings = new HashSet<>();
    }

    /**
     * Returns whether the warning string provided is part of the calling CommandWarnings. As we prevent null warnings
     * from being added in the following method, there is an assertion that the CommandWarnings should not contain
     * any null warnings.
     * @param warning a warning that is being searched for.
     */
    public boolean containsWarningString(String warning) {
        assert warning != null || !warnings.contains(null);
        return warnings.contains(warning);
    }
    /**
     * Adds a warning into the CommandWarnings object. If the message is null, nothing happens.
     * @param message the warning to be added.
     */
    public void addWarning(String message) {
        if (message != null) {
            warnings.add(message);
        }
    }
    public boolean containsWarnings() {
        return !(warnings.isEmpty());
    }

    public String getWarningMessage() {
        if (warnings.isEmpty()) {
            return "";
        }
        return "Warning!; " + warnings + "\nPlease ignore if this is expected.";
    }
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CommandWarnings)) {
            return false;
        }

        CommandWarnings otherCommandWarnings = (CommandWarnings) other;
        return warnings.equals(otherCommandWarnings.warnings);
    }
    @Override
    public String toString() {
        return warnings.toString();
    }
}
