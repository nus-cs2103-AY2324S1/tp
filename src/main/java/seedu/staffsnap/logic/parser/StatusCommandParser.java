package seedu.staffsnap.logic.parser;

import static seedu.staffsnap.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.staffsnap.commons.core.index.Index;
import seedu.staffsnap.logic.commands.StatusCommand;
import seedu.staffsnap.logic.parser.exceptions.ParseException;
import seedu.staffsnap.model.applicant.Status;


/**
 * Parses inputs and returns a StatusCommand
 */
public class StatusCommandParser implements Parser<StatusCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public StatusCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args);

        if (argMultimap.getPreamble().length() <= 2) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, StatusCommand.MESSAGE_USAGE));
        }
        String[] splitString = argMultimap.getPreamble().split("\\s+");
        Index index = ParserUtil.parseIndex(splitString[0]);
        Status status = Status.findByName(splitString[1]);
        if (status == null) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, StatusCommand.MESSAGE_NO_STATUS));
        }
        return new StatusCommand(index, status);
    }

}
