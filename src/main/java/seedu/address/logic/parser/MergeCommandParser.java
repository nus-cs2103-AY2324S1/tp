package seedu.address.logic.parser;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.MergeCommand;
import seedu.address.logic.parser.exceptions.ParseException;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

/**
 * Parses input arguments and creates a new MergeCommand object
 */
public class MergeCommandParser implements Parser<MergeCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the MergeCommand
     * and returns a MergeCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */

    public MergeCommand parse(String args) throws ParseException {

        String trimmedArgs = args.trim();
        try {

            String[] str = trimmedArgs.split(" ", 2);
            if (str.length != 2) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, MergeCommand.MESSAGE_USAGE));
            }

            Index primaryIndex = ParserUtil.parseIndex(str[0]);
            Index secondaryIndex = ParserUtil.parseIndex(str[1]);

            return new MergeCommand(primaryIndex, secondaryIndex);

        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, MergeCommand.MESSAGE_USAGE), pe);
        }
    }
}
