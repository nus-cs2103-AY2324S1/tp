package seedu.application.logic.parser;

import static seedu.application.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.application.logic.parser.CliSyntax.PREFIX_INTERVIEW_ADDRESS;
import static seedu.application.logic.parser.CliSyntax.PREFIX_INTERVIEW_DATETIME;
import static seedu.application.logic.parser.CliSyntax.PREFIX_INTERVIEW_TYPE;
import static seedu.application.logic.parser.CliSyntax.PREFIX_JOB_SOURCE;

import java.util.stream.Stream;

import seedu.application.commons.core.index.Index;
import seedu.application.logic.commands.InterviewEditCommand;
import seedu.application.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new InterviewEditCommand object
 */
public class InterviewEditCommandParser implements Parser<InterviewEditCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the InterviewEditCommand
     * and returns an InterviewEditCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public InterviewEditCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_JOB_SOURCE, PREFIX_INTERVIEW_TYPE,
                        PREFIX_INTERVIEW_DATETIME, PREFIX_INTERVIEW_ADDRESS);

        Index jobIndex;
        Index interviewIndex;

        if (!arePrefixesPresent(argMultimap, PREFIX_JOB_SOURCE)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    InterviewEditCommand.MESSAGE_USAGE));
        }

        try {
            interviewIndex = ParserUtil.parseIndex(argMultimap.getPreamble());
            jobIndex = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_JOB_SOURCE).get());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    InterviewEditCommand.MESSAGE_USAGE), pe);
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_INTERVIEW_TYPE,
                PREFIX_INTERVIEW_DATETIME, PREFIX_INTERVIEW_ADDRESS);

        InterviewEditCommand.EditInterviewDescriptor editInterviewDescriptor =
                new InterviewEditCommand.EditInterviewDescriptor();

        if (argMultimap.getValue(PREFIX_INTERVIEW_TYPE).isPresent()) {
            editInterviewDescriptor.setType(ParserUtil.parseInterviewType(argMultimap
                    .getValue(PREFIX_INTERVIEW_TYPE).get()));
        }
        if (argMultimap.getValue(PREFIX_INTERVIEW_DATETIME).isPresent()) {
            editInterviewDescriptor.setDateTime(ParserUtil.parseInterviewDateTime(argMultimap
                    .getValue(PREFIX_INTERVIEW_DATETIME).get()));
        }
        if (argMultimap.getValue(PREFIX_INTERVIEW_ADDRESS).isPresent()) {
            editInterviewDescriptor.setAddress(ParserUtil.parseInterviewAddress(argMultimap
                    .getValue(PREFIX_INTERVIEW_ADDRESS).get()));
        }
        if (!editInterviewDescriptor.isAnyFieldEdited()) {
            throw new ParseException(InterviewEditCommand.MESSAGE_NOT_EDITED);
        }
        return new InterviewEditCommand(jobIndex, interviewIndex, editInterviewDescriptor);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap,
                                              Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
