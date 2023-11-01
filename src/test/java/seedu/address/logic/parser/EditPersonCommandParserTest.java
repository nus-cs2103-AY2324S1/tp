package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.EditPersonCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class EditPersonCommandParserTest {

    private EditPersonCommandParser p = new EditPersonCommandParser();

    @Test
    void happyCases() {
        try {
            p.parse(EditPersonCommand.COMMAND_WORD + " 1 -name Yiwen");
            p.parse(EditPersonCommand.COMMAND_WORD + " 1 -phone 98765432");
            p.parse(EditPersonCommand.COMMAND_WORD + " 1 -email fakeEmail@com");
        } catch (ParseException e) {
            fail();
        }
    }
    @Test
    void badCases() {
        assertThrows(ParseException.class, () -> p.parse("edit -name yiwen"));
    }
}
