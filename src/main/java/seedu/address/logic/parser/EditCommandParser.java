package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMPANY_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRIORITY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RECRUITER_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STATUS;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new EditCommand object.
 */
public class EditCommandParser implements Parser<EditCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand.
     * and returns an EditCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format.
     */
    public EditCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_COMPANY_NAME, PREFIX_RECRUITER_NAME, PREFIX_ROLE, PREFIX_STATUS,
                        PREFIX_DEADLINE, PREFIX_EMAIL, PREFIX_PHONE, PREFIX_PRIORITY, PREFIX_REMARK);

        Index index;

        // Checks for invalid prefixes parsed as the preamble
        if (!argMultimap.isValidPreamble()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE));
        }

        // Checks for invalid prefixes parsed as fields
        if (!argMultimap.verifyNoInvalidPrefixesFor(PREFIX_COMPANY_NAME, PREFIX_RECRUITER_NAME, PREFIX_ROLE,
                PREFIX_STATUS, PREFIX_DEADLINE, PREFIX_EMAIL, PREFIX_PHONE, PREFIX_PRIORITY, PREFIX_REMARK)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE));
        }

        //Checks for valid index
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(pe.getMessage(), pe);
        }

        // Checks for duplicate prefix
        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_COMPANY_NAME, PREFIX_RECRUITER_NAME, PREFIX_ROLE, PREFIX_STATUS,
                PREFIX_DEADLINE, PREFIX_EMAIL, PREFIX_PHONE, PREFIX_PRIORITY, PREFIX_REMARK);

        EditCommand.EditCompanyDescriptor editCompanyDescriptor = new EditCommand.EditCompanyDescriptor();

        if (argMultimap.getValue(PREFIX_COMPANY_NAME).isPresent()) {
            editCompanyDescriptor.setName(ParserUtil.parseName(argMultimap.getValue(PREFIX_COMPANY_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            editCompanyDescriptor.setPhone(ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get()));
        }
        if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            editCompanyDescriptor.setEmail(ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get()));
        }
        if (argMultimap.getValue(PREFIX_ROLE).isPresent()) {
            editCompanyDescriptor.setRole(ParserUtil.parseRole(argMultimap.getValue(PREFIX_ROLE).get()));
        }
        if (argMultimap.getValue(PREFIX_DEADLINE).isPresent()) {
            editCompanyDescriptor.setDeadline(ParserUtil.parseDeadline(argMultimap.getValue(PREFIX_DEADLINE).get()));
        }
        if (argMultimap.getValue(PREFIX_STATUS).isPresent()) {
            editCompanyDescriptor.setStatus(ParserUtil.parseStatus(argMultimap.getValue(PREFIX_STATUS).get()));
        }
        if (argMultimap.getValue(PREFIX_RECRUITER_NAME).isPresent()) {
            editCompanyDescriptor.setRecruiterName(
                    ParserUtil.parseRecruiterName(argMultimap.getValue(PREFIX_RECRUITER_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_PRIORITY).isPresent()) {
            editCompanyDescriptor.setPriority(ParserUtil.parsePriority(argMultimap.getValue(PREFIX_PRIORITY).get()));
        }

        if (argMultimap.getValue(PREFIX_REMARK).isPresent()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE));
        }

        if (!editCompanyDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
        }

        return new EditCommand(index, editCompanyDescriptor);
    }
}
