package seedu.flashlingo.logic.parser;

import seedu.flashlingo.logic.newcommands.NewCommand;
import seedu.flashlingo.logic.parser.exceptions.ParseException;

/**
 * Represents a Parser that is able to parse user input into a {@code Command} of type {@code T}.
 */
public interface NewParser<T extends NewCommand> {

    /**
     * Parses {@code userInput} into a command and returns it.
     * @throws ParseException if {@code userInput} does not conform the expected format
     */
    T parse(String userInput) throws ParseException;
}
