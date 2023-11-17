package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.fail;
import static seedu.address.logic.parser.AnimalCommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.AnimalCommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.AnimalMessages;
import seedu.address.logic.commands.SearchAnimalCommand;

public class SearchAnimalCommandParserTest {
    private SearchAnimalCommandParser parser = new SearchAnimalCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                AnimalMessages.getFormattedHelpMessage(AnimalMessages.MESSAGE_INVALID_SEARCH_KEYWORDS,
                SearchAnimalCommand.EXAMPLE_USAGE));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "bob", AnimalMessages.getFormattedHelpMessage(
                AnimalMessages.MESSAGE_INVALID_SEARCH_KEYWORDS,
                SearchAnimalCommand.EXAMPLE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        try {
            // no leading and trailing whitespaces
            SearchAnimalCommand expectedSearchAnimalCommand =
                    new SearchAnimalCommandParser().parse(" n/Pookie");
            assertParseSuccess(parser, " n/Pookie", expectedSearchAnimalCommand);

            // multiple prefixes
            expectedSearchAnimalCommand =
                    new SearchAnimalCommandParser().parse(" n/Pookie id/1234");
            assertParseSuccess(parser, " n/Pookie id/1234", expectedSearchAnimalCommand);

            // different order of prefixes
            expectedSearchAnimalCommand =
                    new SearchAnimalCommandParser().parse(" id/1234 n/Pookie");
            assertParseSuccess(parser, " id/1234 n/Pookie", expectedSearchAnimalCommand);
        } catch (Exception e) {
            fail("Exception should not be thrown");
        }
    }
}
