package transact.logic.parser;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import transact.logic.commands.SortCommand;
import transact.logic.parser.exceptions.ParseException;

public class SortCommandParserTest {

    private final SortCommandParser parser = new SortCommandParser();

    // Test Valid Input for Date Sorting
    @Test
    public void parse_validInputForDateSorting_success() throws ParseException {
        String userInput = "sort date/asc";
        SortCommand command = parser.parse(userInput);
        assertNotNull(command);
    }

    // Test Valid Input for Amount Sorting
    @Test
    public void parse_validInputForAmountSorting_success() throws ParseException {
        String userInput = "sort amt/desc";
        SortCommand command = parser.parse(userInput);
        assertNotNull(command);
    }

    // Test Valid Input for Multiple Sort Rules
    @Test
    public void parse_validInputForMultipleSortRules_success() throws ParseException {
        String userInput = "date/asc amt/desc";
        SortCommand command = parser.parse(userInput);
        assertNotNull(command);
    }

    @Test
    public void parse_invalidInput_throwsParseException() {
        assertThrows(ParseException.class, () -> parser.parse("sort ty/asc"));
        assertThrows(ParseException.class, () -> parser.parse("sort unknownPrefix/asc"));
        assertThrows(ParseException.class, () -> parser.parse("sort date/"));
        assertThrows(ParseException.class, () -> parser.parse("sort date/asc extraArgument"));
        assertThrows(ParseException.class, () -> parser.parse("sort "));
        assertThrows(ParseException.class, () -> parser.parse("sort /asc"));
        assertThrows(ParseException.class, () -> parser.parse("sort date/ascending"));

    }

    // Test Duplicate Prefixes
    @Test
    public void parse_duplicatePrefixes_throwsParseException() {
        assertThrows(ParseException.class, () -> parser.parse("sort date/asc date/desc"));
        assertThrows(ParseException.class, () -> parser.parse("sort amt/asc amt/desc"));

    }
}

