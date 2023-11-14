package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.model.Model.MESSAGE_WRONG_VIEW_FIRST_HALF;
import static seedu.address.model.Model.MESSAGE_WRONG_VIEW_SECOND_HALF;

import java.util.function.Function;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.assignment.AddAssignmentCommand;
import seedu.address.logic.commands.assignment.DeleteAssignmentCommand;
import seedu.address.logic.commands.assignment.EditAssignmentCommand;
import seedu.address.logic.commands.assignment.FindAssignmentCommand;
import seedu.address.logic.commands.assignment.ListAssignmentCommand;
import seedu.address.logic.commands.assignment.MarkAssignmentCommand;
import seedu.address.logic.commands.assignment.UnMarkAssignmentCommand;
import seedu.address.logic.commands.internship.ListInternshipCommand;
import seedu.address.logic.commands.internship.role.AddInternshipRoleCommand;
import seedu.address.logic.commands.internship.role.DeleteInternshipRoleCommand;
import seedu.address.logic.commands.internship.role.EditInternshipRoleCommand;
import seedu.address.logic.commands.internship.role.FindInternshipRoleCommand;
import seedu.address.logic.commands.internship.task.AddInternshipTaskCommand;
import seedu.address.logic.commands.internship.task.DeleteInternshipTaskCommand;
import seedu.address.logic.commands.internship.task.EditInternshipTaskCommand;
import seedu.address.logic.commands.internship.task.FindInternshipTaskCommand;
import seedu.address.logic.commands.internship.task.MarkInternshipTaskCommand;
import seedu.address.logic.commands.internship.task.UnMarkInternshipTaskCommand;
import seedu.address.logic.parser.assignment.AddAssignmentParser;
import seedu.address.logic.parser.assignment.DeleteAssignmentParser;
import seedu.address.logic.parser.assignment.EditAssignmentParser;
import seedu.address.logic.parser.assignment.FindAssignmentParser;
import seedu.address.logic.parser.assignment.ListAssignmentParser;
import seedu.address.logic.parser.assignment.MarkAssignmentParser;
import seedu.address.logic.parser.assignment.UnMarkAssignmentParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.logic.parser.internship.role.AddInternshipRoleParser;
import seedu.address.logic.parser.internship.role.DeleteInternshipRoleParser;
import seedu.address.logic.parser.internship.role.EditInternshipRoleParser;
import seedu.address.logic.parser.internship.role.FindInternshipRoleParser;
import seedu.address.logic.parser.internship.task.AddInternshipTaskParser;
import seedu.address.logic.parser.internship.task.DeleteInternshipTaskParser;
import seedu.address.logic.parser.internship.task.EditInternshipTaskParser;
import seedu.address.logic.parser.internship.task.FindInternshipTaskParser;
import seedu.address.logic.parser.internship.task.MarkInternshipTaskParser;
import seedu.address.logic.parser.internship.task.UnMarkInternshipTaskParser;
import seedu.address.model.View;

/**
 * Parses user input.
 */
public class AddressBookParser {

    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");
    private static final Logger logger = LogsCenter.getLogger(AddressBookParser.class);

    private void verifyView(Function<View, Boolean> viewVerifier, View correctView) throws ParseException {
        if (!viewVerifier.apply(correctView)) {
            throw new ParseException(MESSAGE_WRONG_VIEW_FIRST_HALF + correctView + MESSAGE_WRONG_VIEW_SECOND_HALF);
        }
    }


    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    public Command parseCommand(String userInput, Function<View, Boolean> viewVerifier) throws ParseException {
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

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        case AddAssignmentCommand.COMMAND_WORD:
            verifyView(viewVerifier, View.ASSIGNMENTS);
            return new AddAssignmentParser().parse(arguments);

        case MarkAssignmentCommand.COMMAND_WORD:
            verifyView(viewVerifier, View.ASSIGNMENTS);
            return new MarkAssignmentParser().parse(arguments);

        case UnMarkAssignmentCommand.COMMAND_WORD:
            verifyView(viewVerifier, View.ASSIGNMENTS);
            return new UnMarkAssignmentParser().parse(arguments);

        case ListAssignmentCommand.COMMAND_WORD:
            return new ListAssignmentParser().parse(arguments);

        case DeleteAssignmentCommand.COMMAND_WORD:
            verifyView(viewVerifier, View.ASSIGNMENTS);
            return new DeleteAssignmentParser().parse(arguments);

        case FindAssignmentCommand.COMMAND_WORD:
            verifyView(viewVerifier, View.ASSIGNMENTS);
            return new FindAssignmentParser().parse(arguments);

        case EditAssignmentCommand.COMMAND_WORD:
            verifyView(viewVerifier, View.ASSIGNMENTS);
            return new EditAssignmentParser().parse(arguments);

        case AddInternshipRoleCommand.COMMAND_WORD:
            verifyView(viewVerifier, View.INTERNSHIPS);
            return new AddInternshipRoleParser().parse(arguments);

        case DeleteInternshipRoleCommand.COMMAND_WORD:
            verifyView(viewVerifier, View.INTERNSHIPS);
            return new DeleteInternshipRoleParser().parse(arguments);

        case EditInternshipRoleCommand.COMMAND_WORD:
            verifyView(viewVerifier, View.INTERNSHIPS);
            return new EditInternshipRoleParser().parse(arguments);

        case AddInternshipTaskCommand.COMMAND_WORD:
            verifyView(viewVerifier, View.INTERNSHIPS);
            return new AddInternshipTaskParser().parse(arguments);

        case DeleteInternshipTaskCommand.COMMAND_WORD:
            verifyView(viewVerifier, View.INTERNSHIPS);
            return new DeleteInternshipTaskParser().parse(arguments);

        case EditInternshipTaskCommand.COMMAND_WORD:
            verifyView(viewVerifier, View.INTERNSHIPS);
            return new EditInternshipTaskParser().parse(arguments);

        case ListInternshipCommand.COMMAND_WORD:
            return new ListInternshipCommand();

        case MarkInternshipTaskCommand.COMMAND_WORD:
            verifyView(viewVerifier, View.INTERNSHIPS);
            return new MarkInternshipTaskParser().parse(arguments);

        case UnMarkInternshipTaskCommand.COMMAND_WORD:
            verifyView(viewVerifier, View.INTERNSHIPS);
            return new UnMarkInternshipTaskParser().parse(arguments);

        case FindInternshipTaskCommand.COMMAND_WORD:
            verifyView(viewVerifier, View.INTERNSHIPS);
            return new FindInternshipTaskParser().parse(arguments);

        case FindInternshipRoleCommand.COMMAND_WORD:
            verifyView(viewVerifier, View.INTERNSHIPS);
            return new FindInternshipRoleParser().parse(arguments);

        default:
            logger.finer("This user input caused a ParseException: " + userInput);
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
