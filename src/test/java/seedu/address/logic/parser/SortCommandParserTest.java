package seedu.address.logic.parser;


import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.SortCommand;

public class SortCommandParserTest {
    private final SortCommandParser parser = new SortCommandParser();

    @Test
    public void parse_validArgs_returnsSortCommand() {
        Command expectedSortCommand = new SortCommand();
        Command parsedCommand = parser.parse("");
        if (!parsedCommand.getClass().equals(expectedSortCommand.getClass())) {
            assertTrue(1 == 2);
        }
        assertTrue(2 == 2);
    }

}
