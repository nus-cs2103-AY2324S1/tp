package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.Messages.MESSAGE_INVALID_TWO_FIELD;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMPANY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DELETE_MONTH;

import seedu.address.logic.commands.BatchDeleteCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.month.DeleteMonth;
import seedu.address.model.policy.Company;

/**
 * Parses input arguments and creates a new DeleteCommand object
 */
public class BatchDeleteCommandParser implements Parser<BatchDeleteCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the BatchDeleteCommand
     * and returns a BatchDeleteCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public BatchDeleteCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_DELETE_MONTH, PREFIX_COMPANY);

        boolean hasDeleteMonth = argMultimap.getValue(PREFIX_DELETE_MONTH).isPresent();
        boolean hasCompany = argMultimap.getValue(PREFIX_COMPANY).isPresent();

        if (hasDeleteMonth && hasCompany) {
            throw new ParseException(String.format(MESSAGE_INVALID_TWO_FIELD, PREFIX_DELETE_MONTH, PREFIX_COMPANY));
        } else if (!hasDeleteMonth && !hasCompany) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, BatchDeleteCommand.MESSAGE_USAGE));
        }

        if (hasDeleteMonth) {
            argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_DELETE_MONTH);
            DeleteMonth deleteMonth = ParserUtil.parseDeleteMonth(argMultimap.getValue(PREFIX_DELETE_MONTH).get());
            assert !deleteMonth.equals(null) : "wrong input for deleteMonth";
            return new BatchDeleteCommand(deleteMonth);
        }

        // else: hasCompany is true, hasDeleteMonth is false
        assert hasCompany : "should has input for company";
        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_COMPANY);
        Company company = ParserUtil.parseCompany(argMultimap.getValue(PREFIX_COMPANY).get());

        return new BatchDeleteCommand(company);
    }
}
