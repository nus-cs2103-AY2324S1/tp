package seedu.address.logic.parser.deleteroles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.deleteroles.DeleteClientRoleCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class DeleteClientRoleCommandParserTest {

    private final DeleteClientRoleCommandParser parser = new DeleteClientRoleCommandParser();

    @Test
    public void parse_validInput_success() throws ParseException {
        DeleteClientRoleCommand expectedCommand = new DeleteClientRoleCommand("Role");
        assertEquals(expectedCommand.toString(), parser.parse(" Role").toString());
    }

    @Test
    public void parse_validInputWithLeadingSpaces_success() throws ParseException {
        DeleteClientRoleCommand expectedCommand = new DeleteClientRoleCommand("Role");
        assertEquals(expectedCommand.toString(), parser.parse("   Role").toString());
    }

    @Test
    public void parse_emptyInput_throwsParseException() {
        assertThrows(ParseException.class, () -> parser.parse(""));
    }
    @Test
    public void parse_emptyRoleWithMultipleSpaces_throwsParseException() {
        assertThrows(ParseException.class, () -> parser.parse("    "));
    }
}
