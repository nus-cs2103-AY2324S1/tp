package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.PayslipCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new PayslipCommand object
 */
public class PayslipCommandParser implements Parser<PayslipCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the PayslipCommand
     * and returns an PayslipCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public PayslipCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args);
        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, PayslipCommand.MESSAGE_USAGE), pe);
        }

        return new PayslipCommand(index);
    }
}
