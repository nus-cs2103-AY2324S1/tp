package seedu.address.logic.commands.exceptions;

import seedu.address.logic.commands.Command;

/**
 * Represents an error which occurs during execution of a {@link Command}.
 */
public class CommandException extends Exception {
    public CommandException(String message) {
        super(message);
    }

    /**
     * Constructs a new {@code CommandException} with the specified detail {@code message} and {@code cause}.
     */
    public CommandException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Represents an error when a duplicate company is added. Duplicate company refers to
     * companies with the same name field and the same role field.
     */
    public static class DuplicateException extends CommandException {
        /**
         * Constructs a new {@code DuplicateException} with the specified detail {@code message}.
         * This is to be used for user interaction for the ADD AND EDIT command.
         */
        public DuplicateException(String message) {
            super(message);
        }
    }
}
