package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.UnmarkScheduleCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new {@code UnmarkScheduleCommand} object
 */
public class UnmarkScheduleCommandParser implements Parser<UnmarkScheduleCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the {@code UnmarkScheduleCommand}
     * and returns a {@code UnmarkScheduleCommand} object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public UnmarkScheduleCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args);

        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                UnmarkScheduleCommand.MESSAGE_USAGE), ive);
        }

        return new UnmarkScheduleCommand(index);
    }
}
