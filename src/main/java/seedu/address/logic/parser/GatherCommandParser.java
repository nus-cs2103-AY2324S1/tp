package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.GatherCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new GatherCommand object
 */
public class GatherCommandParser implements Parser<GatherCommand> {
    public static final String VALIDATION_REGEX = "^[a-zA-Z0-9\\s]+";
    public static final String MESSAGE_CONSTRAINTS = "PROMPT should be alphanumeric or space characters";

    /**
     * Parses the given {@code String} of arguments in the context of the GatherCommand
     * and returns a GatherCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public GatherCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, GatherCommand.MESSAGE_USAGE));
        }

        if (!trimmedArgs.matches(VALIDATION_REGEX)) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_CONSTRAINTS));
        }

        return new GatherCommand(trimmedArgs);
    }
}
