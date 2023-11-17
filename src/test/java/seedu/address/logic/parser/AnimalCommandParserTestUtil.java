package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

import seedu.address.logic.commands.AnimalCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Contains helper methods for testing command parsers.
 */
public class AnimalCommandParserTestUtil {

    /**
     * Asserts that the parsing of {@code args} by {@code parser} is successful and the command created
     * equals to {@code expectedCommand}.
     */
    public static void assertParseSuccess(AnimalParser<? extends AnimalCommand> parser, String args,
                                          AnimalCommand expectedCommand) {
        try {
            AnimalCommand command = parser.parse(args);
            assertEquals(expectedCommand, command);
        } catch (ParseException pe) {
            throw new IllegalArgumentException("Invalid User input.", pe);
        }
    }

    /**
     * Asserts that the parsing of {@code args} by {@code parser} is unsuccessful and the error message
     * equals to {@code expectedMessage}.
     */
    public static void assertParseFailure(AnimalParser<? extends AnimalCommand> parser,
                                          String args, String expectedMessage) {
        try {
            parser.parse(args);
            throw new AssertionError("The expected ParseException was not thrown.");
        } catch (ParseException pe) {
            assertEquals(expectedMessage, pe.getMessage());
        }
    }
}
