package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.DeleteTeamCommand;

public class DeleteTeamCommandParserTest {

    private DeleteTeamCommandParser parser = new DeleteTeamCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteTeamCommand() {
        assertParseSuccess(parser, " tn/Team1", new DeleteTeamCommand("Team1"));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "123", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                                                                    DeleteTeamCommand.MESSAGE_USAGE));
    }
}
