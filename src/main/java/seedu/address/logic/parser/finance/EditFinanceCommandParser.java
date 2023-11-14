package seedu.address.logic.parser.finance;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_AMOUNT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CLIENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME_DUE;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.finance.EditFinanceCommand;
import seedu.address.logic.commands.finance.EditFinanceCommand.EditFinanceDescriptor;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new EditFinanceCommand object.
 */
public class EditFinanceCommandParser implements Parser<EditFinanceCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the EditEventCommand
     * and returns an EditEventCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public EditFinanceCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_AMOUNT, PREFIX_CLIENT, PREFIX_DESCRIPTION, PREFIX_TIME_DUE);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    EditFinanceCommand.MESSAGE_USAGE), pe);
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_AMOUNT, PREFIX_CLIENT, PREFIX_DESCRIPTION, PREFIX_TIME_DUE);

        EditFinanceDescriptor editFinanceDescriptor = new EditFinanceDescriptor();

        if (argMultimap.getValue(PREFIX_AMOUNT).isPresent()) {
            editFinanceDescriptor.setAmount(ParserUtil.parseAmount(argMultimap.getValue(PREFIX_AMOUNT).get()));
        }
        if (argMultimap.getValue(PREFIX_CLIENT).isPresent()) {
            editFinanceDescriptor.setClient(ParserUtil.parseClientForEdit(argMultimap.getValue(PREFIX_CLIENT).get()));
        }
        if (argMultimap.getValue(PREFIX_DESCRIPTION).isPresent()) {
            editFinanceDescriptor.setDescription(ParserUtil.parseDescription(argMultimap.getValue(PREFIX_DESCRIPTION)
                    .get()));
        }
        if (argMultimap.getValue(PREFIX_TIME_DUE).isPresent()) {
            editFinanceDescriptor.setTimeDue(ParserUtil.parseTimeDue(argMultimap.getValue(PREFIX_TIME_DUE).get()));
        }

        if (!editFinanceDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditFinanceCommand.MESSAGE_NOT_EDITED);
        }

        return new EditFinanceCommand(index, editFinanceDescriptor);
    }
}
