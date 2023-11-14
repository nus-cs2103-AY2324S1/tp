package seedu.address.logic.parser.event;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.event.DeleteEventCommand;

public class DeleteEventCommandParserTest {
    private DeleteEventCommandParser parser = new DeleteEventCommandParser();

    @Test
    public void parseValidArgs_returnsDeleteEventCommand() {
        assertParseSuccess(parser, "1", new DeleteEventCommand(INDEX_FIRST));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeleteEventCommand.MESSAGE_USAGE));
    }

}
