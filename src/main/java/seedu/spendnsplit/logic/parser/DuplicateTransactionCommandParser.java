package seedu.spendnsplit.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.spendnsplit.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.spendnsplit.logic.parser.CliSyntax.PREFIX_COST;
import static seedu.spendnsplit.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.spendnsplit.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.spendnsplit.logic.parser.CliSyntax.PREFIX_TIMESTAMP;

import seedu.spendnsplit.commons.core.index.Index;
import seedu.spendnsplit.logic.commands.DuplicateTransactionCommand;
import seedu.spendnsplit.logic.descriptors.TransactionDescriptor;
import seedu.spendnsplit.logic.parser.exceptions.ParseException;
import seedu.spendnsplit.model.transaction.Timestamp;

/**
 * Parses input arguments and creates a new DuplicateCommand object.
 */
public class DuplicateTransactionCommandParser implements Parser<DuplicateTransactionCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DuplicateCommand
     * and returns a DuplicateCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public DuplicateTransactionCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
            ArgumentTokenizer.tokenize(args, PREFIX_COST, PREFIX_DESCRIPTION, PREFIX_NAME, PREFIX_TIMESTAMP);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(
                MESSAGE_INVALID_COMMAND_FORMAT, DuplicateTransactionCommand.MESSAGE_USAGE), pe);
        }

        TransactionDescriptor duplicateTransactionDescriptor =
            EditTransactionCommandParser.getTransactionDescriptor(argMultimap);

        // Set timestamp to current time if not specified
        if (duplicateTransactionDescriptor.getTimestamp().isEmpty()) {
            duplicateTransactionDescriptor.setTimestamp(Timestamp.now());
        }

        return new DuplicateTransactionCommand(index, duplicateTransactionDescriptor);
    }

}
