package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SHORTCUT;

import java.util.ArrayList;
import java.util.List;

import seedu.address.logic.commands.DeleteShortcutCommand;
import seedu.address.logic.commands.ShortcutAlias;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteShortcutCommand object
 */
public class DeleteShortcutCommandParser implements ParserBasic<DeleteShortcutCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an DeleteShortcutCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public DeleteShortcutCommand parse(String userInput) throws ParseException {
        ArgumentMultimap argMultimap =

                ArgumentTokenizer.tokenize(userInput, PREFIX_SHORTCUT);

        if (argMultimap.getValue(PREFIX_SHORTCUT).isEmpty()

                || !argMultimap.getPreamble().isEmpty()) {

            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    DeleteShortcutCommand.MESSAGE_USAGE));
        }
        List<String> stringList = argMultimap.getAllValues(PREFIX_SHORTCUT);
        List<ShortcutAlias> shortcutList = new ArrayList<>();
        for (String str : stringList) {
            shortcutList.add(ParserUtil.parseShortcutAlias(str));
        }
        return new DeleteShortcutCommand(shortcutList);
    }
}
