package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_NON_INTEGER_INDEX;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_COMPANY;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ViewCommand;

public class ViewCommandParserTest {

    private ViewCommandParser parser = new ViewCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteCommand() {
        assertParseSuccess(parser, "1", new ViewCommand(INDEX_FIRST_COMPANY));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", MESSAGE_NON_INTEGER_INDEX);
    }
}
