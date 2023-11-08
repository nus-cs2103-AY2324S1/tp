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
            p.parse("1 -name Yiwen");
            p.parse("1 -phone 98765432");
            p.parse("1 -email fakeEmail@com");
        } catch (ParseException e) {
            fail();
        }
    }
    //todo, specify this behaviour in the doc "edit index -name yiwen " would not trigger this exception
}
