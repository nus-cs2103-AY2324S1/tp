package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DOSAGE;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.UntakeCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.prescription.Dosage;

/**
 * Parses input arguments and creates a new UntakeCommand object
 */
public class UntakeCommandParser implements Parser<UntakeCommand> {

    /**
     * Parses the given arguments to create an UntakeCommand.
     *
     * @param args User input representing the command.
     * @return An UntakeCommand for untaking a specified number of doses from a prescription.
     * @throws ParseException If the user input does not conform to the expected format.
     */
    @Override
    public UntakeCommand parse(String args) throws ParseException {
        requireNonNull(args);

        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_DOSAGE);
        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_DOSAGE);

        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    UntakeCommand.MESSAGE_USAGE), pe);
        }

        Dosage dosesToUntake = ParserUtil.parseDosage(argMultimap.getValue(PREFIX_DOSAGE).orElse("1"));
        return new UntakeCommand(index, dosesToUntake);
    }
}
