package seedu.cc.logic.parser.medicalhistory;

import static seedu.cc.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.cc.commons.core.index.Index;
import seedu.cc.logic.commands.medhisteventcommands.ListMedicalHistoryEventCommand;
import seedu.cc.logic.parser.Parser;
import seedu.cc.logic.parser.ParserUtil;
import seedu.cc.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new ListMedicalHistoryEventCommand object.
 */
public class ListMedicalHistoryEventCommandParser implements Parser<ListMedicalHistoryEventCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ListMedicalHistoryEventCommand
     * and returns a ListMedicalHistoryEventCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public ListMedicalHistoryEventCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new ListMedicalHistoryEventCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                            ListMedicalHistoryEventCommand.MESSAGE_USAGE), pe);
        }
    }
}
