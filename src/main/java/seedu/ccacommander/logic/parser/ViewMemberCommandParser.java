package seedu.ccacommander.logic.parser;

import seedu.ccacommander.commons.core.index.Index;
import seedu.ccacommander.logic.commands.ViewMemberCommand;
import seedu.ccacommander.logic.parser.exceptions.ParseException;

import static seedu.ccacommander.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

public class ViewMemberCommandParser implements Parser<ViewMemberCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the ViewEventCommand
     * and returns a ViewEventCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ViewMemberCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new ViewMemberCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewMemberCommand.MESSAGE_USAGE), pe);
        }
    }
}

