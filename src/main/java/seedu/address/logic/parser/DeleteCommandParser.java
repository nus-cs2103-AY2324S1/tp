package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APPOINTMENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEDICAL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;

import java.util.HashSet;
import java.util.Set;

import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.DeleteCommand.DeletePersonDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Id;
import seedu.address.model.person.MedicalHistory;
import seedu.address.model.person.Name;

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

        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_ID,
                PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_APPOINTMENT, PREFIX_MEDICAL);

        if (ArgumentMultimap.isAnyPrefixPresent(argMultimap, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS)
                || !ArgumentMultimap.isAnyPrefixPresent(argMultimap, PREFIX_NAME, PREFIX_ID, PREFIX_APPOINTMENT,
                        PREFIX_MEDICAL)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME, PREFIX_ID, PREFIX_APPOINTMENT);

        boolean hasNamePrefix = argMultimap.getValue(PREFIX_NAME).isPresent();
        boolean hasIdPrefix = argMultimap.getValue(PREFIX_ID).isPresent();

        if (!hasNamePrefix && !hasIdPrefix) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));
        }

        Name name = null;
        Id id = null;

        if (hasNamePrefix) {
            name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        }
        if (hasIdPrefix) {
            id = ParserUtil.parseId(argMultimap.getValue(PREFIX_ID).get());
        }

        DeletePersonDescriptor deletePersonDescriptor = new DeletePersonDescriptor();

        if (argMultimap.prefixExist(PREFIX_MEDICAL)) {
            deletePersonDescriptor.setDeleteMedicalHistory();
            if (argMultimap.getValue(PREFIX_MEDICAL).get().equals("")) {
                deletePersonDescriptor.setMedicalHistory(new HashSet<>());
            } else {
                Set<MedicalHistory> medicalHistories = ParserUtil.parseMedicals(
                        argMultimap.getAllValues(PREFIX_MEDICAL));
                deletePersonDescriptor.setMedicalHistory(medicalHistories);
            }
        }

        if (argMultimap.prefixExist(PREFIX_APPOINTMENT)) {
            deletePersonDescriptor.setDeleteAppointment();
        }

        return new DeleteCommand(id, name, deletePersonDescriptor);
    }
}
