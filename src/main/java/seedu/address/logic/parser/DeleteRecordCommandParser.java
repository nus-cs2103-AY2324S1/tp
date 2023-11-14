package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteRecordCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteRecordCommand object
 */
public class DeleteRecordCommandParser implements Parser<DeleteRecordCommand> {

    @Override
    public DeleteRecordCommand parse(String userInput) throws ParseException {
        requireNonNull(userInput);
        Index patientIndex;
        Index recordIndex;

        try {
            String trimmedUserInput = userInput.trim();
            patientIndex = ParserUtil.parsePatientIndex(trimmedUserInput);
            recordIndex = ParserUtil.parseRecordIndex(trimmedUserInput);
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    DeleteRecordCommand.MESSAGE_USAGE), pe);
        }

        return new DeleteRecordCommand(patientIndex, recordIndex);
    }
}
