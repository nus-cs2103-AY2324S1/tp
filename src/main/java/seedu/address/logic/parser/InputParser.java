package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.annotation.Nullable;
import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.parser.exceptions.ParseException;



/**
 * Parses user input.
 */
public class InputParser {
    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    /**
     * Returns a parsed Command based on the specified user input.
     *
     * The returned Command can then be used for execution.
     *
     * @param userInput Full user input string.
     * @throws ParseException If input doesn't conform to expected format.
     */
    public Command parseCommand(String userInput) throws ParseException {
        String trimmed = userInput.trim();
        Matcher matcher = BASIC_COMMAND_FORMAT.matcher(trimmed);
        if (!matcher.matches()) {
            throw new ParseException(
                String.format(
                    MESSAGE_INVALID_COMMAND_FORMAT,
                    HelpCommand.MESSAGE_USAGE
                )
            );
        }

        final @Nullable String commandWord = matcher.group("commandWord");
        final @Nullable String arguments = matcher.group("arguments");

        switch (commandWord) {
        case AddCommand.COMMAND_WORD:
            return new AddCommandParser().parse(arguments);
        case ListCommand.COMMAND_WORD:
            return new ListCommand();
        case FindCommand.COMMAND_WORD:
            return new FindCommandParser().parse(arguments);
        case EditCommand.COMMAND_WORD:
            return new EditCommandParser().parse(arguments);
        case DeleteCommand.COMMAND_WORD:
            return new DeleteCommandParser().parse(arguments);
        case ClearCommand.COMMAND_WORD:
            return new ClearCommand();
        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();
        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();
        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }
}
