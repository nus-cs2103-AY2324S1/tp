package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.MarkAllCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new MarkAllCommand object
 */
public class MarkAllCommandParser implements Parser<MarkAllCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the MarkAllCommand
     * and returns a MarkAllCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public MarkAllCommand parse(String args) throws ParseException {
        requireNonNull(args);

        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args);

        if (argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MarkAllCommand.MESSAGE_USAGE));
        }

        Index index = ParserUtil.parseIndex(argMultimap.getPreamble());

        return new MarkAllCommand(index);
    }
}
