package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.TEAMLEADER_DESC_LEADER3;
import static seedu.address.logic.commands.CommandTestUtil.TEAMNAME_DESC_TEAM3;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_FIONA;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TEAM_TEAM3;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TEAMLEADER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TEAMNAME;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.AddTeamCommand;
import seedu.address.model.person.Name;

public class AddTeamCommandParserTest {
    private AddTeamCommandParser parser = new AddTeamCommandParser();
    @Test
    public void parse_allFieldsPresent_success() {
        Name fionaName = new Name(VALID_NAME_FIONA);
        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + TEAMNAME_DESC_TEAM3
                + TEAMLEADER_DESC_LEADER3, new AddTeamCommand(VALID_TEAM_TEAM3, fionaName));
    }

    @Test
    public void parse_repeatedNonTagValue_failure() {
        String validExpectedTeam = TEAMNAME_DESC_TEAM3 + TEAMLEADER_DESC_LEADER3;

        // multiple teamNames
        assertParseFailure(parser, TEAMNAME_DESC_TEAM3 + validExpectedTeam,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_TEAMNAME));

        // multiple teamLeaders
        assertParseFailure(parser, TEAMLEADER_DESC_LEADER3 + validExpectedTeam,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_TEAMLEADER));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTeamCommand.MESSAGE_USAGE);

        // missing teamname prefix
        assertParseFailure(parser, VALID_TEAM_TEAM3 + TEAMLEADER_DESC_LEADER3,
                expectedMessage);

        // missing teamleader prefix
        assertParseFailure(parser, TEAMNAME_DESC_TEAM3 + VALID_NAME_FIONA,
                expectedMessage);

    }

}
