package seedu.address.logic.parser;

import seedu.address.logic.commands.AddShortcutCommand;
import seedu.address.logic.commands.CommandWord;
import seedu.address.logic.parser.exceptions.ParseException;

public class AddShortcutCommandParser implements ParserBasic<AddShortcutCommand> {

    public static String MESSAGE_INVALID_VALUE = "That command does not exist!";
    @Override
    public AddShortcutCommand parse(String userInput) throws ParseException {
        if (userInput.isEmpty() || userInput.isBlank()) {
            throw new ParseException(AddShortcutCommand.MESSAGE_USAGE);
        }
        // TODO: Make this more legit either with wrappers or more checks (preferably the former)
        String[] pair = userInput.trim().split("\\s+");
        if (!CommandWord.isCommandWord(pair[1])) {
            throw new ParseException(MESSAGE_INVALID_VALUE);
        }
        return new AddShortcutCommand(pair[0], pair[1]);
    }
}
