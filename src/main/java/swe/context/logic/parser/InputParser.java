package swe.context.logic.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import swe.context.annotation.Nullable;
import swe.context.logic.Messages;
import swe.context.logic.commands.AddCommand;
import swe.context.logic.commands.ClearCommand;
import swe.context.logic.commands.Command;
import swe.context.logic.commands.DeleteCommand;
import swe.context.logic.commands.EditCommand;
import swe.context.logic.commands.ExitCommand;
import swe.context.logic.commands.FindCommand;
import swe.context.logic.commands.HelpCommand;
import swe.context.logic.commands.ListCommand;
import swe.context.logic.parser.exceptions.ParseException;



/**
 * Contains methods for parsing user input.
 */
public final class InputParser {
    /**
     * Used for initial separation of command word and arguments.
     */
    private static final Pattern REGEX_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    /**
     * Returns a parsed {@link Command} based on the specified user input. The
     * {@link Command} can then be used for execution.
     *
     * @throws ParseException If input doesn't conform to expected format.
     */
    public static Command parseCommand(String userInput) throws ParseException {
        String trimmed = userInput.trim();
        Matcher matcher = REGEX_COMMAND_FORMAT.matcher(trimmed);
        if (!matcher.matches()) {
            throw new ParseException(
                Messages.commandInvalidFormat(HelpCommand.MESSAGE_USAGE)
            );
        }

        @Nullable String commandWord = matcher.group("commandWord");
        @Nullable String arguments = matcher.group("arguments");

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
            throw new ParseException(Messages.COMMAND_UNKNOWN);
        }
    }

    private InputParser() {
        // No instantiation
    }
}
