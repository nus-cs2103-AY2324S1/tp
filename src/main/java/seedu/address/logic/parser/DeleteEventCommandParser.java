package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.ParserUtil.DATE_TIME_STRING_FORMATTER;

import java.time.LocalDateTime;

import seedu.address.logic.commands.DeleteEventCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteEventCommand object.
 */
public class DeleteEventCommandParser implements Parser<DeleteEventCommand> {
    @Override
    public DeleteEventCommand parse(String userInput) throws ParseException {
        try {
            String eventString = userInput.trim();
            LocalDateTime eventTime = LocalDateTime.parse(eventString, DATE_TIME_STRING_FORMATTER);
            return new DeleteEventCommand(eventTime);
        } catch (Exception pe) {
            throw new ParseException(String.format(
                    MESSAGE_INVALID_COMMAND_FORMAT, DeleteEventCommand.MESSAGE_USAGE));
        }
    }
}
