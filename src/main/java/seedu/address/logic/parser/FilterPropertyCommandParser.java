package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRICE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.address.logic.commands.FilterPropertyCommand;
import seedu.address.logic.commands.FilterPropertyCommand.FilterPropertyDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Filters and lists all properties in address book whose price and/or tags are selected.
 */
public class FilterPropertyCommandParser implements Parser<FilterPropertyCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the FilterPropertyCommand
     * and returns a FilterPropertyCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FilterPropertyCommand parse(String args) throws ParseException {
        requireNonNull(args);
        FilterPropertyDescriptor descriptor = new FilterPropertyDescriptor();
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_PRICE, PREFIX_TAG);

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_PRICE);
        if (argMultimap.getValue(PREFIX_PRICE).isPresent()) {
            descriptor.setPrice(ParserUtil.parsePrice(argMultimap.getValue(PREFIX_PRICE).get()));
        }

        if (argMultimap.getValue(PREFIX_TAG).isPresent()) {
            descriptor.setTags(ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG)));
        }

        if (!descriptor.isAnyFieldFiltered()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    FilterPropertyCommand.MESSAGE_USAGE));
        }

        return new FilterPropertyCommand(descriptor.getPredicate());
    }
}
