package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.DEVELOPER_DESC_DEVELOPER1;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.TEAMNAME_DESC_TEAM3;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TEAM_TEAM3;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TEAMNAME;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.AddDevToTeamCommand;
import seedu.address.model.person.Name;

public class AddDevToTeamParserTest {
    private AddDevToTeamParser parser = new AddDevToTeamParser();
    @Test
    public void parse_allFieldsPresent_success() {
        Name amyName = new Name(VALID_NAME_AMY);
        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + TEAMNAME_DESC_TEAM3
                + DEVELOPER_DESC_DEVELOPER1, new AddDevToTeamCommand(VALID_TEAM_TEAM3, amyName));
    }

    @Test
    public void parse_repeatedNonTagValue_failure() {
        String validExpectedAdd = TEAMNAME_DESC_TEAM3 + DEVELOPER_DESC_DEVELOPER1;

        // multiple teamNames
        assertParseFailure(parser, TEAMNAME_DESC_TEAM3 + validExpectedAdd,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_TEAMNAME));

        // multiple developers
        assertParseFailure(parser, DEVELOPER_DESC_DEVELOPER1 + validExpectedAdd,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddDevToTeamCommand.MESSAGE_USAGE);

        // missing teamname prefix
        assertParseFailure(parser, VALID_TEAM_TEAM3 + DEVELOPER_DESC_DEVELOPER1,
                expectedMessage);

        // missing developer prefix
        assertParseFailure(parser, TEAMNAME_DESC_TEAM3 + VALID_NAME_AMY,
                expectedMessage);

    }

}
