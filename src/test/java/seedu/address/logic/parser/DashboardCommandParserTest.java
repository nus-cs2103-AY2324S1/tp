package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.DashboardCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class DashboardCommandParserTest {

    private final DashboardCommandParser parser = new DashboardCommandParser();

    @Test
    public void parse_emptyArgs_returnsDashboardCommand() {
        assertParseSuccess(parser, "", new DashboardCommand());
    }

    @Test
    public void parse_validArgs_returnsDashboardCommand() {
        // As the implementation of DashboardCommandParser does not depend on the given args,
        // it should return a DashboardCommand for any valid string input.
        assertParseSuccess(parser, "valid string", new DashboardCommand());
    }

    /**
     * Asserts the parsing of {@code userInput} is successful and the result matches {@code expectedCommand}.
     */
    private void assertParseSuccess(DashboardCommandParser parser, String userInput, DashboardCommand expectedCommand) {
        try {
            DashboardCommand command = parser.parse(userInput);
            assertEquals(expectedCommand, command);
        } catch (ParseException pe) {
            throw new AssertionError("Parsing valid input should not throw ParseException.", pe);
        }
    }
}
