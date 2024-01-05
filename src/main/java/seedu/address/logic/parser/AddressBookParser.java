package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.AddTaskCommand;
import seedu.address.logic.commands.AddToConsultCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CreateConsultCommand;
import seedu.address.logic.commands.CreateSessionCommand;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.DeleteCommentCommand;
import seedu.address.logic.commands.DeleteConsultationCommand;
import seedu.address.logic.commands.DeleteGradeCommand;
import seedu.address.logic.commands.DeleteSessionCommand;
import seedu.address.logic.commands.DeleteTaskCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommentCommand;
import seedu.address.logic.commands.EditGradeCommand;
import seedu.address.logic.commands.EditGradedTestCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.RemoveFromConsultCommand;
import seedu.address.logic.commands.TabCommand;
import seedu.address.logic.commands.TakeAttendanceCommand;
import seedu.address.logic.commands.UpdateSessionRemarkCommand;
import seedu.address.logic.commands.UpdateTaskProgressCommand;
import seedu.address.logic.commands.ViewAssignmentsCommand;
import seedu.address.logic.commands.ViewAttendanceCommand;
import seedu.address.logic.commands.ViewTasksCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses user input.
 */
public class AddressBookParser {

    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");
    private static final Logger logger = LogsCenter.getLogger(AddressBookParser.class);

    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    public Command parseCommand(String userInput) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
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

        case AddCommand.COMMAND_WORD:
            return new AddCommandParser().parse(arguments);

        case EditCommand.COMMAND_WORD:
            return new EditCommandParser().parse(arguments);

        case DeleteCommand.COMMAND_WORD:
            return new DeleteCommandParser().parse(arguments);

        case ClearCommand.COMMAND_WORD:
            return new ClearCommand();

        case FindCommand.COMMAND_WORD:
            return new FindCommandParser().parse(arguments);

        case ListCommand.COMMAND_WORD:
            return new ListCommand();

        // TASKS
        case AddTaskCommand.COMMAND_WORD:
            return new AddTaskCommandParser().parse(arguments);

        case ViewTasksCommand.COMMAND_WORD:
            return new ViewTasksCommandParser().parse(arguments);

        case DeleteTaskCommand.COMMAND_WORD:
            return new DeleteTaskCommandParser().parse(arguments);

        case UpdateTaskProgressCommand.COMMAND_WORD:
            return new UpdateTaskProgressCommandParser().parse(arguments);

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        // SESSIONS

        case CreateSessionCommand.COMMAND_WORD:
            return new CreateSessionCommandParser().parse(arguments);

        case UpdateSessionRemarkCommand.COMMAND_WORD:
            return new UpdateSessionRemarkCommandParser().parse(arguments);

        case DeleteSessionCommand.COMMAND_WORD:
            return new DeleteSessionCommandParser().parse(arguments);

        case TakeAttendanceCommand.COMMAND_WORD:
            return new TakeAttendanceCommandParser().parse(arguments);

        case ViewAttendanceCommand.COMMAND_WORD:
            return new ViewAttendanceCommandParser().parse(arguments);


        // CONSULTATIONS

        case CreateConsultCommand.COMMAND_WORD:
            return new CreateConsultCommandParser().parse(arguments);

        case DeleteConsultationCommand.COMMAND_WORD:
            return new DeleteConsultationCommandParser().parse(arguments);

        case AddToConsultCommand.COMMAND_WORD:
            return new AddToConsultCommandParser().parse(arguments);

        case RemoveFromConsultCommand.COMMAND_WORD:
            return new RemoveFromConsultCommandParser().parse(arguments);

        // GRADES

        case EditGradeCommand.COMMAND_WORD:
            return new EditGradeCommandParser().parse(arguments);

        case EditGradedTestCommand.COMMAND_WORD:
            return new EditGradedTestCommandParser().parse(arguments);

        case DeleteGradeCommand.COMMAND_WORD:
            return new DeleteGradeCommandParser().parse(arguments);

        case EditCommentCommand.COMMAND_WORD:
            return new EditCommentCommandParser().parse(arguments);

        case DeleteCommentCommand.COMMAND_WORD:
            return new DeleteCommentCommandParser().parse(arguments);

        case ViewAssignmentsCommand.COMMAND_WORD:
            return new ViewAssignmentsCommandParser().parse(arguments);

        case TabCommand.COMMAND_WORD:
            return new TabCommandParser().parse(arguments);

        default:
            logger.finer("This user input caused a ParseException: " + userInput);
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
