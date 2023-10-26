package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.LinkedInCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new GithubCommand object.
 */
public class LinkedInCommandParser implements Parser<LinkedInCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the LinkedInCommand
     * and returns a LinkedInCommand object.
     * @throws ParseException if the user input does not conform the expected format.
     */
    public LinkedInCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new LinkedInCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, LinkedInCommand.MESSAGE_USAGE), pe);
        }
    }
}
