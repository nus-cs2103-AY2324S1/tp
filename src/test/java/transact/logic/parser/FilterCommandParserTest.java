package transact.logic.parser;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import transact.logic.commands.FilterCommand;
import transact.logic.parser.exceptions.ParseException;
public class FilterCommandParserTest {

    private final FilterCommandParser parser = new FilterCommandParser();

    // Test Valid Input
    @Test
    public void parse_validInput_success() throws ParseException {
        String userInput = "filter ty/E has/Payment after/09/09/23 before/10/10/23 more/100 less/1000 by/5";
        FilterCommand command = parser.parse(userInput);
        assertNotNull(command);
    }

    // Test Invalid Input
    @Test
    public void parse_invalidInput_throwsParseException() {
        assertThrows(ParseException.class, () -> parser.parse("invalid input"));
        assertThrows(ParseException.class, () -> parser.parse("filter ty/I"));
        assertThrows(ParseException.class, () -> parser.parse("filter after/99/99/99"));
        assertThrows(ParseException.class, () -> parser.parse("filter before/99/99/99"));
        assertThrows(ParseException.class, () -> parser.parse("filter more/-100"));
        assertThrows(ParseException.class, () -> parser.parse("filter less/-100"));
        assertThrows(ParseException.class, () -> parser.parse("filter s/-100"));
        assertThrows(ParseException.class, () -> parser.parse("filter"));
        assertThrows(ParseException.class, () -> parser.parse("filter ty/"));
    }

    // Test Missing Input
    @Test
    public void parse_missingInput_throwsParseException() {
        assertThrows(ParseException.class, () -> parser.parse(""));
    }

    // Test Duplicate Inputs
    @Test
    public void parse_duplicateInputs_throwsParseException() {
        assertThrows(ParseException.class, () -> parser.parse("filter ty/R ty/E"));
        assertThrows(ParseException.class, () -> parser.parse("filter more/100 more/50"));
        assertThrows(ParseException.class, () -> parser.parse("filter less/100 less/50"));
        assertThrows(ParseException.class, () -> parser.parse("filter after/10/10/23 after 11/11/23"));
        assertThrows(ParseException.class, () -> parser.parse("filter before/10/10/23 before 11/11/23"));
        assertThrows(ParseException.class, () -> parser.parse("filter s/5 s/4"));
    }
}
