package seedu.application.logic.parser;

import static seedu.application.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.application.logic.parser.CliSyntax.*;

import java.util.stream.Stream;

import seedu.application.commons.core.index.Index;
import seedu.application.logic.commands.InterviewDeleteCommand;
import seedu.application.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new InterviewDeleteCommand object
 */
public class InterviewDeleteCommandParser implements Parser<InterviewDeleteCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the InterviewDeleteCommand
     * and returns a InterviewDeleteCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public InterviewDeleteCommand parse(String args) throws ParseException {

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_JOB_SOURCE);

        Index jobIndex;
        Index interviewIndex;

        if (!arePrefixesPresent(argMultimap, PREFIX_JOB_SOURCE)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    InterviewDeleteCommand.MESSAGE_USAGE));
        }

        try {
            interviewIndex = ParserUtil.parseIndex(argMultimap.getPreamble());
            jobIndex = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_JOB_SOURCE).get());
            return new InterviewDeleteCommand(jobIndex, interviewIndex);
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    InterviewDeleteCommand.MESSAGE_USAGE), pe);
        }
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
