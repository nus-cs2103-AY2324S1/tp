package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CommonFreetimeCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new CommonFreetimeCommand object.
 */
public class CommonFreeTimeCommandParser implements Parser<CommonFreetimeCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the CommonFreetimeCommand
     * and returns a CommonFreetimeCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public CommonFreetimeCommand parse(String userInput) throws ParseException {
        requireNonNull(userInput);

        ArgumentMultimap argMultimap =
            ArgumentTokenizer.tokenize(userInput, PREFIX_NAME);

        Index index;
        // Check if preamble is present
        try {
            if (userInput.equalsIgnoreCase("")) {
                return new CommonFreetimeCommand();
            } else {
                index = ParserUtil.parseIndex(argMultimap.getPreamble());
            }
        } catch (Exception e) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    CommonFreetimeCommand.MESSAGE_USAGE));
        }

        return new CommonFreetimeCommand(index);
    }
}
