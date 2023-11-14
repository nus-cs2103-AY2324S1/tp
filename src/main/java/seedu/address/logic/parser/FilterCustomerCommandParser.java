package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BUDGET;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.address.logic.commands.FilterCustomerCommand;
import seedu.address.logic.commands.FilterCustomerCommand.FilterCustomerDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Filters and lists all customers in address book whose budget and/or tags are selected.
 */
public class FilterCustomerCommandParser implements Parser<FilterCustomerCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FilterCustomerCommand
     * and returns a FilterPropertyCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FilterCustomerCommand parse(String args) throws ParseException {
        requireNonNull(args);
        FilterCustomerDescriptor descriptor = new FilterCustomerDescriptor();
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_BUDGET, PREFIX_TAG);

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_BUDGET);
        if (argMultimap.getValue(PREFIX_BUDGET).isPresent()) {
            descriptor.setBudget(ParserUtil.parseBudget(argMultimap.getValue(PREFIX_BUDGET).get()));
        }

        if (argMultimap.getValue(PREFIX_TAG).isPresent()) {
            descriptor.setTags(ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG)));
        }

        if (!descriptor.isAnyFieldFiltered()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    FilterCustomerCommand.MESSAGE_USAGE));
        }

        return new FilterCustomerCommand(descriptor.getPredicate());
    }

}
