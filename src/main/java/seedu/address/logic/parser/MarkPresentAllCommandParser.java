package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.MarkPresentAllCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new MarkPresentAllCommand object
 */
public class MarkPresentAllCommandParser implements Parser<MarkPresentAllCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the MarkPresentAllCommand
     * and returns a MarkPresentAllCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public MarkPresentAllCommand parse(String args) throws ParseException {
        requireNonNull(args);

        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args);

        if (argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    MarkPresentAllCommand.MESSAGE_USAGE));
        }

        Index index = ParserUtil.parseIndex(argMultimap.getPreamble());

        return new MarkPresentAllCommand(index);
    }
}
