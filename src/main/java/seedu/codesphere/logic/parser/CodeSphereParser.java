package seedu.codesphere.logic.parser;

import static seedu.codesphere.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.codesphere.logic.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.codesphere.logic.Messages.MESSAGE_WRONG_STAGE_COURSE;
import static seedu.codesphere.logic.Messages.MESSAGE_WRONG_STAGE_HOME;

import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.codesphere.commons.core.LogsCenter;
import seedu.codesphere.logic.commands.AddCommand;
import seedu.codesphere.logic.commands.AddCourseCommand;
import seedu.codesphere.logic.commands.ClearCommand;
import seedu.codesphere.logic.commands.ClearCourseCommand;
import seedu.codesphere.logic.commands.Command;
import seedu.codesphere.logic.commands.DeleteCommand;
import seedu.codesphere.logic.commands.DeleteCourseCommand;
import seedu.codesphere.logic.commands.EditCommand;
import seedu.codesphere.logic.commands.EditCourseCommand;
import seedu.codesphere.logic.commands.ExitCommand;
import seedu.codesphere.logic.commands.FindCommand;
import seedu.codesphere.logic.commands.FindCourseCommand;
import seedu.codesphere.logic.commands.HelpCommand;
import seedu.codesphere.logic.commands.HomeCommand;
import seedu.codesphere.logic.commands.ListPendingQuestionsCommand;
import seedu.codesphere.logic.commands.PendingQuestionCommand;
import seedu.codesphere.logic.commands.RemarkCommand;
import seedu.codesphere.logic.commands.RemoveCommand;
import seedu.codesphere.logic.commands.ResetCommand;
import seedu.codesphere.logic.commands.ResetCourseCommand;
import seedu.codesphere.logic.commands.SelectCommand;
import seedu.codesphere.logic.commands.SortCommand;
import seedu.codesphere.logic.parser.exceptions.ParseException;
import seedu.codesphere.logic.stagemanager.StageManager;
import seedu.codesphere.logic.stagemanager.Stages;

/**
 * Parses user input.
 */
public class CodeSphereParser {

    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");
    private static final Logger logger = LogsCenter.getLogger(CodeSphereParser.class);

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

        Stages currentStage = StageManager.getInstance().getStage();

        switch (currentStage) {
        case HOME:
            return parseHomeStageCommand(commandWord, arguments);
        case SELECTED_COURSE:
            return parseCourseStageCommand(commandWord, arguments);
        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

    // Solution below inspired from github.com/AY2223S1-CS2103T-W11-4/tp
    //@@author devanshubisht
    /**
     * Parses user input into command for execution.
     *
     * @param commandWord command word
     * @param arguments arguments
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    private Command parseHomeStageCommand(String commandWord, String arguments) throws ParseException {
        switch (commandWord.toLowerCase()) {
        case AddCourseCommand.COMMAND_WORD:
            return new AddCourseCommandParser().parse(arguments);

        case ClearCourseCommand.COMMAND_WORD:
            return new ClearCourseCommandParser().parse(arguments);

        case DeleteCourseCommand.COMMAND_WORD:
            return new DeleteCourseCommandParser().parse(arguments);

        case EditCourseCommand.COMMAND_WORD:
            return new EditCourseCommandParser().parse(arguments);

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case FindCourseCommand.COMMAND_WORD:
            return new FindCourseCommandParser().parse(arguments);

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        case HomeCommand.COMMAND_WORD:
            return new HomeCommandParser().parse(arguments);

        case ResetCourseCommand.COMMAND_WORD:
            return new ResetCourseCommandParser().parse(arguments);

        case SelectCommand.COMMAND_WORD:
            return new SelectCommandParser().parse(arguments);

        case ListPendingQuestionsCommand.COMMAND_WORD:
            throw new ParseException(MESSAGE_WRONG_STAGE_HOME);

        case SortCommand.COMMAND_WORD:
            throw new ParseException(MESSAGE_WRONG_STAGE_HOME);

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

    // Solution below inspired from github.com/AY2223S1-CS2103T-W11-4/tp
    //@@author devanshubisht
    /**
     * Parses user input into command for execution.
     *
     * @param commandWord command word
     * @param arguments arguments
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    private Command parseCourseStageCommand(String commandWord, String arguments) throws ParseException {
        switch (commandWord.toLowerCase()) {
        case AddCommand.COMMAND_WORD:
            return new AddCommandParser().parse(arguments);

        case ClearCommand.COMMAND_WORD:
            return new ClearCommandParser().parse(arguments);

        case DeleteCommand.COMMAND_WORD:
            return new DeleteCommandParser().parse(arguments);

        case EditCommand.COMMAND_WORD:
            return new EditCommandParser().parse(arguments);

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case FindCommand.COMMAND_WORD:
            return new FindCommandParser().parse(arguments);

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        case HomeCommand.COMMAND_WORD:
            return new HomeCommandParser().parse(arguments);

        case PendingQuestionCommand.COMMAND_WORD:
            return new PendingQuestionCommandParser().parse(arguments);

        case RemarkCommand.COMMAND_WORD:
            return new RemarkCommandParser().parse(arguments);

        case RemoveCommand.COMMAND_WORD:
            return new RemoveCommandParser().parse(arguments);

        case ResetCommand.COMMAND_WORD:
            return new ResetCommandParser().parse(arguments);

        case SortCommand.COMMAND_WORD:
            return new SortCommandParser().parse(arguments);

        case ListPendingQuestionsCommand.COMMAND_WORD:
            return new ListPendingQuestionsCommandParser().parse(arguments);

        case SelectCommand.COMMAND_WORD:
            throw new ParseException(MESSAGE_WRONG_STAGE_COURSE);

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }
}
