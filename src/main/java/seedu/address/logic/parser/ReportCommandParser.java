package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.ReportCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.employee.Id;

/**
 * Parses input arguments and creates a new ReportCommand object
 */
public class ReportCommandParser implements Parser<ReportCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ReportCommand
     * and returns a ReportCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ReportCommand parse(String args) throws ParseException {
        try {
            Id id = ParserUtil.parseId(args);
            return new ReportCommand(id);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ReportCommand.MESSAGE_USAGE), pe);
        }
    }

}
