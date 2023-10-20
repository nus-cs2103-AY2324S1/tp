package seedu.staffsnap.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.staffsnap.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.staffsnap.logic.parser.CliSyntax.PREFIX_INTERVIEW;
import static seedu.staffsnap.logic.parser.CliSyntax.PREFIX_TYPE;

import seedu.staffsnap.commons.core.index.Index;
import seedu.staffsnap.logic.commands.EditInterviewCommand;
import seedu.staffsnap.logic.commands.EditInterviewCommand.EditInterviewDescriptor;
import seedu.staffsnap.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new EditInterviewCommand object.
 */
public class EditInterviewCommandParser implements Parser<EditInterviewCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditInterviewCommand
     * and returns an EditInterviewCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditInterviewCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_INTERVIEW, PREFIX_TYPE);

        Index applicantIndex;
        Index interviewIndex;

        try {
            applicantIndex = ParserUtil.parseIndex(argMultimap.getPreamble());
            interviewIndex = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_INTERVIEW).get());
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditInterviewCommand.MESSAGE_USAGE), pe);
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_INTERVIEW, PREFIX_TYPE);

        EditInterviewDescriptor editInterviewDescriptor = new EditInterviewDescriptor();
        if (argMultimap.getValue(PREFIX_TYPE).isPresent()) {
            editInterviewDescriptor.setType(ParserUtil.parseType(argMultimap.getValue(PREFIX_TYPE).get()));
        }
        if (!editInterviewDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditInterviewCommand.MESSAGE_NOT_EDITED);
        }

        return new EditInterviewCommand(applicantIndex, interviewIndex, editInterviewDescriptor);
    }
}
