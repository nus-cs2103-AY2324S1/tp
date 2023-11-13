package seedu.address.logic.parser.exceptions;

/**
 * Represents an error that occurs when an input command is not properly formatted.
 */
public class InvalidFormatException extends ParseException {
    public InvalidFormatException(String message) {
        super(message);
    }

    public InvalidFormatException(String message, String commandWord, String usageMessage) {
        super(String.format(message, commandWord) + "\nUsage: " + usageMessage);
    }

    public InvalidFormatException(String message, String commandWord, String usageMessage, Throwable cause) {
        super(String.format(message, commandWord) + "\nUsage: " + usageMessage, cause);
    }
}
