package seedu.address.logic.parser;

import seedu.address.logic.commands.Command;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * The parent class for all the Parsers for the different tabs.
 */
public abstract class ParseCommandHandlers {
    public abstract Command parseCommand(String userInput) throws ParseException;
}
