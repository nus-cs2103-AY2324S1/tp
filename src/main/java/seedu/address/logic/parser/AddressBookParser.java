package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.AddApplicantCommand;
import seedu.address.logic.commands.AddMemberCommand;
import seedu.address.logic.commands.AddMemberTaskCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CopyApplicantCommand;
import seedu.address.logic.commands.CopyMemberCommand;
import seedu.address.logic.commands.DeleteApplicantCommand;
import seedu.address.logic.commands.DeleteMemberCommand;
import seedu.address.logic.commands.DeleteMemberTaskCommand;
import seedu.address.logic.commands.EditApplicantCommand;
import seedu.address.logic.commands.EditMemberCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindApplicantCommand;
import seedu.address.logic.commands.FindMemberCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ViewApplicantsCommand;
import seedu.address.logic.commands.ViewMemberTaskCommand;
import seedu.address.logic.commands.ViewMembersCommand;
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

        switch (commandWord.toLowerCase()) {

        case AddMemberCommand.COMMAND_WORD:
        case AddMemberCommand.COMMAND_ALIAS:
            return new AddMemberCommandParser().parse(arguments);

        case AddApplicantCommand.COMMAND_WORD:
        case AddApplicantCommand.COMMAND_ALIAS:
            return new AddApplicantCommandParser().parse(arguments);

        case EditMemberCommand.COMMAND_WORD:
        case EditMemberCommand.COMMAND_ALIAS:
            return new EditMemberCommandParser().parse(arguments);

        case EditApplicantCommand.COMMAND_WORD:
        case EditApplicantCommand.COMMAND_ALIAS:
            return new EditApplicantCommandParser().parse(arguments);

        case DeleteApplicantCommand.COMMAND_WORD:
        case DeleteApplicantCommand.COMMAND_ALIAS:
            return new DeleteApplicantCommandParser().parse(arguments);

        case DeleteMemberCommand.COMMAND_WORD:
        case DeleteMemberCommand.COMMAND_ALIAS:
            return new DeleteMemberCommandParser().parse(arguments);

        case ClearCommand.COMMAND_WORD:
            return new ClearCommand();

        case FindMemberCommand.COMMAND_WORD:
        case FindMemberCommand.COMMAND_ALIAS:
            return new FindMemberCommandParser().parse(arguments);

        case FindApplicantCommand.COMMAND_WORD:
        case FindApplicantCommand.COMMAND_ALIAS:
            return new FindApplicantCommandParser().parse(arguments);

        case ViewMembersCommand.COMMAND_WORD:
        case ViewMembersCommand.COMMAND_ALIAS:
            return new ViewMembersCommand();

        case ViewApplicantsCommand.COMMAND_WORD:
        case ViewApplicantsCommand.COMMAND_ALIAS:
            return new ViewApplicantsCommand();

        case AddMemberTaskCommand.COMMAND_WORD:
        case AddMemberTaskCommand.COMMAND_ALIAS:
            return new AddMemberTaskCommandParser().parse(arguments);

        case ViewMemberTaskCommand.COMMAND_WORD:
        case ViewMemberTaskCommand.COMMAND_ALIAS:
            return new ViewMemberTaskCommandParser().parse(arguments);

        case DeleteMemberTaskCommand.COMMAND_WORD:
        case DeleteMemberTaskCommand.COMMAND_ALIAS:
            return new DeleteMemberTaskCommandParser().parse(arguments);

        case CopyMemberCommand.COMMAND_WORD:
        case CopyMemberCommand.COMMAND_ALIAS:
            return new CopyMemberCommandParser().parse(arguments);

        case CopyApplicantCommand.COMMAND_WORD:
        case CopyApplicantCommand.COMMAND_ALIAS:
            return new CopyApplicantCommandParser().parse(arguments);

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        default:
            logger.finer("This user input caused a ParseException: " + userInput);
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
