package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DOSAGE;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.TakeCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.prescription.Dosage;

/**
 * Parses input arguments and creates a new TakeCommand object
 */
public class TakeCommandParser implements Parser<TakeCommand> {

    /**
     * Parses the given arguments to create a TakeCommand.
     *
     * @param args User input representing the command.
     * @return A TakeCommand for taking a specified number of doses from a prescription.
     * @throws ParseException If the user input does not conform to the expected format.
     */
    @Override
    public TakeCommand parse(String args) throws ParseException {
        requireNonNull(args);

        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_DOSAGE);
        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_DOSAGE);

        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    TakeCommand.MESSAGE_USAGE), pe);
        }

        Dosage dosesToTake = ParserUtil.parseDosage(argMultimap.getValue(PREFIX_DOSAGE).orElse("1"));
        return new TakeCommand(index, dosesToTake);
    }
}
