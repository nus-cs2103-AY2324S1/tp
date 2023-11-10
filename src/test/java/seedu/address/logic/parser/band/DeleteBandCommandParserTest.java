package seedu.address.logic.parser.band;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_MUSICIAN;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.band.DeleteBandCommand;

public class DeleteBandCommandParserTest {
    private DeleteBandCommandParser parser = new DeleteBandCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteCommand() {
        assertParseSuccess(parser, "1", new DeleteBandCommand(INDEX_FIRST_MUSICIAN));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteBandCommand.MESSAGE_USAGE));
    }
}
