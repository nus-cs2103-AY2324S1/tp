package seedu.address.logic.parser;

import org.junit.jupiter.api.Test;
import seedu.address.logic.commands.EditTeamNameCommand;


import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.*;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

public class EditTeamNameCommandParserTest {
    private EditTeamNameCommandParser parser = new EditTeamNameCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + TEAMNAME_DESC_TEAM1
                + TEAMNAME_DESC_TEAM2, new EditTeamNameCommand(VALID_TEAM_TEAM1, VALID_TEAM_TEAM2));
    }

    @Test
    public void parse_repeatedValue_failure() {
        String validExpectedTeam = TEAMNAME_DESC_TEAM1 + TEAMNAME_DESC_TEAM2;
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditTeamNameCommand.MESSAGE_USAGE);

        // more teamNames before valid input
        assertParseFailure(parser, TEAMNAME_DESC_TEAM3 + validExpectedTeam,
                expectedMessage);

        // more teamNames after valid input
        assertParseFailure(parser,  validExpectedTeam + TEAMNAME_DESC_TEAM3,
                expectedMessage);
    }
    @Test
    public void parse_PrefixMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditTeamNameCommand.MESSAGE_USAGE);

        // missing first teamName prefix
        assertParseFailure(parser, VALID_TEAM_TEAM1 + TEAMNAME_DESC_TEAM2,
                expectedMessage);

        // missing last teamName prefix
        assertParseFailure(parser, TEAMNAME_DESC_TEAM1 + VALID_TEAM_TEAM2,
                expectedMessage);

    }


    @Test
    public void parser_noPrefix_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditTeamNameCommand.MESSAGE_USAGE);
        assertParseFailure(parser, VALID_TEAM_TEAM1 + VALID_TEAM_TEAM2,
                expectedMessage);
    }
}
