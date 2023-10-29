package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_CARD;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.PractiseCommand;


public class PractiseCommandParserTest {

    private PractiseCommandParser parser = new PractiseCommandParser();

    @Test
    public void parse_validArgs_returnsPractiseCommand() {
        assertParseSuccess(parser, "1", new PractiseCommand(INDEX_FIRST_CARD));
    }

    @Test
    public void parse_invalidArgs_throwsPractiseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT, PractiseCommand.MESSAGE_USAGE));
    }

}
