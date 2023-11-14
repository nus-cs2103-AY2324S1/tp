package seedu.address.logic.parser.finance;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.finance.SummaryCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.finance.ClientNameExactMatchPredicate;
/**
 * Parses input arguments and creates a new SummaryCommand object.
 */
public class SummaryCommandParser implements Parser<SummaryCommand> {
    @Override
    public SummaryCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, SummaryCommand.MESSAGE_USAGE));
        }

        return new SummaryCommand(new ClientNameExactMatchPredicate(trimmedArgs));
    }
}
