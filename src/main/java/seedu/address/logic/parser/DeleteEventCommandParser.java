package seedu.address.logic.parser;

import seedu.address.logic.commands.DeleteEventCommand;
import seedu.address.logic.parser.exceptions.ParseException;

import java.time.LocalDateTime;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.model.event.EventPeriod.DATE_TIME_STRING_FORMATTER;

public class DeleteEventCommandParser implements  Parser<DeleteEventCommand> {
    @Override
    public DeleteEventCommand parse(String userInput) throws ParseException {
        try {
            String startString = userInput.trim();
            LocalDateTime startTime = LocalDateTime.parse(startString, DATE_TIME_STRING_FORMATTER);
            return new DeleteEventCommand(startTime);
        } catch (Exception pe) {
            throw new ParseException(String.format(
                    MESSAGE_INVALID_COMMAND_FORMAT, DeleteEventCommand.MESSAGE_USAGE), pe);
        }
    }
}
