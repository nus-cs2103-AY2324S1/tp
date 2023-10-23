package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;

public class EditCommandParserTest {

    private EditCommandParser p = new EditCommandParser();

    @Test
    void happyCases() {
        try {
            p.parse("edit 1 -name Yiwen");
            p.parse("edit 1 -phone 98765432");
            p.parse("edit 1 -email fakeEmail@com");
        } catch (ParseException e) {
            fail();
        }
    }
    @Test
    void badCases() {
        assertThrows(ParseException.class, () -> p.parse("edit -name yiwen"));
    }
}
