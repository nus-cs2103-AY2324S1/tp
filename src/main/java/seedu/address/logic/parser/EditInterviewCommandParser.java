package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_JOB_ROLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_TIME;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditInterviewCommand;
import seedu.address.logic.commands.EditInterviewCommand.EditInterviewDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new EditInterviewCommand object
 * Adapted from AB3s EditCommand Parser
 */
public class EditInterviewCommandParser implements Parser<EditInterviewCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     * @throws seedu.address.logic.parser.exceptions.ParseException if the user input
     *     does not conform the expected format
     */
    public EditInterviewCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_JOB_ROLE, PREFIX_START_TIME, PREFIX_END_TIME);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditInterviewCommand.MESSAGE_USAGE),
                    pe);
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_JOB_ROLE, PREFIX_START_TIME, PREFIX_END_TIME);

        EditInterviewDescriptor editInterviewDescriptor = new EditInterviewDescriptor();

        if (argMultimap.getValue(PREFIX_JOB_ROLE).isPresent()) {
            editInterviewDescriptor.setJobRole(argMultimap.getValue(PREFIX_JOB_ROLE).get());
        }
        if (argMultimap.getValue(PREFIX_START_TIME).isPresent()) {
            editInterviewDescriptor.setStartTime(
                    TimeParser.parseDate(argMultimap.getValue(PREFIX_START_TIME).get()));
        }
        if (argMultimap.getValue(PREFIX_END_TIME).isPresent()) {
            editInterviewDescriptor.setEndTime(
                    TimeParser.parseDate(argMultimap.getValue(PREFIX_END_TIME).get()));
        }

        if (!editInterviewDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditInterviewCommand.MESSAGE_NOT_EDITED);
        }

        return new EditInterviewCommand(index, editInterviewDescriptor);
    }

}
