package seedu.address.logic.parser;

import seedu.address.logic.commands.Command;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Represents a parser that the command line utilizes to parse the command.
 */
public interface Parser {
    public Command parseCommand(String userInput) throws ParseException;
}
