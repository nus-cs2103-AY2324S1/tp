package transact.logic.parser;

import static java.util.Objects.requireNonNull;
import static transact.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static transact.logic.parser.CliSyntax.PREFIX_AMOUNT;
import static transact.logic.parser.CliSyntax.PREFIX_DATE;
import static transact.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static transact.logic.parser.CliSyntax.PREFIX_STAFF;
import static transact.logic.parser.CliSyntax.PREFIX_TYPE;

import transact.logic.commands.EditStaffCommand;
import transact.logic.commands.EditTransactionCommand;
import transact.logic.commands.EditTransactionCommand.EditTransactionDescriptor;
import transact.logic.parser.exceptions.ParseException;
import transact.model.person.PersonId;

/**
 * Parses input arguments and creates a new EditStaffCommand object
 */
public class EditTransactionCommandParser implements Parser<EditTransactionCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the
     * EditStaffCommand
     * and returns an EditStaffCommand object for execution.
     *
     * @throws ParseException
     *             if the user input does not conform the expected format
     */
    public EditTransactionCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_TYPE, PREFIX_DESCRIPTION, PREFIX_AMOUNT,
                PREFIX_DATE, PREFIX_STAFF);

        Integer transactionId;

        try {
            transactionId = Integer.parseInt(argMultimap.getPreamble());
        } catch (NumberFormatException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditTransactionCommand.MESSAGE_USAGE), pe);
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_TYPE, PREFIX_DESCRIPTION, PREFIX_AMOUNT,
                PREFIX_DATE, PREFIX_STAFF);

        EditTransactionDescriptor editTransactionDescriptor = new EditTransactionDescriptor();

        if (argMultimap.getValue(PREFIX_TYPE).isPresent()) {
            editTransactionDescriptor.setType(ParserUtil.parseType(argMultimap.getValue(PREFIX_TYPE).get()));
        }
        if (argMultimap.getValue(PREFIX_DESCRIPTION).isPresent()) {
            editTransactionDescriptor.setDescription(ParserUtil.parseDescription(argMultimap
                    .getValue(PREFIX_DESCRIPTION).get()));
        }
        if (argMultimap.getValue(PREFIX_AMOUNT).isPresent()) {
            editTransactionDescriptor.setAmount(ParserUtil.parseAmount(argMultimap.getValue(PREFIX_AMOUNT).get()));
        }
        if (argMultimap.getValue(PREFIX_DATE).isPresent()) {
            editTransactionDescriptor.setDate(ParserUtil.parseDate(argMultimap.getValue(PREFIX_DATE).get()));
        }
        if (argMultimap.getValue(PREFIX_STAFF).isPresent()) {
            Integer staffId = ParserUtil.parsePersonId(argMultimap.getValue(PREFIX_STAFF).get());
            if (staffId > 0) {
                editTransactionDescriptor.setStaffId(staffId);
            } else {
                throw new ParseException(PersonId.MESSAGE_CONSTRAINTS);
            }
        }

        if (!editTransactionDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditStaffCommand.MESSAGE_NOT_EDITED);
        }

        return new EditTransactionCommand(transactionId, editTransactionDescriptor);
    }
}
