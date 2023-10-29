package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

import seedu.address.logic.commands.Command;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.PersonType;


/**
 * Contains helper methods for testing command parsers.
 */
public class CommandParserTestUtil {

    /**
     * Asserts that the parsing of {@code userInput} by {@code parserComplex} is successful and the command created
     * equals to {@code expectedCommand}.
     */
    public static void assertParseComplexSuccess(ParserComplex<? extends Command> parserComplex, String userInput,
                                                 Command expectedCommand, PersonType personType) {
        try {
            Command command = parserComplex.parse(personType, userInput);
            assertEquals(expectedCommand, command);
        } catch (ParseException pe) {
            throw new IllegalArgumentException("Invalid userInput.", pe);
        }
    }

    /**
     * Asserts that the parsing of {@code userInput} by {@code parserComplex} is unsuccessful and the error message
     * equals to {@code expectedMessage}.
     */
    public static void assertParseComplexFailure(ParserComplex<? extends Command> parserComplex,
                                                 String userInput, String expectedMessage,
                                                 PersonType personType) {
        try {
            parserComplex.parse(personType, userInput);
            throw new AssertionError("The expected ParseException was not thrown.");
        } catch (ParseException pe) {
            assertEquals(expectedMessage, pe.getMessage());
        }
    }

    /**
     * Asserts that the parsing of {@code userInput} by {@code parserBasic} is successful and the command created
     * equals to {@code expectedCommand}.
     */
    public static void assertParseBasicSuccess(ParserBasic<? extends Command> parserBasic, String userInput,
                                                 Command expectedCommand) {
        try {
            Command command = parserBasic.parse(userInput);
            assertEquals(expectedCommand, command);
        } catch (ParseException pe) {
            throw new IllegalArgumentException("Invalid userInput.", pe);
        }
    }

    /**
     * Asserts that the parsing of {@code userInput} by {@code parserBasic} is unsuccessful and the error message
     * equals to {@code expectedMessage}.
     */
    public static void assertParseBasicFailure(ParserBasic<? extends Command> parserBasic, String userInput,
                                               String expectedMessage) {
        try {
            parserBasic.parse(userInput);
            throw new AssertionError("The expected ParseException was not thrown.");
        } catch (ParseException pe) {
            assertEquals(expectedMessage, pe.getMessage());
        }
    }
}
