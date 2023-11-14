package seedu.application.logic.parser;

import static seedu.application.logic.Messages.*;

import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.application.commons.core.LogsCenter;
import seedu.application.logic.commands.HelpCommand;
import seedu.application.logic.commands.InterviewAddCommand;
import seedu.application.logic.commands.InterviewCommand;
import seedu.application.logic.commands.InterviewDeleteCommand;
import seedu.application.logic.commands.InterviewEditCommand;
import seedu.application.logic.parser.exceptions.ParseException;

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
     * Parses user input into command for execution.
     *
     * @param args full user input string
     * @return the command based on the user input
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
            return new InterviewAddCommandParser().parse(arguments);

        case InterviewDeleteCommand.COMMAND_WORD:
            return new InterviewDeleteCommandParser().parse(arguments);

        case InterviewEditCommand.COMMAND_WORD:
            return new InterviewEditCommandParser().parse(arguments);
        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }
}
