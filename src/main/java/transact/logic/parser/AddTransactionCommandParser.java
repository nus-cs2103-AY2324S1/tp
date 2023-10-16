package transact.logic.parser;

import static transact.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static transact.logic.parser.CliSyntax.PREFIX_AMOUNT;
import static transact.logic.parser.CliSyntax.PREFIX_DATE;
import static transact.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static transact.logic.parser.CliSyntax.PREFIX_STAFF;
import static transact.logic.parser.CliSyntax.PREFIX_TYPE;

import java.util.stream.Stream;

import transact.logic.commands.AddTransactionCommand;
import transact.logic.parser.exceptions.ParseException;
import transact.model.transaction.Transaction;
import transact.model.transaction.info.Amount;
import transact.model.transaction.info.Description;
import transact.model.transaction.info.TransactionId;

/**
 * Parses input arguments and creates a new AddStaffCommand object
 */
public class AddTransactionCommandParser implements Parser<AddTransactionCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the
     * AddStaffCommand
     * and returns an AddStaffCommand object for execution.
     *
     * @throws ParseException
     *             if the user input does not conform the expected format
     */
    public AddTransactionCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_TYPE, PREFIX_DESCRIPTION,
                PREFIX_AMOUNT, PREFIX_DATE, PREFIX_STAFF);

        if (!arePrefixesPresent(argMultimap, PREFIX_TYPE, PREFIX_DESCRIPTION, PREFIX_AMOUNT, PREFIX_DATE,
                PREFIX_STAFF)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddTransactionCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_TYPE, PREFIX_DESCRIPTION, PREFIX_AMOUNT, PREFIX_DATE);
        /*
         * TODO add this when Transaction class is done.
         * Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_TYPE).get());
         * Phone phone =
         * ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get());
         * Email email =
         * ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get());
         * Address address =
         * ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get());
         * Set<Tag> tagList =
         * ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));
         */

        Transaction transaction = new Transaction(new TransactionId(), new Description("Test Description"),
                new Amount(10));

        return new AddTransactionCommand(transaction);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values
     * in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
