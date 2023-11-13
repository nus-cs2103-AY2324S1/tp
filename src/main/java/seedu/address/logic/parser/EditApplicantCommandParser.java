package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INTERVIEW;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditApplicantCommand;
import seedu.address.logic.commands.EditApplicantCommand.EditApplicantDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new EditApplicantCommand object
 */
public class EditApplicantCommandParser implements Parser<EditApplicantCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditApplicantCommand
     * and returns an EditApplicantCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditApplicantCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_INTERVIEW);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    EditApplicantCommand.MESSAGE_USAGE), pe);
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME, PREFIX_PHONE, PREFIX_INTERVIEW);

        EditApplicantDescriptor editApplicantDescriptor = new EditApplicantDescriptor();

        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            editApplicantDescriptor.setName(ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            editApplicantDescriptor.setPhone(ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get()));
        }

        if (argMultimap.getValue(PREFIX_INTERVIEW).isPresent()) {
            editApplicantDescriptor.setInterviewTime(ParserUtil
                    .parseInterviewTime(argMultimap.getValue(PREFIX_INTERVIEW).get()));
        }

        if (!editApplicantDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditApplicantCommand.MESSAGE_NOT_EDITED);
        }

        return new EditApplicantCommand(index, editApplicantDescriptor);
    }
}
