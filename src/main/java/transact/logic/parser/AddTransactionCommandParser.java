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
import transact.model.person.PersonId;
import transact.model.transaction.info.Amount;
import transact.model.transaction.info.Date;
import transact.model.transaction.info.Description;
import transact.model.transaction.info.TransactionType;

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

        if (!arePrefixesPresent(argMultimap, PREFIX_TYPE, PREFIX_DESCRIPTION, PREFIX_AMOUNT, PREFIX_DATE)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddTransactionCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_TYPE, PREFIX_DESCRIPTION, PREFIX_AMOUNT, PREFIX_DATE);

        TransactionType transactionType = ParserUtil.parseType(argMultimap.getValue(PREFIX_TYPE).get());
        Description description = ParserUtil.parseDescription(argMultimap.getValue(PREFIX_DESCRIPTION).get());
        Amount amount = ParserUtil.parseAmount(argMultimap.getValue(PREFIX_AMOUNT).get());
        Date date = ParserUtil.parseDate(argMultimap.getValue(PREFIX_DATE).get());

        Integer personId;
        if (argMultimap.getValue(PREFIX_STAFF).isPresent()) {
            personId = ParserUtil.parsePersonId(argMultimap.getValue(PREFIX_STAFF).get());
            if (personId < 0) {
                throw new ParseException(PersonId.MESSAGE_CONSTRAINTS);
            } else {
                return new AddTransactionCommand(transactionType, description, amount, date, personId);
            }
        }
        return new AddTransactionCommand(transactionType, description, amount, date, -1);
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
