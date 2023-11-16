package seedu.address.logic.parser.addroles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.addroles.AddClientRoleCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class AddClientRoleCommandParserTest {

    private final AddClientRoleCommandParser parser = new AddClientRoleCommandParser();

    @Test
    public void parse_validInput_success() throws ParseException {
        AddClientRoleCommand expectedCommand = new AddClientRoleCommand("Hi");
        AddClientRoleCommand parsedCommand = parser.parse(" Hi");
        assertEquals(expectedCommand.toString(), parsedCommand.toString());
    }

    @Test
    public void parse_validInputWithLeadingSpaces_success() throws ParseException {
        AddClientRoleCommand expectedCommand = new AddClientRoleCommand("Tester");
        AddClientRoleCommand parsedCommand = parser.parse("   Tester");
        assertEquals(expectedCommand.toString(), parsedCommand.toString());
    }


    @Test
    public void parse_emptyInput_throwsParseException() {
        // Empty input
        assertThrows(ParseException.class, () -> parser.parse(""));
    }

    @Test
    public void parse_emptyRoleWithMultiplesSpaces_throwsParseException() {
        assertThrows(ParseException.class, () -> parser.parse("    "));
    }
}
