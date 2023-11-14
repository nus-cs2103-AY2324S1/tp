package seedu.application.logic.parser;

import static seedu.application.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.application.logic.parser.CliSyntax.PREFIX_INTERVIEW_ADDRESS;
import static seedu.application.logic.parser.CliSyntax.PREFIX_INTERVIEW_DATETIME;
import static seedu.application.logic.parser.CliSyntax.PREFIX_INTERVIEW_TYPE;

import java.util.stream.Stream;

import seedu.application.commons.core.index.Index;
import seedu.application.logic.commands.InterviewAddCommand;
import seedu.application.logic.parser.exceptions.ParseException;
import seedu.application.model.job.interview.Interview;
import seedu.application.model.job.interview.InterviewAddress;
import seedu.application.model.job.interview.InterviewDateTime;
import seedu.application.model.job.interview.InterviewType;

/**
 * Parses input arguments and creates a new InterviewAddCommand object
 */
public class InterviewAddCommandParser implements Parser<InterviewAddCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the InterviewAddCommand
     * and returns an InterviewAddCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public InterviewAddCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_INTERVIEW_TYPE,
                        PREFIX_INTERVIEW_DATETIME, PREFIX_INTERVIEW_ADDRESS);

        if (!arePrefixesPresent(argMultimap, PREFIX_INTERVIEW_TYPE,
                PREFIX_INTERVIEW_DATETIME, PREFIX_INTERVIEW_ADDRESS)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, InterviewAddCommand.MESSAGE_USAGE));
        }
        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    InterviewAddCommand.MESSAGE_USAGE), pe);
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_INTERVIEW_TYPE,
                PREFIX_INTERVIEW_DATETIME, PREFIX_INTERVIEW_ADDRESS);

        InterviewType interviewType =
                ParserUtil.parseInterviewType(argMultimap.getValue(PREFIX_INTERVIEW_TYPE).get());
        InterviewDateTime interviewDateTime =
                ParserUtil.parseInterviewDateTime(argMultimap.getValue(PREFIX_INTERVIEW_DATETIME).get());
        InterviewAddress interviewAddress =
                ParserUtil.parseInterviewAddress(argMultimap.getValue(PREFIX_INTERVIEW_ADDRESS).get());

        Interview interview = new Interview(interviewType, interviewDateTime, interviewAddress);

        return new InterviewAddCommand(index, interview);
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
