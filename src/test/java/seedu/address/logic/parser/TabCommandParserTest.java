package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.commons.core.tab.Tab.FINANCE_PARAMETER;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.tab.Tab;
import seedu.address.logic.commands.TabCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class TabCommandParserTest {
    @Test
    void parse_validArgs_returnsTabCommand() throws ParseException {
        TabCommandParser parser = new TabCommandParser();
        TabCommand command = parser.parse(FINANCE_PARAMETER);
        assertEquals(new TabCommand(Tab.fromParameter(FINANCE_PARAMETER)), command);
    }

    @Test
    void parse_invalidArgs_throwsParseException() {
        TabCommandParser parser = new TabCommandParser();
        assertThrows(ParseException.class, () -> parser.parse("invalidTab"));
    }
}
