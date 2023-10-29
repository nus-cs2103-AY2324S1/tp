package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.ArgumentMultimap.arePrefixesPresent;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMMAND_WORD;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SHORTCUT;

import seedu.address.logic.commands.AddShortcutCommand;
import seedu.address.logic.commands.CommandWord;
import seedu.address.logic.commands.ShortcutAlias;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new AddShortcutCommand object
 */
public class AddShortcutCommandParser implements ParserBasic<AddShortcutCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddShortcutCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public AddShortcutCommand parse(String userInput) throws ParseException {
        ArgumentMultimap argMultimap =

                ArgumentTokenizer.tokenize(userInput, PREFIX_COMMAND_WORD, PREFIX_SHORTCUT);

        if (!arePrefixesPresent(argMultimap, PREFIX_SHORTCUT, PREFIX_COMMAND_WORD)

                || !argMultimap.getPreamble().isEmpty()) {

            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddShortcutCommand.MESSAGE_USAGE));
        }
        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_COMMAND_WORD, PREFIX_SHORTCUT);

        CommandWord commandWord = ParserUtil.parseCommandWord(argMultimap.getValue(PREFIX_COMMAND_WORD).get());
        ShortcutAlias shortcutAlias = ParserUtil.parseShortcutAlias(argMultimap.getValue(PREFIX_SHORTCUT).get());

        return new AddShortcutCommand(shortcutAlias, commandWord);
    }

}
