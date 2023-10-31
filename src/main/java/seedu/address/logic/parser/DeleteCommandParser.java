package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APPOINTMENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEDICAL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NRIC;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.ParserUtil.isAnyPrefixPresent;

import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.DeleteCommand.DeletePersonDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Name;
import seedu.address.model.person.Nric;

/**
 * Parses input arguments and creates a new DeleteCommand object
 */
public class DeleteCommandParser implements Parser<DeleteCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the
     * DeleteCommand
     * and returns a DeleteCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteCommand parse(String args) throws ParseException {
        requireNonNull(args);

        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_NRIC,
                PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_APPOINTMENT, PREFIX_MEDICAL, PREFIX_TAG);

        if (isAnyPrefixPresent(argMultimap, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_TAG)
                || !isAnyPrefixPresent(argMultimap, PREFIX_NAME, PREFIX_NRIC, PREFIX_APPOINTMENT, PREFIX_MEDICAL)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME, PREFIX_NRIC, PREFIX_APPOINTMENT, PREFIX_MEDICAL);

        boolean hasNamePrefix = argMultimap.getValue(PREFIX_NAME).isPresent();
        boolean hasNricPrefix = argMultimap.getValue(PREFIX_NRIC).isPresent();

        if (!hasNamePrefix && !hasNricPrefix) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));
        }

        Name name = null;
        Nric nric = null;

        if (hasNamePrefix) {
            name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        }
        if (hasNricPrefix) {
            nric = ParserUtil.parseNric(argMultimap.getValue(PREFIX_NRIC).get());
        }

        DeletePersonDescriptor deletePersonDescriptor = new DeletePersonDescriptor();

        if (argMultimap.prefixExist(PREFIX_MEDICAL)) {
            deletePersonDescriptor.setShouldDeleteMedicalHistory();
        }

        if (argMultimap.prefixExist(PREFIX_APPOINTMENT)) {
            deletePersonDescriptor.setShouldDeleteAppointment();
        }

        return new DeleteCommand(nric, name, deletePersonDescriptor);
    }
}
