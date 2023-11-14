package seedu.address.logic.parser.finance;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.finance.ListFinancesCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.finance.FinanceListType;

/**
 * Parses input arguments and creates a new ListFinancesCommand object.
 */
public class ListFinanceParser implements Parser<ListFinancesCommand> {
    @Override
    public ListFinancesCommand parse(String args) throws ParseException {
        try {
            FinanceListType type = ParserUtil.parseFinanceListType(args);
            return new ListFinancesCommand(type);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListFinancesCommand.MESSAGE_USAGE), pe);
        }
    }
}
