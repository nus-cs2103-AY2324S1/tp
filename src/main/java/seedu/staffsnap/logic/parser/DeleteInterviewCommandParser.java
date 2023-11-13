package seedu.staffsnap.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.staffsnap.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.staffsnap.logic.parser.CliSyntax.PREFIX_INTERVIEW;
import static seedu.staffsnap.logic.parser.ParserUtil.arePrefixesPresent;

import seedu.staffsnap.commons.core.index.Index;
import seedu.staffsnap.logic.commands.DeleteInterviewCommand;
import seedu.staffsnap.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteInterviewCommand object
 */
public class DeleteInterviewCommandParser implements Parser<DeleteInterviewCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteInterviewCommand
     * and returns a DeleteInterviewCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteInterviewCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_INTERVIEW);

        Index applicantIndex;
        Index interviewIndex;

        try {
            applicantIndex = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteInterviewCommand.MESSAGE_USAGE), pe);
        }

        if (!arePrefixesPresent(argMultimap, PREFIX_INTERVIEW)) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteInterviewCommand.MESSAGE_USAGE));
        }

        interviewIndex = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_INTERVIEW).get());
        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_INTERVIEW);

        return new DeleteInterviewCommand(applicantIndex, interviewIndex);
    }


}
