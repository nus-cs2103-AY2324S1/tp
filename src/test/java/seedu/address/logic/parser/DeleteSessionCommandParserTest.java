package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.DeleteSessionCommand;
import seedu.address.model.session.SessionNumber;

public class DeleteSessionCommandParserTest {

    private DeleteSessionCommandParser parser = new DeleteSessionCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteCommand() {
        assertParseSuccess(parser, " s/1", new DeleteSessionCommand(new SessionNumber("1")));
    }
    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, " s/abc", SessionNumber.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "", String.format(
                MESSAGE_INVALID_COMMAND_FORMAT, DeleteSessionCommand.MESSAGE_USAGE));
    }
}
