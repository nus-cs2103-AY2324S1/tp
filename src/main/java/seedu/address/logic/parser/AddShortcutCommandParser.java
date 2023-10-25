package seedu.address.logic.parser;

import seedu.address.logic.commands.AddShortcutCommand;
import seedu.address.logic.commands.CommandWord;
import seedu.address.logic.commands.ShortcutAlias;
import seedu.address.logic.parser.exceptions.ParseException;

import java.util.stream.Stream;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMMAND_WORD;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SHORTCUT;

public class AddShortcutCommandParser implements ParserBasic<AddShortcutCommand> {

    public static String MESSAGE_INVALID_VALUE = "That command does not exist!";
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
    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }


}
