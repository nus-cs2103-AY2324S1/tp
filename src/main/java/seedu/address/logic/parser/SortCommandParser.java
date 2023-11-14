package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APPLICATION_STATUS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMPANY_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DURATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REQUIREMENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_DATE;

import seedu.address.logic.commands.SortCommand;
import seedu.address.logic.commands.SortCommand.Order;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new SortCommand object
 */
public class SortCommandParser implements InternshipParser<SortCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the SortCommand
     * and returns an SortCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public SortCommand parse(String args) throws ParseException {
        requireNonNull(args);

        if (args.trim().equals(SortCommand.DEFAULT_KEYWORD)) {
            return new SortCommand();
        }

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_COMPANY_NAME, PREFIX_ROLE, PREFIX_APPLICATION_STATUS,
                        PREFIX_START_DATE, PREFIX_DURATION, PREFIX_REQUIREMENT, PREFIX_DEADLINE);


        // Ensure only one prefix is used, we check for 2 because of the empty string preamble.
        if (argMultimap.getLength() != 2) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
        }
        Prefix categoryPrefix = argMultimap.getSinglePrefix();
        String sortOrderStr = argMultimap.getValue(categoryPrefix).get().toUpperCase();

        // Convert sortOrderStr to the corresponding Order enum.
        Order sortOrder;

        try {
            sortOrder = Order.valueOf(sortOrderStr);
        } catch (IllegalArgumentException e) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_COMPANY_NAME, PREFIX_ROLE, PREFIX_APPLICATION_STATUS,
                PREFIX_START_DATE, PREFIX_DURATION, PREFIX_DEADLINE);


        return new SortCommand(categoryPrefix, sortOrder);
    }

}



