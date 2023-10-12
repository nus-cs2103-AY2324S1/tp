package seedu.address.logic.parser;

import seedu.address.logic.commands.Command;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.PersonType;

/**
 * Represents a ParserComplex that is able to parse complex user input into a {@code Command} of type {@code T}.
 */
public interface ParserComplex<T extends Command> {

    /**
     * Parses {@code userInput} into a command and returns it.
     * @throws ParseException if {@code userInput} does not conform the expected format
     */
    T parse(PersonType personType, String userInput) throws ParseException;
}
