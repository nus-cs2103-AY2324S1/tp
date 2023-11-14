package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.FilterGroupCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.group.GroupBelongsTutorialPredicate;

/**
 * Parses input arguments and creates a new FilterGroupCommand object
 */
public class FilterGroupCommandParser implements Parser<FilterGroupCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FilterGroupCommand
     * and returns a FilterGroupCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FilterGroupCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterGroupCommand.MESSAGE_USAGE));
        }

        ParserUtil.parseTutorial(trimmedArgs);

        return new FilterGroupCommand(new GroupBelongsTutorialPredicate(trimmedArgs));
    }
}
