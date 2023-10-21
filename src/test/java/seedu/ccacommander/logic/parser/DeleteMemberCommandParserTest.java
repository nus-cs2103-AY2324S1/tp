package seedu.ccacommander.logic.parser;

import static seedu.ccacommander.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.ccacommander.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.ccacommander.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.ccacommander.testutil.TypicalIndexes.INDEX_FIRST_MEMBER;

import org.junit.jupiter.api.Test;

import seedu.ccacommander.logic.commands.DeleteMemberCommand;

public class DeleteMemberCommandParserTest {
    private DeleteMemberCommandParser parser = new DeleteMemberCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteMemberCommand() {
        assertParseSuccess(parser, "1", new DeleteMemberCommand(INDEX_FIRST_MEMBER));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeleteMemberCommand.MESSAGE_USAGE));
    }
}
