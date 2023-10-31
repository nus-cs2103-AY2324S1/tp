package seedu.application.logic.parser;

import static seedu.application.logic.Messages.*;
import static seedu.application.logic.parser.CliSyntax.*;

import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import seedu.application.commons.core.LogsCenter;
import seedu.application.commons.core.index.Index;
import seedu.application.logic.commands.HelpCommand;
import seedu.application.logic.commands.InterviewAddCommand;
import seedu.application.logic.commands.InterviewCommand;
import seedu.application.logic.commands.InterviewDeleteCommand;
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
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");
    private static final Logger logger = LogsCenter.getLogger(ApplicationBookParser.class);

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public InterviewCommand parse(String args) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(args.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");

        // Note to developers: Change the log level in config.json to enable lower level (i.e., FINE, FINER and lower)
        // log messages such as the one below.
        // Lower level log messages are used sparingly to minimize noise in the code.
        logger.fine("Command word: " + commandWord + "; Arguments: " + arguments);
        switch (commandWord) {
        case InterviewAddCommand.COMMAND_WORD:
            return parseAdd(arguments);

        case InterviewDeleteCommand.COMMAND_WORD:
            return new InterviewDeleteCommandParser().parse(arguments);

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

    private static InterviewAddCommand parseAdd(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_INTERVIEW_TYPE,
                        PREFIX_INTERVIEW_DATETIME, PREFIX_INTERVIEW_ADDRESS);

        Index index;

        if (!arePrefixesPresent(argMultimap, PREFIX_INTERVIEW_TYPE,
            PREFIX_INTERVIEW_DATETIME, PREFIX_INTERVIEW_ADDRESS)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, InterviewAddCommand.MESSAGE_USAGE));
        }

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
