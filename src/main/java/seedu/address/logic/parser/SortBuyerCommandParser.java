package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_HOUSE_INFO;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRIORITY;

import seedu.address.logic.CommandWarnings;
import seedu.address.logic.commands.SortBuyerCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.displayable.SortOrder;
import seedu.address.model.displayable.buyer.BuyerComparator;

/**
 * Parses input arguments and creates a new SortBuyerCommand object
 */
public class SortBuyerCommandParser implements Parser<SortBuyerCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the SortBuyerCommand
     * and returns a SortBuyerCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public SortBuyerCommand parse(String args, CommandWarnings commandWarnings) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_ADDRESS, PREFIX_HOUSE_INFO, PREFIX_PRIORITY);

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME, PREFIX_ADDRESS, PREFIX_HOUSE_INFO, PREFIX_PRIORITY);

        try {
            if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
                SortOrder sortOrder =
                        ParserUtil.parseSortOrder(commandWarnings, argMultimap.getValue(PREFIX_NAME).get());
                return new SortBuyerCommand(BuyerComparator.of(PREFIX_NAME, sortOrder));
            } else if (argMultimap.getValue(PREFIX_ADDRESS).isPresent()) {
                SortOrder sortOrder =
                        ParserUtil.parseSortOrder(commandWarnings, argMultimap.getValue(PREFIX_ADDRESS).get());
                return new SortBuyerCommand(BuyerComparator.of(PREFIX_ADDRESS, sortOrder));
            } else if (argMultimap.getValue(PREFIX_HOUSE_INFO).isPresent()) {
                SortOrder sortOrder =
                        ParserUtil.parseSortOrder(commandWarnings, argMultimap.getValue(PREFIX_HOUSE_INFO).get());
                return new SortBuyerCommand(BuyerComparator.of(PREFIX_HOUSE_INFO, sortOrder));
            } else if (argMultimap.getValue(PREFIX_PRIORITY).isPresent()) {
                SortOrder sortOrder =
                        ParserUtil.parseSortOrder(commandWarnings, argMultimap.getValue(PREFIX_PRIORITY).get());
                return new SortBuyerCommand(BuyerComparator.of(PREFIX_PRIORITY, sortOrder));
            } else {
                return new SortBuyerCommand(BuyerComparator.of());
            }
        } catch (Exception e) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortBuyerCommand.MESSAGE_USAGE));
        }
    }
}
