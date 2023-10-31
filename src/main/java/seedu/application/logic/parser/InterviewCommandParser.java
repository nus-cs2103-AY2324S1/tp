package seedu.application.logic.parser;

import static seedu.application.logic.Messages.*;
import static seedu.application.logic.parser.CliSyntax.*;

import java.util.stream.Stream;

import seedu.application.commons.core.index.Index;
import seedu.application.commons.exceptions.IllegalValueException;
import seedu.application.logic.commands.DeleteCommand;
import seedu.application.logic.commands.InterviewAddCommand;
import seedu.application.logic.commands.InterviewCommand;
import seedu.application.logic.parser.exceptions.ParseException;
import seedu.application.model.job.interview.Interview;
import seedu.application.model.job.interview.InterviewAddress;
import seedu.application.model.job.interview.InterviewDateTime;
import seedu.application.model.job.interview.InterviewType;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class InterviewCommandParser implements Parser<InterviewCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public InterviewCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
            ArgumentTokenizer.tokenize(args, PREFIX_INTERVIEW_TYPE, PREFIX_INTERVIEW_DATETIME, PREFIX_INTERVIEW_ADDRESS);

        Index index;
        String subCommandWord;
        String[] splitPreamble;

        try {
            splitPreamble = ParserUtil.parseInterviewPreamble(argMultimap.getPreamble());
            subCommandWord = ParserUtil.parseSubCommandWord(splitPreamble[0]);
            index = ParserUtil.parseIndex(splitPreamble[1]);
        } catch (ParseException pe) {
            throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, InterviewCommand.MESSAGE_USAGE), pe);
        }

        switch (subCommandWord) {
        case InterviewAddCommand.COMMAND_WORD:
            return parseAdd(index, argMultimap);

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

    private static InterviewAddCommand parseAdd(Index index, ArgumentMultimap argMultimap) throws ParseException {
        if (!arePrefixesPresent(argMultimap, PREFIX_INTERVIEW_TYPE, PREFIX_INTERVIEW_DATETIME, PREFIX_INTERVIEW_ADDRESS)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, InterviewAddCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_INTERVIEW_TYPE, PREFIX_INTERVIEW_DATETIME, PREFIX_INTERVIEW_ADDRESS);

        InterviewType interviewType = ParserUtil.parseInterviewType(argMultimap.getValue(PREFIX_INTERVIEW_TYPE).get());
        InterviewDateTime interviewDateTime = ParserUtil.parseInterviewDateTime(argMultimap.getValue(PREFIX_INTERVIEW_DATETIME).get());
        InterviewAddress interviewAddress = ParserUtil.parseInterviewAddress(argMultimap.getValue(PREFIX_INTERVIEW_ADDRESS).get());

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
