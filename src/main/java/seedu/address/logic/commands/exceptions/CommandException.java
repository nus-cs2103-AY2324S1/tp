package seedu.address.logic.commands.exceptions;

import seedu.address.logic.commands.Command;

/**
 * Represents an error which occurs during execution of a {@link Command}.
 */
public class CommandException extends Exception {
    /**
     * Constructs a new {@code CommandException} with the specified detail {@code message}.
     *
     * @param message The detail message.
     */
    public CommandException(String message) {
        super(message);
    }

    /**
     * Constructs a new {@code CommandException} with the specified detail {@code message} and {@code cause}.
     *
     * @param message The detail message.
     * @param cause   The cause.
     */
    public CommandException(String message, Throwable cause) {
        super(message, cause);
    }
}
